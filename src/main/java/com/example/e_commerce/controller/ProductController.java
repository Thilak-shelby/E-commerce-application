package com.example.e_commerce.controller;

import com.example.e_commerce.dto.ProductResponse;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.e_commerce.dto.ProductRequest;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{productId}/category/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product assignCategory(
            @PathVariable Long productId,
            @PathVariable Long categoryId) {

        return productService.assignCategory(productId, categoryId);
    }

    // Delete product
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}