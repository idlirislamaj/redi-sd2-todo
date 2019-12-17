package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.TodoServiceController;

public abstract class Item{
    String label;

    public Item(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public abstract TodoServiceController.ItemDto toItemDto();
}
