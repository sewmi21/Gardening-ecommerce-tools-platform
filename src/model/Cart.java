package model;
import model.Product;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();
    private double total;

    //Add products to cart
    public void addToCart(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            items.put(product, items.getOrDefault(product, 0) + quantity);
            total += product.getPrice() * quantity;
            product.reduceStock(quantity);
        } else {
            System.out.println("Insufficient stock for " + product.getName());
        }
    }

    //Get total price of the cart
    public double getTotal() {

        return total;
    }

    //Display cart information
    public void showCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in Cart:");
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("- " + product.getName() + " | Price: $" + product.getPrice() + " | Quantity: " + quantity);
            }
            System.out.println("Total Price: $" + getTotal());
        }
    }

    public boolean isEmpty() {

        return items.isEmpty();
    }
    //get items availability
    public int getQuantity(Product product1) {
        return items.getOrDefault(product1, 0);
    }

    //Get total number of items
    public int getTotalItems() {
        int totalItems = 0;
        for (int quantity : items.values()) {
            totalItems += quantity;
        }
        return totalItems;
    }

}