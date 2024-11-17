package Controller;
import View.ConsoleView;
import model.Cart;
import model.OrderManager;
import model.Product;
import model.ProductCategory;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Product> products = new ArrayList<>();
    private List<ProductCategory> productCategories = new ArrayList<>();
    private Cart cart = new Cart();
    private ConsoleView view = new ConsoleView();
    private OrderManager orderManager = new OrderManager();

    public Controller() {
        // Defining Product Categories
        ProductCategory handTools = new ProductCategory(1, "Hand Tools", "Small-scale gardening tasks such as digging, planting, and trimming", 0); 
        ProductCategory diggingTools = new ProductCategory(2, "Digging Tools", "For digging, lifting, and turning soil", 0); 
        ProductCategory wateringTools = new ProductCategory(3, "Watering Tools", "Wide range of watering tools to hydrate plants", 0); 
        ProductCategory protectiveGear = new ProductCategory(4, "Protective Gear", "Gears to protect you while working in the garden, ensuring comfort and safety.", 0); 
        ProductCategory miscellaneous = new ProductCategory(5, "Miscellaneous", "Other gardening tools", 0); 

        // Adding Product Categories
        productCategories.add(handTools); 
        productCategories.add(diggingTools); 
        productCategories.add(wateringTools); 
        productCategories.add(protectiveGear); 
        productCategories.add(miscellaneous);
        
        // Adding some sample products
        products.add(new Product("Shovel", 30, 50, diggingTools));
        products.add(new Product("Garden Fork", 20, 50, diggingTools));
        products.add(new Product("Gloves", 25, 50, protectiveGear));
        products.add(new Product("Rake", 15, 30, miscellaneous));
        products.add(new Product("Spade", 45, 30, diggingTools));
        products.add(new Product("Watering Can", 20, 100, wateringTools));

        // Update product counts
        updateProductCounts();
    }

    public void start() {
        view.displayWelcome();
        if (!login()) return;  // If login fails, exit the system

        boolean running = true;
        while (running) {
            int choice = view.promptMenuSelection();
            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> viewProductCategories();
                case 3 -> searchProduct();
                case 4 -> searchCategory();
                case 5 -> addToCart();
                case 6 -> cart.showCart();
                case 7 -> placeOrder();
                case 8 -> viewOrders();
                case 9 -> cancelOrder();
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

    //Update Product Count for Product Category
    private void updateProductCounts() { 
        for (ProductCategory category : productCategories) { 
            int count = (int) products.stream().filter(p -> p.getCategory().equals(category)).count(); 
            category.setProductCount(count); 
        }
    }   
    //View product categories
    private void viewProductCategories() {
        System.out.println("\nAvailable Product Categories:");
        //view each product in the stock
        for (ProductCategory category : productCategories) {
            view.displayProductCategory(category);
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

    //Search Product Category
    private void searchCategory() {
        String name = view.promptCategoryName();
        for (ProductCategory category : productCategories) {
            if (category.getName().equalsIgnoreCase(name)) {
                view.displayProductCategory(category);
                return;
            }     
        }
        System.out.println("Category not found!");
        if (products.isEmpty()) { System.out.println("No products found in this category."); }
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