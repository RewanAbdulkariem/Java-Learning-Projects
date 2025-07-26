//ChangeEmailMenu.java
package menu.impl;

import context.ApplicationContext;
import menu.Menu;
import model.User;

import java.util.Scanner;

public class ChangeEmailMenu implements Menu {

    private ApplicationContext context;
    public ChangeEmailMenu() {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner input = new Scanner(System.in);
        User user = context.getLoggedInUser();
        System.out.println("Enter a new email: ");
        String email = input.nextLine();
        user.setEmail(email);
        System.out.println("Your email has been successfully changed");
    }

    @Override
    public void printMenuHeader() {
        System.out.println("=== CHANGE EMAIL ===");
    }

}
