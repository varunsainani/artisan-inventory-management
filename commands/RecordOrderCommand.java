package commands;

import models.Order;
import models.Product;
import utils.FileUtils;
import utils.IDGenerator;
import utils.InputUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecordOrderCommand implements Command {
    @Override
    public void execute() {
        String productId = InputUtils.nextLine("Enter product ID: ");
        int orderQty = InputUtils.nextInt("Enter quantity to order: ");

        List<String> lines = FileUtils.readLines("data/products.txt");
        List<String> updated = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            Product product = Product.fromFileString(line);
            if (product != null && product.getId().equals(productId)
                    && product.getStatus().equalsIgnoreCase("Approved")) {
                if (product.getQuantity() >= orderQty) {
                    product.setQuantity(product.getQuantity() - orderQty);
                    found = true;
                    updated.add(product.toFileString());
                } else {
                    System.out.println("Insufficient stock.");
                    return;
                }
            } else {
                updated.add(line);
            }
        }

        if (found) {
            FileUtils.overwriteFile("data/products.txt", updated);
            String orderId = IDGenerator.generateId("ORD");
            Order order = new Order(orderId, productId, orderQty, LocalDateTime.now());
            FileUtils.appendLine("data/orders.txt", order.toFileString());
            System.out.println("Order recorded.");
        } else {
            System.out.println("Product not found or not approved.");
        }
    }
}