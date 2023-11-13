package be.intecbrussel.productmanagementapp.controllers;

import be.intecbrussel.productmanagementapp.models.Product;
import be.intecbrussel.productmanagementapp.services.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    // localhost:8080/product/add
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product = productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // localhost:8080/product/get/id
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        Product foundProduct = productService.getProduct(id);
        return ResponseEntity.ok(foundProduct);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> foundProducts = productService.getAllProducts();
        return ResponseEntity.ok(foundProducts);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") long id) {
        Product updatedProduct = productService.updateProduct(product, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        // changed value to void. so that we don't get an error on clientside
//        return ResponseEntity.ok("Product is successfully deleted with id: " + id);
    }

    @PatchMapping("/is-available/{id}")
    public ResponseEntity<Product> productIsAvailable(@PathVariable("id") long id) {
        Product updatedProduct = productService.isAvailable(id);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/is-not-available/{id}")
    public ResponseEntity<Product> productIsNotAvailable(@PathVariable("id") long id) {
        Product updatedProduct = productService.isNotAvailable(id);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {
        return ResponseEntity.ok(productService.searchProducts(query));
    }

}
