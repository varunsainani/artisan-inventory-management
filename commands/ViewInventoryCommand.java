package commands;

import models.Product;
import utils.FileUtils;

public class ViewInventoryCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Approved Product Inventory:");
        for (String line : FileUtils.readLines("data/products.txt")) {
            Product product = Product.fromFileString(line);
            if (product != null && product.getStatus().equalsIgnoreCase("Approved")) {
                System.out.println("ID: " + product.getId() +
                        ", Name: " + product.getName() +
                        ", Quantity: " + product.getQuantity() +
                        ", Artisan ID: " + product.getArtisanId());
            }
        }
    }
}