package commands;

import models.Product;
import utils.FileUtils;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class ApproveProductCommand implements Command {
    @Override
    public void execute() {
        List<String> lines = FileUtils.readLines("data/products.txt");
        List<Product> pending = new ArrayList<>();

        for (String line : lines) {
            Product product = Product.fromFileString(line);
            if (product != null && product.getStatus().equalsIgnoreCase("Pending")) {
                pending.add(product);
            }
        }

        if (pending.isEmpty()) {
            System.out.println("No pending products to approve.");
            return;
        }

        for (Product product : pending) {
            System.out.println("ID: " + product.getId() + ", Name: " + product.getName());
            String decision = InputUtils.nextChoice("Approve this product? (yes/no): ", "yes", "no");
            if (decision.equalsIgnoreCase("yes")) {
                product.setStatus("Approved");
            }
        }

        List<String> updatedLines = new ArrayList<>();
        for (Product product : pending) {
            updatedLines.add(product.toFileString());
        }

        for (String line : lines) {
            Product product = Product.fromFileString(line);
            if (product == null || !product.getStatus().equalsIgnoreCase("Pending")) {
                updatedLines.add(line);
            }
        }

        FileUtils.overwriteFile("data/products.txt", updatedLines);
        System.out.println("Product approvals updated.");
    }
}