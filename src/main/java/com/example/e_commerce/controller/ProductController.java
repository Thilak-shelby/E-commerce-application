package com.example.e_commerce.controller;

import com.example.e_commerce.dto.ProductResponse;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.service.ProductService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.e_commerce.dto.ProductRequest;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{productId}/category/{categoryId}")
    public Product assignCategory(
            @PathVariable Long productId,
            @PathVariable Long categoryId) {

        return productService.assignCategory(productId, categoryId);
    }
}