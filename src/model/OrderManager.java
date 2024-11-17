package model;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Cart cart) {
        Order order = new Order();
        for (int i = 0; i < cart.getProducts().size(); i++) {
            order.addProduct(cart.getProducts().get(i), cart.getQuantities().get(i));
        }
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public boolean cancelOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId) && order.getStatus().equals("PROCESSING")) {
                order.setStatus("CANCELLED");
                return true;
            }
        }
        return false;
    }
}