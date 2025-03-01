package com.LeMart.repo;

import com.LeMart.model.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name); // Find category by name
}