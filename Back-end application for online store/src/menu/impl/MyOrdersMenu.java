//MyOrdersMenu.java
package menu.impl;

import context.ApplicationContext;
import menu.Menu;
import model.Cart;
import model.Order;
import model.Product;
import service.OrderManagementService;
import service.impl.DefaultOrderManagementService;

public class MyOrdersMenu implements Menu {
    private ApplicationContext context;
    private OrderManagementService orderManagementService;
    private Cart cart;
    public MyOrdersMenu() {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
        cart = context.getSessionCart();
    }


    @Override
    public void start() {
        printMenuHeader();

        if (context.getLoggedInUser() == null) {
            System.out.println("Please, log in or create new account to see list of your orders");
            return;
        }

        var userOrders = orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());

        if (userOrders.length == 0) {
            System.out.println("Unfortunately, you don’t have any orders yet. Navigate back to main menu to place a new order");
            return;
        }
        for (Order order : userOrders) {
            System.out.println(order);
            System.out.println("-------------------------");
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("=== MY ORDERS ===");

    }
}
