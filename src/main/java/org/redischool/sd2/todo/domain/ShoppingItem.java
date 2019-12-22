package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.TodoServiceController;

public class ShoppingItem extends Item {
    Integer amount;

    public ShoppingItem(String label, Integer amount) {
        super(label);
        this.amount = amount;
    }

    public ShoppingItem(String label) {
        super(label);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public TodoServiceController.ItemDto toItemDto() {
        TodoServiceController.ItemDto result = new TodoServiceController.ItemDto();
        result.label = getLabel();
        result.amount = getAmount();
        result.type = "SHOPPING_ITEM";
        return result;
    }
}
