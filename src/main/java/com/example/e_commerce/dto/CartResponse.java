package com.example.e_commerce.dto;

import java.util.List;

public class CartResponse {

    private List<CartItemResponse> items;
    private double total;

    public CartResponse(List<CartItemResponse> items, double total) {
        this.items = items;
        this.total = total;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }
}