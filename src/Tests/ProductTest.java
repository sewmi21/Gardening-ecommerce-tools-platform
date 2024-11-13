package Tests;

import model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductCreation() {
        Product product = new Product("Garden Shovel", 12.99, 50);
        assertEquals("Garden Shovel", product.getName());
        assertEquals(12.99, product.getPrice());
        assertEquals(50, product.getStock());
    }

    @Test
    public void testReduceStock() {
        Product product = new Product("Garden Shovel", 12.99, 50);
        product.reduceStock(10);
        assertEquals(40, product.getStock());
    }

    @Test
    public void testReduceStockInsufficientQuantity() {
        Product product = new Product("Garden Shovel", 12.99, 5);
        product.reduceStock(10);//attempt to reduce stock more than available
        assertTrue(product.getStock() >= 0, "Stock cannot be reduced below zero.");
    }

}