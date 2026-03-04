package com.example.e_commerce.service;

import com.example.e_commerce.dto.ProductResponse;
import com.example.e_commerce.entity.Category;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Dependency Injection
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Create category
    public Category createCategory(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    public Category addSubCategory(Long parentId, String name) {

        Category parent = categoryRepository.findById(parentId)
                .orElseThrow(() ->
                        new RuntimeException("Parent category not found"));

        Category subCategory = new Category(name);

        subCategory.setParentCategory(parent);

        return categoryRepository.save(subCategory);
    }

    // Fetch all categories
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    // Fetch products belonging to a category
    public List<ProductResponse> getProductsByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        // Hibernate loads products automatically here
        return category.getProducts()
                .stream()
                .map(product -> mapToResponse(product))
                .toList();
    }

    // Helper mapper method
    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory() != null
                        ? product.getCategory().getName()
                        : null
        );
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}