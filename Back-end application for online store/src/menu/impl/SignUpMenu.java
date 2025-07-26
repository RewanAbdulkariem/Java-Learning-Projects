//SignUpMenu.java
package menu.impl;

import context.ApplicationContext;
import menu.Menu;
import model.impl.DefaultUser;
import model.User;
import service.UserManagementService;
import service.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignUpMenu implements Menu {
    private ApplicationContext context;
    private UserManagementService userService;
    User user;

    public SignUpMenu() {
        this.context = ApplicationContext.getInstance();
        this.userService = DefaultUserManagementService.getInstance();

    }

    @Override
    public void start() {
        printMenuHeader();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        System.out.print("Enter your email: ");
        sc = new Scanner(System.in);
        String email = sc.nextLine();

        user = new DefaultUser(firstName, lastName, email, password);
        String errorMessage = userService.registerUser(user);
        if (errorMessage == null || errorMessage.isEmpty()) {
            context.setLoggedInUser(user);
            System.out.println("New user is created");
        } else {
            System.out.println(errorMessage);
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("=== SIGN UP ===");
    }
}
