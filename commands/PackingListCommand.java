package commands;

import models.Order;
import models.Product;
import utils.FileUtils;

import java.util.HashMap;
import java.util.Map;

public class PackingListCommand implements Command {
    @Override
    public void execute() {
        Map<String, Integer> packingList = new HashMap<>();

        for (String line : FileUtils.readLines("data/orders.txt")) {
            Order order = Order.fromFileString(line);
            if (order != null) {
                packingList.put(order.getProductId(),
                        packingList.getOrDefault(order.getProductId(), 0) + order.getQuantity());
            }
        }

        System.out.println("Packing List:");
        for (String line : FileUtils.readLines("data/products.txt")) {
            Product product = Product.fromFileString(line);
            if (product != null && packingList.containsKey(product.getId())) {
                System.out.println("Product: " + product.getName() +
                        ", Qty: " + packingList.get(product.getId()));
            }
        }
    }
}