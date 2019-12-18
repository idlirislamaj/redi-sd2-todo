package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.TodoServiceController;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public final class ConcreteTodoListService implements TodoListService {
  @Override
  public void addTask(String label) {
    String lbl = label;
    Item myItem = new OneTimeTask(lbl);
    myItems.add(myItem);

    //throw new UnsupportedOperationException("Not implemented yet");
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
  static List<Item> myItems = new ArrayList<>();
  static int count = 0;
  public static List<Item> currentItems(){
    if (count == 0){
      myItems.add(new OneTimeTask("Learn Java"));
      myItems.add(new OneTimeTask("Learn German",LocalDate.of(2019, Month.NOVEMBER,9)));
      myItems.add(new RecurringTask("Do ReDI homework",Period.ofWeeks(1)));
      myItems.add(new ShoppingItem("Müesli"));
    }
    count++;
    return myItems;
}}
