//ChangePasswordMenu.java
package menu.impl;

import context.ApplicationContext;
import menu.Menu;
import model.User;

import java.util.Scanner;

public class ChangePasswordMenu implements Menu {

    private ApplicationContext context;
    public ChangePasswordMenu() {
        context = ApplicationContext.getInstance();
    }
    @Override
    public void start() {
        printMenuHeader();
        Scanner input = new Scanner(System.in);
        User user = context.getLoggedInUser();
        System.out.println("Enter a new password: ");
        String password = input.nextLine();
        user.setPassword(password);
        System.out.println("Your password has been successfully changed");
    }

    @Override
    public void printMenuHeader() {
        System.out.println("=== CHANGE PASSWORD ===");
    }
}
