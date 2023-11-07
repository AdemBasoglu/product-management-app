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

        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with id: " + id));

        foundProduct.setName(product.getName());
        foundProduct.setDescription(product.getDescription());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setAvailable(product.isAvailable());

        return productRepository.save(foundProduct);
    }

    @Override
    public void deleteProduct(long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with id: " + id));

        productRepository.deleteById(id);
    }

    @Override
    public Product isAvailable(long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with id: " + id));

        foundProduct.setAvailable(Boolean.TRUE);

        return productRepository.save(foundProduct);
    }

    @Override
    public Product isNotAvailable(long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with id: " + id));

        foundProduct.setAvailable(Boolean.FALSE);

        return productRepository.save(foundProduct);
    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.searchProducts(query);
    }

}
