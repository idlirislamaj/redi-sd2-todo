package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.TodoServiceController;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
final class ConcreteTodoListService implements TodoListService {
  @Override
  public void addTask(String label) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public void addTaskWithDeadline(String label, LocalDate deadline) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public void addRecurringTask(String label, Period recurrencePeriod) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public void addShoppingItem(String label, int amount) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public void markCompleted(String itemId) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public void updateRecurringTasks() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public static List<TodoServiceController.ItemDto> currentItems(){
    return List.of(
            TodoServiceController.ItemDto.oneTimeTaskWithLabel("Learn German"),
            TodoServiceController.ItemDto.oneTimeTaskWithLabelAndDeadline("Do mid-semester project for ReDI", "2019-12-31"),
            TodoServiceController.ItemDto.recurringTaskWithLabel("Do ReDI homework"),
            TodoServiceController.ItemDto.shoppingItemWithLabel("Müesli"));
  }
}
