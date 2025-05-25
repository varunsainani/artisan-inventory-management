package commands;

import models.Order;
import models.Product;
import utils.FileUtils;

import java.util.*;

public class ViewCommissionCommand implements Command {
    @Override
    public void execute() {
        Map<String, Integer> productSales = new HashMap<>();
        Map<String, Product> productMap = new HashMap<>();

        for (String line : FileUtils.readLines("data/products.txt")) {
            Product product = Product.fromFileString(line);
            if (product != null) {
                productMap.put(product.getId(), product);
            }
        }

        for (String line : FileUtils.readLines("data/orders.txt")) {
            Order order = Order.fromFileString(line);
            if (order != null) {
                productSales.put(order.getProductId(),
                        productSales.getOrDefault(order.getProductId(), 0) + order.getQuantity());
            }
        }

        Map<String, Integer> artisanEarnings = new HashMap<>();
        for (String productId : productSales.keySet()) {
            Product product = productMap.get(productId);
            if (product != null) {
                int qtySold = productSales.get(productId);
                int commission = qtySold * 10; // Flat 10 per item sold
                artisanEarnings.put(product.getArtisanId(),
                        artisanEarnings.getOrDefault(product.getArtisanId(), 0) + commission);
            }
        }

        System.out.println("Commission Report:");
        for (Map.Entry<String, Integer> entry : artisanEarnings.entrySet()) {
            System.out.println("Artisan ID: " + entry.getKey() + ", Commission: Rs. " + entry.getValue());
        }
    }
}