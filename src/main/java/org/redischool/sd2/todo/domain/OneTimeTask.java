package org.redischool.sd2.todo.domain;

import java.time.LocalDate;

public class OneTimeTask extends Item {
    LocalDate deadline;

    OneTimeTask(String lbl){
        super(lbl);
    }

    OneTimeTask(String lbl, LocalDate dline){
        super(lbl);
        this.deadline = dline;
    }
}
