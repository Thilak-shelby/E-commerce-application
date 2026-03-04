package com.example.e_commerce.controller;

import com.example.e_commerce.dto.ProductResponse;
import com.example.e_commerce.entity.Category;
import com.example.e_commerce.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // Dependency Injection
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create a category
    // POST /categories?name=Electronics
    @PostMapping
    public Category createCategory(@RequestParam String name) {
        return categoryService.createCategory(name);
    }

    @PostMapping("/{parentId}/subcategories")
    public Category addSubCategory(
            @PathVariable Long parentId,
            @RequestParam String name) {

        return categoryService.addSubCategory(parentId, name);
    }

    // Get all categories
    // GET /categories
    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    // Get all products belonging to a category
    // GET /categories/1/products
    @GetMapping("/{id}/products")
    public List<ProductResponse> getProductsByCategory(
            @PathVariable Long id) {

        return categoryService.getProductsByCategory(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}