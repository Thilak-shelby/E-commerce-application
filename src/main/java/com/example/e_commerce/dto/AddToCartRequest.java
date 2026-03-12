package com.example.e_commerce.dto;

public class AddToCartRequest {

    private Long productId;
    private int quantity;

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}