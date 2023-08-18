// Import necessary packages for testing and the model
package com.receiptprocessor.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Define the test class for Receipt model
public class ReceiptTest {

    // Declare a Receipt object to be used in tests
    private Receipt receipt;

    // Method annotated with @BeforeEach will be executed before each test
    @BeforeEach
    public void setUp() {
        // Initialize the Receipt object for testing
        receipt = new Receipt();
    }

    // Test for setting and getting retailer's name in Receipt
    @Test
    public void testSetAndGetRetailer() {
        String retailer = "ABC Store";
        // Set retailer's name
        receipt.setRetailer(retailer);
        // Assert if the set retailer's name matches the expected retailer's name
        assertEquals(retailer, receipt.getRetailer());
    }

    // Test for setting and getting purchase date in Receipt
    @Test
    public void testSetAndGetPurchaseDate() {
        String date = "2023-08-16";
        // Set purchase date
        receipt.setPurchaseDate(date);
        // Assert if the set purchase date matches the expected date
        assertEquals(date, receipt.getPurchaseDate());
    }

    // Test for setting and getting purchase time in Receipt
    @Test
    public void testSetAndGetPurchaseTime() {
        String time = "14:30";
        // Set purchase time
        receipt.setPurchaseTime(time);
        // Assert if the set purchase time matches the expected time
        assertEquals(time, receipt.getPurchaseTime());
    }

    // Test for setting and getting list of items in Receipt
    @Test
    public void testSetAndGetItems() {
        // Create two item objects for testing
        Item item1 = new Item();
        item1.setShortDescription("Milk");
        item1.setPrice("1.99");

        Item item2 = new Item();
        item2.setShortDescription("Bread");
        item2.setPrice("2.49");

        // Create a list containing both items
        List<Item> items = Arrays.asList(item1, item2);
        // Set the list of items in the receipt
        receipt.setItems(items);

        // Assert if the size of items in the receipt matches the expected size
        assertEquals(2, receipt.getItems().size());
        // Assert if the description of the first item matches the expected description
        assertEquals("Milk", receipt.getItems().get(0).getShortDescription());
        // Assert if the description of the second item matches the expected description
        assertEquals("Bread", receipt.getItems().get(1).getShortDescription());
    }

    // Test for setting and getting total price in Receipt
    @Test
    public void testSetAndGetTotal() {
        String total = "50.99";
        // Set the total price in the receipt
        receipt.setTotal(total);
        // Assert if the set total price matches the expected total price
        assertEquals(total, receipt.getTotal());
    }
}
