//CheckoutMenu.java
package menu.impl;

import context.ApplicationContext;
import menu.Menu;
import model.Cart;

import model.Order;
import model.impl.DefaultOrder;
import service.OrderManagementService;
import service.impl.DefaultOrderManagementService;

import java.util.Scanner;

public class CheckoutMenu implements Menu {
    private ApplicationContext context;
    private OrderManagementService orderManagementService;
    private Cart cart;
    public CheckoutMenu(){
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
        cart = context.getSessionCart();
    }
    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        Order order = new DefaultOrder();
        boolean isCorrect;
        String input;
        printMenuHeader();
        do{
            System.out.println("Enter your credit card number without spaces and press enter if " +
                    "you confirm purchase");
            input = sc.nextLine().trim();
            isCorrect = order.isCreditCardNumberValid(input);
            if (!isCorrect){
                System.out.println("You entered invalid credit card number. Valid credit card should " +
                        "contain 16 digits. Please, try one more time");
            }
        } while (!isCorrect);

        order.setCustomerId(context.getLoggedInUser().getId());
        order.setProducts(cart.getProducts());
        order.setCreditCardNumber(input);
        orderManagementService.addOrder(order);

        System.out.println("Thanks a lot for your purchase. Details about order delivery are " +
                "sent to your email");

        cart.clear();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("=== CHECK OUT ===");
    }
}
