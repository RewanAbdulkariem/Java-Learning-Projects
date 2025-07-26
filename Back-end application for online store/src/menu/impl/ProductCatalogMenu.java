//ProductCatalogMenu.java

package menu.impl;

import context.ApplicationContext;
import menu.Menu;
import model.Cart;
import model.Product;
import service.ProductManagementService;
import service.impl.DefaultProductManagementService;

import java.util.Scanner;

public class ProductCatalogMenu implements Menu {
    private static final String MENU_COMMAND = "menu";
    private static final String CHECKOUT_COMMAND = "checkout";

    private ApplicationContext context;
    Cart cart;
    Product product;
    Menu checkOutMenu;
    ProductManagementService productService;
    public ProductCatalogMenu(){
        context = ApplicationContext.getInstance();
        productService = DefaultProductManagementService.getInstance();
        cart = context.getSessionCart();
        checkOutMenu = new CheckoutMenu();
    }
    @Override
    public void start() {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenuHeader();
            System.out.println("Enter product id to add it to the cart or ‘menu’ if you want to navigate " +
                    "back to the main menu");
            input = sc.nextLine().trim();

            if (input.equalsIgnoreCase(MENU_COMMAND)) {
                System.out.println("Returning to main menu...");
                break;
            } else if (input.equalsIgnoreCase(CHECKOUT_COMMAND)){
                if (cart.isEmpty()){
                    System.out.println("Your cart is empty. Please, add product to cart first and then " +
                            "proceed with checkout");
                    continue;
                }
                System.out.println("Go to Checkout");
                checkOutMenu.start();
                break;
            } else {
                if (context.getLoggedInUser() == null){
                    System.out.println("‘You are not logged in. Please, sign in or create new account");
                    break;
                }
                int productId = Integer.parseInt(input);

                product = productService.getProductById(productId);
                if (product == null) {
                    System.out.println("‘Please, enter product ID if you want to add product to cart. Or " +
                            "enter ‘checkout’ if you want to proceed with checkout. Or enter ‘menu’ if you want to " +
                            "navigate back to the main menu.");
                } else {
                    cart.addProduct(product);
                    System.out.println("Product " + product.getProductName() + " has been added to your cart. If you " +
                            "want to add a new product - enter the product id. If you want to proceed with checkout - " +
                            "enter word ‘checkout’ to console");
                }
            }

        }
    }
    @Override
    public void printMenuHeader() {
        Product[] products = productService.getProducts();
        System.out.println("=== PRODUCT CATALOG ===");
        for (Product product:products){
            System.out.println(product);
        }
    }

    public static void main(String[] args){
        ProductCatalogMenu productCatalogMenu = new ProductCatalogMenu();
        productCatalogMenu.start();

    }
}
