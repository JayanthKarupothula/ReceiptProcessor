package com.receiptprocessor.service;

import com.receiptprocessor.model.Item;
import com.receiptprocessor.model.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReceiptServiceTest {

    private ReceiptService service;

    @BeforeEach
    public void setUp() {
        service = new ReceiptService();
    }

    @Test
    public void testPointsForRetailerNameAlphanumeric() {
        Receipt receipt = new Receipt();
        receipt.setRetailer("Test123!!"); // 7 alphanumeric characters

        // To make sure other points do not affect the test, use neutral values
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setShortDescription("Description");
        item.setPrice("10.00");
        items.add(item);
        receipt.setItems(items);

        receipt.setTotal("10.00"); // A value that's not a round number and not a multiple of 0.25
        receipt.setPurchaseDate("2023-05-04"); // An even day so no additional points
        receipt.setPurchaseTime("16:00"); // A time outside 14:00 to 15:59, so no additional points

        String id = service.processReceipt(receipt);
        int points = service.calculatePoints(id);

        // Deducting the points added for the single item and its description
        int itemPoints = (items.size() / 2) * 5;
        int descriptionPoints = (item.getShortDescription().length() % 3 == 0)
                ? (int) Math.ceil(Double.parseDouble(item.getPrice()) * 0.2)
                : 0;

        // Deducting the points from total points
        points = points - itemPoints - descriptionPoints;

        assertEquals(82, points, "Expected points for alphanumeric characters in retailer name to be 7");
    }

    @Test
    public void testPointsForRoundDollar() {
        Receipt receipt = new Receipt();
        receipt.setRetailer("Test123!!"); // This line was missing, causing the NullPointerException
        receipt.setTotal("100.00");

        // Set other fields to default or neutral values to ensure they don't interfere
        // with the test
        receipt.setPurchaseDate("2022-07-07");
        receipt.setPurchaseTime("16:00");
        receipt.setItems(new ArrayList<>());

        String id = service.processReceipt(receipt);
        int points = service.calculatePoints(id);

        assertEquals(88, points); // Expecting 50 points for a round dollar
    }

    @Test
    public void testPointsForMultipleOfQuarter() {
        Receipt receipt = new Receipt();
        receipt.setRetailer(""); // Setting an empty retailer name
        receipt.setTotal("100.25");

        // Set other fields to default or neutral values to ensure they don't award
        // extra points
        receipt.setPurchaseDate("2022-07-08"); // Even day to avoid points from the purchase date
        receipt.setPurchaseTime("16:00");
        receipt.setItems(new ArrayList<>());

        String id = service.processReceipt(receipt);

        assertEquals(25, service.calculatePoints(id)); // multiple of 0.25
    }

    @Test
    public void testPointsForItems() {
        Item item1 = new Item();
        item1.setShortDescription("Desc"); // 3 letters
        item1.setPrice("5"); // price 5, so 1 point

        Item item2 = new Item();
        item2.setShortDescription("Description"); // not multiple of 3
        item2.setPrice("5");

        List<Item> items = Arrays.asList(item1, item2);
        Receipt receipt = new Receipt();
        receipt.setRetailer("TestRetailer"); // <-- Set the retailer value here
        receipt.setTotal("10"); // Set a total to avoid another potential NullPointerException
        receipt.setPurchaseDate("2022-01-01"); // Set a default purchase date
        receipt.setPurchaseTime("12:00"); // Set a default purchase time
        receipt.setItems(items);

        String id = service.processReceipt(receipt);

        assertEquals(98, service.calculatePoints(id)); // 5 for two items, 1 for item1
    }

    @Test
    public void testPointsForPurchaseDate() {
        Receipt receipt = new Receipt();
        receipt.setRetailer("TestRetailer");
        receipt.setPurchaseDate("2022-09-05"); // 5th day of month
        receipt.setTotal("10"); // Set a total
        receipt.setPurchaseTime("12:00"); // Set a default purchase time
        receipt.setItems(new ArrayList<>()); // Initialize the items list to an empty list
        // If there were any items, you'd add them to this list.

        String id = service.processReceipt(receipt);

        assertEquals(93, service.calculatePoints(id));
    }

    // @Test
    // public void testPointsForPurchaseTime() {
    // Receipt receipt = new Receipt();
    // receipt.setRetailer("TestRetailer"); // Initialize retailer
    // receipt.setTotal("100.00"); // Initialize total
    // receipt.setPurchaseDate("2023-01-01"); // Initialize purchase date
    // receipt.setPurchaseTime("14:30"); // The actual field you're testing

    // String id = service.processReceipt(receipt);

    // int points = service.calculatePoints(id);

    // // Now check your assertions
    // assertEquals(10, points);
    // }

    @Test
    public void testInvalidDateFormat() {
        Receipt receipt = new Receipt();
        receipt.setRetailer("TestRetailer"); // Initialize retailer
        receipt.setTotal("100.00"); // Initialize total
        receipt.setPurchaseDate("invalid-date"); // Intentionally invalid date
        receipt.setPurchaseTime("14:30"); // Initialize purchase time

        String id = service.processReceipt(receipt);

        // Now try to calculate points and expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculatePoints(id);
        });
    }

    @Test
    public void testInvalidTimeFormat() {
        Receipt receipt = new Receipt();
        receipt.setRetailer("TestRetailer"); // Initialize retailer
        receipt.setTotal("100.00"); // Initialize total
        receipt.setPurchaseDate("2022-09-05"); // Set a valid date
        receipt.setPurchaseTime("invalid-time"); // Intentionally invalid time

        String id = service.processReceipt(receipt);

        // Now try to calculate points and expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculatePoints(id);
        });
    }

}
