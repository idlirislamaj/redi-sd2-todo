package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.TodoServiceController;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;


@Service
public final class ConcreteTodoListService implements TodoListService {

  public boolean isFound(String label){
    for (Item  item: myItems){
      if (item.getLabel().equals(label)){
        return true;
      }
    }
    return false;
  }

  @Override
  public void addTask(String label) {
    if (isFound(label)){
      for (Item item : myItems){
        if (item.getLabel().equals(label)){
          if (!(item instanceof OneTimeTask)){
            return;
          }
        }
      }
    }else{myItems.add(new OneTimeTask(label));}
  }


  @Override
  public void addTaskWithDeadline(String label, LocalDate deadline) {
    if (isFound(label)){
      for (Item item : myItems){
        if (item.getLabel().equals(label)){
          if (item instanceof OneTimeTask){
            OneTimeTask onetimetask = (OneTimeTask) item;
            onetimetask.setDeadline(deadline);
          }else{return;}
        }
      }
    }else {myItems.add(new OneTimeTask(label,deadline));}
  }

  @Override
  public void addRecurringTask(String label, Period recurrencePeriod) {
    if (isFound(label)){
      for (Item item : myItems){
        if (item.getLabel().equals(label)){
          if (item instanceof RecurringTask){
            RecurringTask recurringTask = (RecurringTask) item;
            recurringTask.setPeriod(recurrencePeriod);
          }else{return;}

        }
      }
    }else {myItems.add(new RecurringTask(label,recurrencePeriod));}
  }

  @Override
  public void addShoppingItem(String label, int amount) {
    if (isFound(label)){
      for (Item item : myItems) {
        if (item.getLabel().equals(label)) {
          if (item instanceof ShoppingItem){
            ShoppingItem shi = (ShoppingItem) item;
            shi.setAmount(shi.getAmount()+amount);
          }else{return;}

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
}
}
