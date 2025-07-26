package service;

import model.Product;

public interface ProductManagementService {
    Product[] getProducts();
    Product getProductById(int productIdToAddToCart);
}