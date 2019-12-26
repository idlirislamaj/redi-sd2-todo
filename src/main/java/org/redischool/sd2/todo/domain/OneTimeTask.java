package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.TodoServiceController;

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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public TodoServiceController.ItemDto toItemDto() {
        TodoServiceController.ItemDto result = new TodoServiceController.ItemDto();
        result.label = getLabel();

        if (this.deadline != null){
            result.deadline = getDeadline().toString();
        }
        result.id = String.valueOf(getId());
        result.type = "TASK";
        return result;
    }
}
