//CustomerListMenu.java

package menu.impl;

import context.ApplicationContext;
import menu.Menu;
import model.User;
import service.UserManagementService;
import service.impl.DefaultUserManagementService;

public class CustomerListMenu implements Menu {
    private ApplicationContext context;
    private UserManagementService userManagementService;

    public CustomerListMenu() {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }
    @Override
    public void start() {
        printMenuHeader();
        User[] users = userManagementService.getUsers();
        for (User user: users){
            System.out.println(user);
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("=== Customer List ===");

    }
}
