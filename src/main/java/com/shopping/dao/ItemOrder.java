package com.shopping.dao;

public class ItemOrder {

    private String itemName;

    private long quantities;

    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getQuantities() {
        return quantities;
    }

    public void setQuantities(long quantities) {
        this.quantities = quantities;
    }
}
