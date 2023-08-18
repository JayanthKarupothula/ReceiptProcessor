package com.receiptprocessor.service;

import com.receiptprocessor.model.Item;
import com.receiptprocessor.model.Receipt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptService {

    private final ConcurrentHashMap<String, Receipt> receipts = new ConcurrentHashMap<>();

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipts.put(id, receipt);
        return id;
    }

    public int calculatePoints(String id) {
        Receipt receipt = receipts.get(id);
        if (receipt == null) {
            return -1; // Not found
        }

        int points = 0;

        // Points for retailer name's alphanumeric characters
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // Points for total being a round dollar or multiple of 0.25
        String totalValue = receipt.getTotal();
        if (totalValue == null) {
            throw new IllegalArgumentException("Total value in receipt is null.");
        }
        double total = Double.parseDouble(totalValue.trim());
        if (total % 1 == 0)
            points += 50;
        if (total % 0.25 == 0)
            points += 25;

        // Points for items
        List<Item> items = receipt.getItems();
        if (items == null) {
            throw new IllegalArgumentException("Items list in receipt is null.");
        }
        points += (items.size() / 2) * 5;
        for (Item item : items) {
            String description = item.getShortDescription().trim();
            if (description.length() % 3 == 0) {
                double itemPrice = Double.parseDouble(item.getPrice().trim());
                points += Math.ceil(itemPrice * 0.2);
            }
        }

        // Points for purchase date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate purchaseDate = LocalDate.parse(receipt.getPurchaseDate().trim(), dateFormatter);
            if (purchaseDate.getDayOfMonth() % 2 != 0)
                points += 6;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid purchase date format.");
        }

        // Points for purchase time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime purchaseTime = LocalTime.parse(receipt.getPurchaseTime().trim(), timeFormatter);
            if (!purchaseTime.isBefore(LocalTime.of(14, 0)) && !purchaseTime.isAfter(LocalTime.of(15, 59)))
                points += 10;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid purchase time format.");
        }

        return points;
    }
}
