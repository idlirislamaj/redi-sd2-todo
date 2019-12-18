package org.redischool.sd2.todo.api;

import org.redischool.sd2.todo.domain.Item;
import org.redischool.sd2.todo.domain.OneTimeTask;
import org.redischool.sd2.todo.domain.TodoListService;
import org.redischool.sd2.todo.domain.ConcreteTodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
  public final class TodoServiceController {
    private final TodoListService todoListService;

  TodoServiceController(@Autowired TodoListService todoListService) {
    this.todoListService = todoListService;
  }

  @GetMapping("/api/items")
  FetchItemsResponseDto fetchItems() {
    return new FetchItemsResponseDto(currentItems());
  }

  @PostMapping("/api/items")
  AddItemResponseDto addItem(@RequestBody AddItemRequestDto addItemDto) {
    switch (addItemDto.type) {
      case "TASK":
        if (addItemDto.deadline != null && !addItemDto.deadline.isEmpty()) {
          todoListService.addTaskWithDeadline(
              addItemDto.label, LocalDate.parse(addItemDto.deadline, DateTimeFormatter.ISO_DATE));
        } else {
          todoListService.addTask(addItemDto.label);
        }
        break;
      case "RECURRING":
        todoListService.addRecurringTask(
            addItemDto.label, toPeriod(addItemDto.frequency, addItemDto.period));
        break;
      case "SHOPPING_ITEM":
        todoListService.addShoppingItem(addItemDto.label, addItemDto.amount);
        break;
      default:
        throw new HttpClientErrorException(
            HttpStatus.BAD_REQUEST, String.format("Unknown type %s", addItemDto.type));
    }
    return new AddItemResponseDto(currentItems());
  }

  private Period toPeriod(int frequency, String unit) {
    switch (unit) {
      case "DAY":
        return Period.ofDays(frequency);
      case "WEEK":
        return Period.ofWeeks(frequency);
      case "MONTH":
        return Period.ofMonths(frequency);
      case "YEAR":
        return Period.ofYears(frequency);
      default:
        throw new HttpClientErrorException(
            HttpStatus.BAD_REQUEST, String.format("Unknown period unit %s", unit));
    }
  }

  @DeleteMapping("/api/items/{id}")
  DeleteItemResponseDto deleteItem(@PathVariable("id") String id) {
    todoListService.markCompleted(id);
    return new DeleteItemResponseDto(currentItems());
  }

  @PutMapping("/api/items:updateRecurring")
  void updateRecurringTasks() {
    todoListService.updateRecurringTasks();
  }

  private List<ItemDto> currentItems() {

    List<ItemDto> myDto = new ArrayList<>();
    List<Item> myItems = ConcreteTodoListService.currentItems();
    for (Item item : myItems){
      ItemDto myItemDto = item.toItemDto();
      myDto.add(myItemDto);
    }
    return myDto;
    //return List.of(
       // ItemDto.oneTimeTaskWithLabel("Learn German"),
       // ItemDto.oneTimeTaskWithLabelAndDeadline("Do mid-semester project for ReDI", "2019-12-31"),
       // ItemDto.recurringTaskWithLabel("Do ReDI homework"),
       // ItemDto.shoppingItemWithLabel("Müesli"));
  }

  private static final class FetchItemsResponseDto {
    private final List<ItemDto> items;

    FetchItemsResponseDto(List<ItemDto> items) {
      this.items = List.copyOf(items);
    }

    public List<ItemDto> getItems() {
      return items;
    }
  }

  private static final class AddItemResponseDto {
    private final List<ItemDto> items;

    AddItemResponseDto(List<ItemDto> items) {
      this.items = List.copyOf(items);
    }

    public List<ItemDto> getItems() {
      return items;
    }
  }

  private static final class DeleteItemResponseDto {
    private final List<ItemDto> items;

    DeleteItemResponseDto(List<ItemDto> items) {
      this.items = List.copyOf(items);
    }

    public List<ItemDto> getItems() {
      return items;
    }
  }

  public static final class ItemDto {
    private static long nextId = 0;
    public String id;
    public String label;
    public String type;
    public Integer amount;
    public Integer frequency;
    public String period;
    public String deadline;

    static ItemDto oneTimeTaskWithLabel(String label) {
      ItemDto itemDto = new ItemDto();
      itemDto.label = label;
      itemDto.id = String.valueOf(nextId++);
      itemDto.type = "TASK";
      return itemDto;
    }

    static ItemDto oneTimeTaskWithLabelAndDeadline(String label, String deadline) {
      ItemDto itemDto = new ItemDto();
      itemDto.label = label;
      itemDto.deadline = deadline;
      return itemDto;
    }

    static ItemDto recurringTaskWithLabel(String label) {
      ItemDto itemDto = new ItemDto();
      itemDto.label = label;
      itemDto.frequency = 1;
      itemDto.period = "WEEK";
      itemDto.id = String.valueOf(nextId++);
      itemDto.type = "RECURRING";
      return itemDto;
    }

    static ItemDto shoppingItemWithLabel(String label) {
      ItemDto itemDto = new ItemDto();
      itemDto.label = label;
      itemDto.amount = 1;
      itemDto.id = String.valueOf(nextId++);
      itemDto.type = "SHOPPING_ITEM";
      return itemDto;
    }

    public String getId() {
      return id;
    }

    public String getLabel() {
      return label;
    }

    public String getType() {
      return type;
    }

    public Integer getAmount() {
      return amount;
    }

    public Integer getFrequency() {
      return frequency;
    }

    public String getPeriod() {
      return period;
    }

    public String getDeadline() {
      return deadline;
    }
  }

  private static final class AddItemRequestDto {
    String label;
    String type;
    Integer amount;
    Integer frequency;
    String period;
    String deadline;

    public void setLabel(String label) {
      this.label = label;
    }

    public void setType(String type) {
      this.type = type;
    }

    public void setAmount(Integer amount) {
      this.amount = amount;
    }

    public void setFrequency(Integer frequency) {
      this.frequency = frequency;
    }

    public void setPeriod(String period) {
      this.period = period;
    }

    public void setDeadline(String deadline) {
      this.deadline = deadline;
    }
  }
}
