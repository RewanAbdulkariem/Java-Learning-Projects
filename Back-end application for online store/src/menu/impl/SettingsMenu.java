//SettingsMenu.java
package menu.impl;

import context.ApplicationContext;
import menu.Menu;
import model.User;

import java.util.Scanner;

public class SettingsMenu implements Menu {

    private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
            + "2. Change Email";

    private ApplicationContext context;
    private ChangePasswordMenu changePassword;
    private ChangeEmailMenu changeEmail;
    public SettingsMenu() {
        context = ApplicationContext.getInstance();
        changePassword = new ChangePasswordMenu();
        changeEmail = new ChangeEmailMenu();
    }

    @Override
    public void start() {
        User user = context.getLoggedInUser();
        if (user == null) {
            System.out.println("Please, log in or create new account to change your account settings");
            return;
        }

        Scanner sc = new Scanner(System.in);

        printMenuHeader();
        System.out.println(SETTINGS);

        String input = sc.next().trim();
        if (input.equalsIgnoreCase("menu")){
            return;
        }

        int option = Integer.parseInt(input);
        switch (option){
            case 1:
                changePassword.start();
                break;
            case 2:
                changeEmail.start();
                break;
            default:
                System.out.println("‘Only 1, 2 is allowed. Try one more time");
                start();
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("=== SETTINGS ===");
    }

}

