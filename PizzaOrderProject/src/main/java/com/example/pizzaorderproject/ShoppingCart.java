package com.example.pizzaorderproject;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // Method to add an item to the cart
    public void addItem(Item item) {
        items.add(item);
    }

    // Method to remove an item from the cart
    public void removeItem(Item item) {
        items.remove(item);
    }


    // Method to get the list of items in the cart
    public List<Item> getItems() {
        return items;
    }

    // Method to calculate the total price of all items in the cart
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}