package com.LeMart.service;

import com.LeMart.exception.ProductNotFoundException;
import com.LeMart.model.Product;
import com.LeMart.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public Product findProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " does not exist"));
    }

    public List<Product> findProductsByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }

    public List<Product> searchProductsByName(String name) {
        return productRepo.findByNameContainingIgnoreCase(name);
    }
}