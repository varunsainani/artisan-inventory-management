package models;

import java.time.LocalDateTime;

public class Product extends Record {
    private String artisanId;
    private String name;
    private String description;
    private int quantity;
    private String status;

    public Product(String id, String artisanId, String name, String description, int quantity, String status,
            LocalDateTime timestamp) {
        super(id, timestamp);
        this.artisanId = artisanId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
    }

    public String getArtisanId() {
        return artisanId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return id + "|" + artisanId + "|" + name + "|" + description + "|" + quantity + "|" + status + "|"
                + timestamp.toString();
    }

    public static Product fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 7) {
            return new Product(
                    parts[0],
                    parts[1],
                    parts[2],
                    parts[3],
                    Integer.parseInt(parts[4]),
                    parts[5],
                    LocalDateTime.parse(parts[6]));
        }
        return null;
    }
}