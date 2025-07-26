//Main.java
package app;

import menu.impl.MainMenu;
import menu.Menu;

public class Main {

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.start();
    }

}
