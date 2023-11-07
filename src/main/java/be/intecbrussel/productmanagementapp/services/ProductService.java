package be.intecbrussel.productmanagementapp.services;

import be.intecbrussel.productmanagementapp.models.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);
    Product getProduct(long id);
    List<Product> getAllProducts();
    Product updateProduct(Product product, long id);
    void deleteProduct(long id);
    Product isAvailable(long id);
    Product isNotAvailable(long id);
    List<Product> searchProducts(String query);

}
