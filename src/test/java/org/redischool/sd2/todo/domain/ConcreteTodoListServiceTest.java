package org.redischool.sd2.todo.domain;

import javafx.css.CssParser;
import org.junit.jupiter.api.Test;
import org.redischool.sd2.todo.api.TodoServiceController;

import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteTodoListServiceTest {

    @Test
    public void shouldReturn2Weeks(){
        RecurringTask myItem = new RecurringTask("test", Period.ofWeeks(2));
        TodoServiceController.ItemDto result = myItem.toItemDto();
        assertEquals(2,result.frequency);

    }

    @Test
    public void shouldReturn6months(){
        RecurringTask myItem = new RecurringTask("test", Period.ofMonths(6));
        TodoServiceController.ItemDto result = myItem.toItemDto();
        assertEquals(6,result.frequency);

    }
}
