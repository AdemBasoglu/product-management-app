package be.intecbrussel.productmanagementapp.services.impl;

import be.intecbrussel.productmanagementapp.exception.ProductException;
import be.intecbrussel.productmanagementapp.models.Product;
import be.intecbrussel.productmanagementapp.repositories.ProductRepository;
import be.intecbrussel.productmanagementapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        if (product == null) {
            throw new ProductException("Product can't be null!");
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with id: " + id));
        return foundProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product, long id) {
        return null;
    }

    @Override
    public void deleteProduct(long id) {

    }

    @Override
    public Product isAvailable(long id) {
        return null;
    }

    @Override
    public Product isNotAvailable(long id) {
        return null;
    }

    @Override
    public List<Product> seachProducts(String query) {
        return null;
    }


}
