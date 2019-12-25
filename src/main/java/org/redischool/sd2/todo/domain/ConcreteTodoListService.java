package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.TodoServiceController;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;


@Service
public final class ConcreteTodoListService implements TodoListService {

  @Override
  public void addTask(String label) {
    boolean condition = false;
    for (Item item : myItems){
      if (item.getLabel().equals(label)){
        condition = true;
      }
    }
    if (condition){}else {
      Item item = new OneTimeTask(label);
      myItems.add(item);

    }

    //throw new UnsupportedOperationException("Not implemented yet");
  }


  @Override
  public void addTaskWithDeadline(String label, LocalDate deadline) {

    myItems.add(new OneTimeTask(label,deadline));

    //throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public void addRecurringTask(String label, Period recurrencePeriod) {
    myItems.add(new RecurringTask(label,recurrencePeriod));
  }

  @Override
  public void addShoppingItem(String label, int amount) {
    boolean condition = false;
    for (Item item : myItems){
      if (item.getLabel().equals(label)){
        condition = true;
      }
    }

    if (condition){
      for (Item item : myItems) {
        if (item.getLabel().equals(label)) {
          ShoppingItem shi = (ShoppingItem) item;
          shi.setAmount(shi.getAmount()+amount);
        }
      }
    }else {myItems.add(new ShoppingItem(label,amount));}
  }


  @Override
  public void markCompleted(String itemId) {
    TodoServiceController.currentItems().removeIf(itemdto -> itemdto.id.equals(itemId));
    myItems.removeIf(item -> item.getId() == Integer.parseInt(itemId));
  }

  @Override
  public void updateRecurringTasks() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  static List<Item> myItems = new ArrayList<>();
  public static List<Item> currentItems(){
    return myItems;
}}
