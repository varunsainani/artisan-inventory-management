package models;

import java.time.LocalDateTime;

public class Order extends Record {
    private String productId;
    private int quantity;

    public Order(String id, String productId, int quantity, LocalDateTime timestamp) {
        super(id, timestamp);
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toFileString() {
        return id + "|" + productId + "|" + quantity + "|" + timestamp.toString();
    }

    public static Order fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 4) {
            return new Order(
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.parse(parts[3]));
        }
        return null;
    }
}