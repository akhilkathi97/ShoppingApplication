package com.shopping.model;

import javax.persistence.*;

@Entity
@Table(name="ITEM_DETAILS")
public class ItemDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ITEM_ID")
    private long itemId;

    @Column(name="ITEM_NAME")
    private String itemName;

    @Column(name="ITEM_PRICE")
    private float itemPrice;

    @Column(name="AVAILABLE_QUANTITY")
    private long availableQuantity;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
