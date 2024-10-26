package org.example.lab4.services;

import org.example.lab4.entities.Category;
import org.example.lab4.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Long id, Category newCategory) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

        existingCategory.setName(newCategory.getName());

        categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}