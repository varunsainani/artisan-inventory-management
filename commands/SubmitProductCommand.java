package commands;

import models.Product;
import utils.FileUtils;
import utils.IDGenerator;
import utils.InputUtils;

import java.time.LocalDateTime;

public class SubmitProductCommand implements Command {
    private final String artisanId;

    public SubmitProductCommand(String artisanId) {
        this.artisanId = artisanId;
    }

    @Override
    public void execute() {
        String name = InputUtils.nextLine("Product name: ");
        String desc = InputUtils.nextLine("Product description: ");
        int qty = InputUtils.nextInt("Quantity: ");

        String productId = IDGenerator.generateId("PROD");
        Product product = new Product(productId, artisanId, name, desc, qty, "Pending", LocalDateTime.now());
        FileUtils.appendLine("data/products.txt", product.toFileString());

        System.out.println("Product submitted for approval.");
    }
}