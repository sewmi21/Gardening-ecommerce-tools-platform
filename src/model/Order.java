package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;
    private List<Product> products;
    private List<Integer> quantities;
    private double totalAmount;
    private String status; // "PROCESSING", "COMPLETED", "CANCELLED"
    private LocalDateTime orderDate;

    public Order() {
        this.orderId = UUID.randomUUID().toString().substring(0, 8);
        this.products = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = "PROCESSING";
    }

    public void addProduct(Product product, int quantity) {
        products.add(product);
        quantities.add(quantity);
        totalAmount += product.getPrice() * quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}