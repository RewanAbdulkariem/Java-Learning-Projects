package service;

import model.Order;

public interface OrderManagementService{
    void addOrder(Order order);
    Order[] getOrdersByUserId(int userId);
    Order[] getOrders();
}
