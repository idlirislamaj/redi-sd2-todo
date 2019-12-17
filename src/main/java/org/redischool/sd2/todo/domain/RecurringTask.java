package org.redischool.sd2.todo.domain;
import org.redischool.sd2.todo.api.TodoServiceController;
import java.time.Period;

public class RecurringTask extends Item {
    Period period;

    public RecurringTask(String label, Period period) {
        super(label);
        this.period = period;
    }

    public Period getPeriod() {
        return period;
    }

    @Override
    public TodoServiceController.ItemDto toItemDto() {
        TodoServiceController.ItemDto result = new TodoServiceController.ItemDto();
        result.label = getLabel();
        result.period = getPeriod().toString();
        return result;
    }
}
