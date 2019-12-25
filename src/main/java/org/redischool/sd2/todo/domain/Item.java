package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.TodoServiceController;

public abstract class Item{

    String label;
    Integer id;
    public static int nextId = 1;

    public Integer getId() {
        return id;
    }


    public Item(String label) {
        this.label = label;
        // Get an ID
        this.id = Item.nextId;
        // Increase the ID for the next Item
        Item.nextId++;
    }


    public String getLabel() {
        return label;
    }

    public abstract TodoServiceController.ItemDto toItemDto();
}
