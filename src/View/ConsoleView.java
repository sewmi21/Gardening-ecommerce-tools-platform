package View;

import model.Product;

import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);

    public void displayWelcome() {
        System.out.println("****************************************\n Welcome to the Gardening E-Commerce Platform!\n ****************************************");
    }

    //take username input
    public String promptLogin() {
        System.out.print("Enter username: ");
        return scanner.nextLine();
    }

    //take password input
    public String promptPassword() {
        System.out.print("Enter password: ");
        return scanner.nextLine();
    }

    //Display successful message
    public void showLoginSuccess() {
        System.out.println("Successfully Logged in to the system!");
    }

    //Display login failure message
    public void showLoginFailure() {
        System.out.println("Login failed! Incorrect username or password.");
    }

    public int promptMenuSelection() {
        System.out.println("1. View Products\n2. Search Product\n3. Add to Cart\n4. View Cart\n5. Place Order\n0. Exit");
        System.out.print("Select an option: ");
        return scanner.nextInt();
    }

    //Prompt product name to take user inputs for product search
    public String promptProductName() {
        System.out.print("Enter product name: ");
        return scanner.next();
    }

    //Ask user to add product quantity
    public int promptQuantity() {
        System.out.print("Enter quantity: ");
        return scanner.nextInt();
    }

    public void displayProduct(Product product) {
        System.out.println("Product: " + product.getName() + ", Price: $" + product.getPrice() + ", Stock: " + product.getStock());
    }

    public void showTotal(double total) {
        System.out.println("Total Price: $" + total);
    }
}