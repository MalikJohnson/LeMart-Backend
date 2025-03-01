package com.LeMart.service;

import com.LeMart.exception.CategoryNotFoundException;
import com.LeMart.model.Category;
import com.LeMart.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    public Category updateCategory(Category category) {
        return categoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    public Category findCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " does not exist"));
    }

    public Category findCategoryByName(String name) {
        return categoryRepo.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + name + " does not exist"));
    }
}