//SignOutMenu.java
package menu.impl;

import context.ApplicationContext;
import menu.Menu;

public class SignOutMenu implements Menu {
    private ApplicationContext context;

    public SignOutMenu() {
        this.context = ApplicationContext.getInstance();
    }
    @Override
    public void start() {
        printMenuHeader();
        context.setLoggedInUser(null);
        System.out.println("Press Enter to return to main menu...");
        new java.util.Scanner(System.in).nextLine();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("=== SIGN OUT ===");
        System.out.println("Have a nice day! Look forward to welcoming back!");
    }
}
