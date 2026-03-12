package com.example.e_commerce.controller;

import com.example.e_commerce.dto.ProductResponse;
import com.example.e_commerce.entity.Category;
import com.example.e_commerce.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create category
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Category createCategory(@RequestBody String name) {
        return categoryService.createCategory(name);
    }

    // Add subcategory
    @PostMapping("/add/{parentId}/subcategory")
    @PreAuthorize("hasRole('ADMIN')")
    public Category addSubCategory(@PathVariable Long parentId,
                                   @RequestBody String name) {
        return categoryService.addSubCategory(parentId, name);
    }

    // Get category tree
    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    // Get products in category
    @GetMapping("/{categoryId}/products")
    public List<ProductResponse> getProductsByCategory(@PathVariable Long categoryId) {
        return categoryService.getProductsByCategory(categoryId);
    }
}