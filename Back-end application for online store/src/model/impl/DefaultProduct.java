//DefaultProduct.java
package model.impl;

import model.Product;

public class DefaultProduct implements Product {
    private int id;
    private String productName;
    private String categoryName;
    private double price;

    public DefaultProduct(int id, String productName, String categoryName, double price){
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
        this.price = price;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return id + ". " + productName +
                " - Category: " + categoryName +
                " - Price: $" + String.format("%.2f", price);
    }
}
