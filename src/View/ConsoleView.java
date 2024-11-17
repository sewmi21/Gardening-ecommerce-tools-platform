package View;

import model.Order;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);

    public void displayWelcome() {
        System.out.println(
                "****************************************\n Welcome to the Gardening E-Commerce Platform!\n ****************************************");
    }

    // take username input
    public String promptLogin() {
        System.out.print("Enter username: ");
        return scanner.nextLine();
    }

    // take password input
    public String promptPassword() {
        System.out.print("Enter password: ");
        return scanner.nextLine();
    }

    // Display successful message
    public void showLoginSuccess() {
        System.out.println("Successfully Logged in to the system!");
    }

    // Display login failure message
    public void showLoginFailure() {
        System.out.println("Login failed! Incorrect username or password.");
    }

    public int promptMenuSelection() {
        System.out
                .println(
                        "1. View Products\n2. Search Product\n3. Add to Cart\n4. View Cart\n5. Place Order\n6. View Orders\n7. Cancel Order\n"
                                + //
                                "0. Exit");
        System.out.print("Select an option: ");
        return scanner.nextInt();
    }

    // Prompt product name to take user inputs for product search
    public String promptProductName() {
        System.out.print("Enter product name: ");
        return scanner.next();
    }

    // Ask user to add product quantity
    public int promptQuantity() {
        System.out.print("Enter quantity: ");
        return scanner.nextInt();
    }

    public void displayProduct(Product product) {
        System.out.println(
                "Product: " + product.getName() + ", Price: $" + product.getPrice() + ", Stock: " + product.getStock());
    }

    public void showTotal(double total) {
        System.out.println("Total Price: $" + total);
    }

    public void displayOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("\n=== Your Orders ===");
        for (Order order : orders) {
            System.out.println("\nOrder ID: " + order.getOrderId());
            System.out.println("Date: " + order.getOrderDate());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Products:");

            List<Product> products = order.getProducts();
            List<Integer> quantities = order.getQuantities();

            for (int i = 0; i < products.size(); i++) {
                System.out.printf("- %s (Quantity: %d) - $%.2f\n",
                        products.get(i).getName(),
                        quantities.get(i),
                        products.get(i).getPrice() * quantities.get(i));
            }
            System.out.printf("Total Amount: $%.2f\n", order.getTotalAmount());
        }
    }

    public String promptOrderIdForCancellation() {
        System.out.print("Enter the Order id to cancel: ");
        String s = scanner.next();
        return s;
    }

    public void showOrderCancellationSuccess() {
        System.out.println("Order cancelled Successfully!");
    }

    public void showOrderCancellationFailure() {
        System.out.println("Failed to cancel.Order may not exist or is not in processing status.");
    }
}