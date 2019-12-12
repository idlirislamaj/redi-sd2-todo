package org.redischool.sd2.todo.domain;

public class ShoppingItem extends Item {
    Integer amount;

    public ShoppingItem(String label, Integer amount) {
        super(label);
        this.amount = amount;
    }

    public ShoppingItem(String label) {
        super(label);
    }
}
