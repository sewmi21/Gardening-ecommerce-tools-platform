package Tests;
import model.Cart;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private Cart cart;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        product1 = new Product("gloves", 12.99, 50);
        product2 = new Product("Spade", 15.99, 30);
    }

    @Test
    public void testAddToCart() {
        cart.addToCart(product1, 2);
        assertFalse(cart.isEmpty());
        assertEquals(2, cart.getQuantity(product1));
        assertEquals(25.98, cart.getTotal(), 0.01);
    }

    @Test
    public void testAddToCartInsufficientStock() {
        cart.addToCart(product2, 40);  // Exceeds available stock
        assertTrue(cart.isEmpty());
    }

    @Test
    public void testShowCartWithItems() {
        cart.addToCart(product1, 1);
        cart.addToCart(product2, 2);
        cart.showCart();
        assertEquals(3, cart.getTotalItems());
    }

    @Test
    public void testShowCartEmpty() {
        assertTrue(cart.isEmpty());
        cart.showCart();
    }

    @Test
    public void testTotalCalculation() {
        cart.addToCart(product1, 2);
        cart.addToCart(product2, 1);
        assertEquals(41.97, cart.getTotal(), 0.01);
    }
}