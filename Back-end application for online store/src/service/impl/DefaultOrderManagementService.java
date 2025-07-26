//DefaultOrderManagementService.java
package service.impl;

import model.Order;
import service.OrderManagementService;

import java.util.Arrays;

public class DefaultOrderManagementService implements OrderManagementService {
    private static final int DEFAULT_ORDER_CAPACITY = 10;
    private static DefaultOrderManagementService instance;

    private Order[] orders;
    private int orderIndex;

    private DefaultOrderManagementService() {
        orders = new Order[DEFAULT_ORDER_CAPACITY * 2];
        orderIndex = 0;
    }
    public static OrderManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderManagementService();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) {
        if (order == null) {
            return;
        }
        if (orderIndex < orders.length) {
            orders[orderIndex++] = order;
            System.out.println("Order added, total orders: " + orderIndex);
        } else {
            orders = Arrays.copyOf(orders, orders.length << 1);
        }
    }

    @Override
    public Order[] getOrdersByUserId(int userId) {
        int count = 0;
        for (int i = 0; i < orderIndex; i++) {
            if (orders[i].getCustomerId() == userId) {
                count++;
            }
        }

        Order[] userOrders = new Order[count];
        int j = 0;
        for (int i = 0; i < orderIndex; i++) {
            if (orders[i].getCustomerId() == userId) {
                userOrders[j++] = orders[i];
            }
        }
        return userOrders;
    }

    @Override
    public Order[] getOrders() {
        Order[] result = new Order[orderIndex];
        for (int i = 0; i < orderIndex; i++) {
            result[i] = orders[i];
        }
        return result;
    }

    void clearServiceState() {
        orders = new Order[DEFAULT_ORDER_CAPACITY * 2];
        orderIndex = 0;
    }
}
