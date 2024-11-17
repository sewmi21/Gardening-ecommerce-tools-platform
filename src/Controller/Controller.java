package Controller;
import View.ConsoleView;
import model.Cart;
import model.OrderManager;
import model.Product;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Product> products = new ArrayList<>();
    private Cart cart = new Cart();
    private ConsoleView view = new ConsoleView();
    private OrderManager orderManager = new OrderManager();

    public Controller() {
        // Adding some sample products
        products.add(new Product("Shovel", 30, 50));
        products.add(new Product("Garden Fork", 20, 50));
        products.add(new Product("Gloves", 25, 50));
        products.add(new Product("Rake", 15, 30));
        products.add(new Product("Spade", 45, 30));
        products.add(new Product("Watering Can", 20, 100));
    }

    public void start() {
        view.displayWelcome();
        if (!login()) return;  // If login fails, exit the system

        boolean running = true;
        while (running) {
            int choice = view.promptMenuSelection();
            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> searchProduct();
                case 3 -> addToCart();
                case 4 -> cart.showCart();
                case 5 -> placeOrder();
                case 6 -> viewOrders();
                case 7 -> cancelOrder();
                case 0 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //Login
    private boolean login() {
        String username = view.promptLogin();
        String password = view.promptPassword();
        // hardcoded the username and password in this scope
        if ("customer".equals(username) && "password".equals(password)) {
            view.showLoginSuccess();
            return true;
        } else {
            view.showLoginFailure();
            return false;
        }
    }

    //View product details
    private void viewProducts() {
        System.out.println("Available Store Products:");
        //view each product in the stock
        for (Product product : products) {
            view.displayProduct(product);
        }
    }
    //Search products
    private void searchProduct() {
        String name = view.promptProductName();
        //search each product user adds to search
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                view.displayProduct(product);
                return;
            }
        }
        System.out.println("Product not found!");
    }
    //Add products to the cart
    private void addToCart() {
        String name = view.promptProductName();
        //add each product with quantity
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                int quantity = view.promptQuantity();
                cart.addToCart(product, quantity);
                return;
            }
        }
        System.out.println("Product not found!");
    }
    //place an order
    private void placeOrder() {
        //check if the cart is empty before proceeding with order
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Please add items to your cart before placing an order.");
        } else {
            cart.showCart();
            System.out.println("Thank you for your purchase! Your order has been placed!");
            orderManager.addOrder(cart);
            cart = new Cart();
        }
    }

    private void viewOrders() {
        view.displayOrders(orderManager.getOrders());
    }

    private void cancelOrder() {
        String orderId = view.promptOrderIdForCancellation();
        if (orderManager.cancelOrder(orderId)) {
            view.showOrderCancellationSuccess();
        } else {
            view.showOrderCancellationFailure();
        }
    }
}