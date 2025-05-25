package commands;

import models.Product;
import utils.FileUtils;

public class ViewMyProductsCommand implements Command {
    private final String artisanId;

    public ViewMyProductsCommand(String artisanId) {
        this.artisanId = artisanId;
    }

    @Override
    public void execute() {
        System.out.println("Your Products:");
        for (String line : FileUtils.readLines("data/products.txt")) {
            Product product = Product.fromFileString(line);
            if (product != null && product.getArtisanId().equals(artisanId)) {
                System.out.println("ID: " + product.getId() +
                        ", Name: " + product.getName() +
                        ", Qty: " + product.getQuantity() +
                        ", Status: " + product.getStatus());
            }
        }
    }
}