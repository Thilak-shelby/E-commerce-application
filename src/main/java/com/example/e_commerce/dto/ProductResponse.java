package com.example.e_commerce.dto;

public class ProductResponse {

    private final Long id;
    private final String name;
    private final double price;
    private final String category;

    public ProductResponse(Long id,
                           String name,
                           double price,
                           String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}