// Import necessary packages for testing and the model
package com.receiptprocessor.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// Define the test class for the Item model
public class ItemTest {

    // Test to check if the extra spaces in the short description are being trimmed
    // correctly
    @Test
    public void testShortDescriptionTrimming() {
        // Create a new Item object for testing
        Item item = new Item();

        // Set a short description with leading and trailing spaces
        item.setShortDescription("  testDescription  ");

        // Assert that the spaces are trimmed and the returned description matches the
        // expected one
        assertEquals("testDescription", item.getShortDescription());
    }

    // Test to check if the extra spaces in the price are being trimmed correctly
    @Test
    public void testPriceTrimming() {
        // Create a new Item object for testing
        Item item = new Item();

        // Set a price with leading and trailing spaces
        item.setPrice("  25.50  ");

        // Assert that the spaces are trimmed and the returned price matches the
        // expected one
        assertEquals("25.50", item.getPrice());
    }

    // Test to check behavior when the short description is set to null
    @Test
    public void testNullDescription() {
        // Create a new Item object for testing
        Item item = new Item();

        // Set the short description to null
        item.setShortDescription(null);

        // Assert that the returned short description is null
        assertNull(item.getShortDescription());
    }

    // Test to check behavior when the price is set to null
    @Test
    public void testNullPrice() {
        // Create a new Item object for testing
        Item item = new Item();

        // Set the price to null
        item.setPrice(null);

        // Assert that the returned price is null
        assertNull(item.getPrice());
    }

}
