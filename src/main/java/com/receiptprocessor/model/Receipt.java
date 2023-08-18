// Package declaration specifying where the class resides in the project's structure
package com.receiptprocessor.model;

// Importing the List data structure for storing multiple items in the receipt
import java.util.List;

// The Receipt class definition which models a receipt containing retailer information, purchase details, and items
public class Receipt {

    // Private instance variable for the retailer's name or identifier
    private String retailer;

    // Private instance variable for the date of the purchase
    private String purchaseDate;

    // Private instance variable for the time of the purchase
    private String purchaseTime;

    // Private instance variable for the list of items in the receipt
    private List<Item> items;

    // Private instance variable for the total amount of the purchase
    private String total;

    // Default constructor which initializes the Receipt object with default values
    public Receipt() {
    }

    // Getter method to retrieve the name or identifier of the retailer
    public String getRetailer() {
        return retailer;
    }

    // Setter method to set the name or identifier of the retailer
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    // Getter method to retrieve the date of the purchase
    public String getPurchaseDate() {
        return purchaseDate;
    }

    // Setter method to set the date of the purchase
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // Getter method to retrieve the time of the purchase
    public String getPurchaseTime() {
        return purchaseTime;
    }

    // Setter method to set the time of the purchase
    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    // Getter method to retrieve the list of items in the receipt
    public List<Item> getItems() {
        return items;
    }

    // Setter method to set the list of items in the receipt
    public void setItems(List<Item> items) {
        this.items = items;
    }

    // Getter method to retrieve the total amount of the purchase
    public String getTotal() {
        return total;
    }

    // Setter method to set the total amount of the purchase
    public void setTotal(String total) {
        this.total = total;
    }
}
