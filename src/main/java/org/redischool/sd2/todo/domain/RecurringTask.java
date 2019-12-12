package org.redischool.sd2.todo.domain;

import java.time.LocalDate;
import java.time.Period;

public class RecurringTask extends Item {
    Period period;

    public RecurringTask(String label, Period period) {
        super(label);
        this.period = period;
    }
    public RecurringTask(String label) {
        super(label);
    }
}
