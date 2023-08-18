// Package declaration specifying where the class resides in the project's structure
package com.receiptprocessor.model;

// The Item class definition which models an item with a description and price
public class Item {

    // Declare private instance variables for the item's short description and price
    private String shortDescription;
    private String price;

    // Default constructor which initializes the Item object with default values
    public Item() {
    }

    // Getter method to retrieve the short description of the item
    public String getShortDescription() {
        return shortDescription;
    }

    // Setter method to set the short description of the item
    // It checks if the input is not null, then trims any leading or trailing spaces
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription != null ? shortDescription.trim() : null;
    }

    // Getter method to retrieve the price of the item
    public String getPrice() {
        return price;
    }

    // Setter method to set the price of the item
    // It checks if the input is not null, then trims any leading or trailing spaces
    public void setPrice(String price) {
        this.price = price != null ? price.trim() : null;
    }
}
