package org.redischool.sd2.todo.domain;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
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

  public List<Item> currentItems(){
    return List.of(
            new OneTimeTask("Learn German"),
            new OneTimeTask("Learn German",LocalDate.of(2019, Month.NOVEMBER,9)),
            new RecurringTask("Do ReDI homework",Period.ofWeeks(1)),
            new ShoppingItem("Müesli")
    );
  }
}
