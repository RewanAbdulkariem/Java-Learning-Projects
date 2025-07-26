//SignInMenu.java
package menu.impl;

import context.ApplicationContext;
import menu.Menu;
import model.User;
import service.UserManagementService;
import service.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignInMenu implements Menu {
    private ApplicationContext context;
    UserManagementService userManagementService;


    public SignInMenu(){
        this.context = ApplicationContext.getInstance();
        this.userManagementService = DefaultUserManagementService.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        User user = userManagementService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)){
            context.setLoggedInUser(user);
            System.out.println("Glad to see you back " + user.getFirstName() + " "+ user.getLastName());
        } else {
            System.out.println("Unfortunately, such login and password doesn’t exist");
            System.out.println("Redirecting to main menu...");
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("=== SIGN IN ===");

    }
}
