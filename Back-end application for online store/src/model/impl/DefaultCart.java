//DefaultCart.java
package model.impl;

import model.Cart;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class DefaultCart implements Cart {
    private List<Product> products = new ArrayList<>();
    public DefaultCart(){
    }
    @Override
    public boolean isEmpty() {
        if (products == null || products.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void addProduct(Product productById) {
        if (productById == null) {
            System.out.println("Cannot add null product to cart.");
            return;
        }
        products.add(productById);
    }

    @Override
    public Product[] getProducts() {
        if (products == null)
            return null;
        return products.toArray(new Product[0]);
    }

    @Override
    public void clear() {
        products.clear();
    }
}
