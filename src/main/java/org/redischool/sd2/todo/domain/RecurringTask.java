package org.redischool.sd2.todo.domain;
import org.redischool.sd2.todo.api.TodoServiceController;

import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;

public class RecurringTask extends Item {
    Period period;
    Integer frequency;


    public RecurringTask(String label, java.time.Period period) {
        super(label);
        this.period = period;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public TodoServiceController.ItemDto toItemDto() {
        TodoServiceController.ItemDto result = new TodoServiceController.ItemDto();
        result.label = getLabel();
        if (getPeriod().getYears() > 0){
            result.period = "YEAR";
            result.frequency = getPeriod().getYears();
        }
        if (getPeriod().getMonths() > 0){
            result.period = "MONTH";
            result.frequency = getPeriod().getMonths();
        }
        if ((getPeriod().getDays())>0){
            if ((getPeriod().getDays())%7 == 0){
                result.period = "WEEK";
                result.frequency = (getPeriod().getDays())/7;
            }else{
                result.period = "DAY";
                result.id = String.valueOf(getId());
                result.frequency = getPeriod().getDays();

            }}
        result.type = "RECURRING";
        result.id = String.valueOf(getId());
        return result;
    }
}
