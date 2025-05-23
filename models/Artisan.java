package models;

public class Artisan {
    private String artisanId;
    private String name;
    private String contact;
    private String status;

    public Artisan(String artisanId, String name, String contact, String status) {
        this.artisanId = artisanId;
        this.name = name;
        this.contact = contact;
        this.status = status;
    }

    public String getArtisanId() {
        return artisanId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return artisanId + "|" + name + "|" + contact + "|" + status;
    }

    public static Artisan fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 4) {
            return new Artisan(parts[0], parts[1], parts[2], parts[3]);
        }
        return null;
    }
}