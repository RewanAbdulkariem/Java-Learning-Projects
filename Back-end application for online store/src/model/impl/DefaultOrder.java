//DefaultOrder.java
package model.impl;

import model.Order;
import model.Product;

public class DefaultOrder implements Order {

    private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

    private String creditCardNumber;
    private Product[] products;
    private int customerId;

    @Override
    public boolean isCreditCardNumberValid(String creditCardNumber) {
        if (creditCardNumber == null || !creditCardNumber.matches("^\\d{16}$")) {
            return false;
        }
        return true;
    }

    @Override
    public void setCreditCardNumber(String creditCardNumber) {
        if (creditCardNumber == null) {
            return;
        }
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public int getCustomerId() {
        return this.customerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order for customer ID: ").append(customerId).append("\n");
        sb.append("Products:\n");
        if (products != null && products.length > 0) {
            for (Product product : products) {
                sb.append(" - ").append(product.getProductName()).append("\n");
            }
        } else {
            sb.append("No products\n");
        }
        return sb.toString();
    }
}
