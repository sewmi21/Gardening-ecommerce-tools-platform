package model;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();
    private double total;

    // Add products to cart
    public void addToCart(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            items.put(product, items.getOrDefault(product, 0) + quantity);
            total += product.getPrice() * quantity;
            product.reduceStock(quantity);
        } else {
            System.out.println("Insufficient stock for " + product.getName());
        }
    }

    // Get total price of the cart
    public double getTotal() {

        return total;
    }

    // Display cart information
    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in Cart:");
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(
                        "- " + product.getName() + " | Price: $" + product.getPrice() + " | Quantity: " + quantity);
            }
            System.out.println("Total Price: $" + getTotal());
        }
    }

    public boolean isEmpty() {

        return items.isEmpty();
    }

    // get items availability
    public int getQuantity(Product product1) {
        return items.getOrDefault(product1, 0);
    }

    // Get total number of items
    public int getTotalItems() {
        int totalItems = 0;
        for (int quantity : items.values()) {
            totalItems += quantity;
        }
        return totalItems;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(items.keySet());
    }

    // Get list of quantities corresponding to products
    public List<Integer> getQuantities() {
        return new ArrayList<>(items.values());
    }

    // Remove specific quantity of a product from cart
    public void removeFromCart(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQty = items.get(product);
            if (currentQty <= quantity) {
                // Remove the product entirely
                total -= product.getPrice() * currentQty;
                items.remove(product);
                product.addStock(currentQty); // Return stock
            } else {
                // Reduce the quantity
                items.put(product, currentQty - quantity);
                total -= product.getPrice() * quantity;
                product.addStock(quantity); // Return stock
            }
        }
    }

    // Clear all items from cart
    public void clearCart() {
        // Return all items to stock
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.addStock(quantity);
        }
        items.clear();
        total = 0;
    }

    // Update quantity of a product in cart
    public void updateQuantity(Product product, int newQuantity) {
        if (items.containsKey(product)) {
            int currentQty = items.get(product);
            if (newQuantity <= 0) {
                // Remove the product if quantity is 0 or negative
                removeFromCart(product, currentQty);
            } else if (product.getStock() + currentQty >= newQuantity) {
                // First return current quantity to stock
                product.addStock(currentQty);
                // Then reduce stock by new quantity
                product.reduceStock(newQuantity);
                // Update total price
                total -= product.getPrice() * currentQty;
                total += product.getPrice() * newQuantity;
                // Update quantity
                items.put(product, newQuantity);
            } else {
                System.out.println("Insufficient stock for " + product.getName());
            }
        }
    }

    // Check if a product exists in cart
    public boolean containsProduct(Product product) {
        return items.containsKey(product);
    }

    // Get cart summary as a formatted string
    public String getCartSummary() {
        if (items.isEmpty()) {
            return "Cart is empty";
        }

        StringBuilder summary = new StringBuilder("Cart Summary:\n");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            summary.append(String.format("- %s x%d ($%.2f each, total: $%.2f)\n",
                    product.getName(),
                    quantity,
                    product.getPrice(),
                    product.getPrice() * quantity));
        }
        summary.append(String.format("\nTotal Items: %d\n", getTotalItems()));
        summary.append(String.format("Total Price: $%.2f", getTotal()));
        return summary.toString();
    }

}