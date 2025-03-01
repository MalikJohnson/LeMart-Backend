package com.LeMart.repo;

import com.LeMart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId); // Find products by category
    List<Product> findByNameContainingIgnoreCase(String name); // Search products by name
}