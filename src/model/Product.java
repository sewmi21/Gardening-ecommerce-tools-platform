package model;

public class Product {
    private String name;
    private double price;
    private int stock;
    private ProductCategory productCategory;

    public Product( String name, double price,int stock, ProductCategory productCategory) {

        this.name = name;
        this.price = price;
        this.stock = stock;
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int quantity) {
        if (this.stock > quantity)
            this.stock -= quantity;
    }

    public void addStock(int quantity) {
        if (quantity > 0) {
            this.stock += quantity;
        }
    }

    public void setCategory(ProductCategory productCategory) { 
        this.productCategory = productCategory; 
    }

    public ProductCategory getCategory() { 
        return productCategory; 
    }

    @Override public String toString() { 
        return "Product: " + name + ", Price: $" + price + ", Stock: " + stock + ", Category: " + productCategory; 
    }
}
