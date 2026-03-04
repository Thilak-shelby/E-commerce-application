package com.example.e_commerce.service;

import com.example.e_commerce.dto.ProductRequest;
import com.example.e_commerce.entity.Category;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.repository.ProductRepository;
import com.example.e_commerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.example.e_commerce.dto.ProductResponse;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product ->
                        new ProductResponse(
                                product.getId(),
                                product.getName(),
                                product.getPrice(),
                                product.getCategory() != null
                                        ? product.getCategory().getName()
                                        : null
                        ))
                .toList();
    }
    public Product createProduct(ProductRequest request) {

        Product product = new Product(
                request.getName(),
                request.getPrice()
        );

        return productRepository.save(product);
    }

    public Product assignCategory(Long productId, Long categoryId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);

        return productRepository.save(product);
    }
}