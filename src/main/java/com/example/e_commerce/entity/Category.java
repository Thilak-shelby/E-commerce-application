package com.example.e_commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    // ---------------- PRIMARY KEY ----------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---------------- BASIC FIELD ----------------
    @Column(nullable = false)
    private String name;

    // ---------------- PRODUCTS IN CATEGORY ----------------
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    // ---------------- PARENT CATEGORY ----------------
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore   // prevents recursive JSON loop
    private Category parentCategory;

    // ---------------- SUBCATEGORIES ----------------
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subCategories = new ArrayList<>();

    // Constructor
    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    // ---------------- GETTERS ----------------
    public Long getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public List<Product> getProducts() {

        return products;
    }

    public Category getParentCategory() {

        return parentCategory;
    }

    public List<Category> getSubCategories() {

        return subCategories;
    }

    // ---------------- SETTERS ----------------
    public void setName(String name) {

        this.name = name;
    }

    public void setProducts(List<Product> products) {

        this.products = products;
    }

    public void setParentCategory(Category parentCategory) {

        this.parentCategory = parentCategory;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    // ---------------- HELPER METHOD ----------------
    public void addSubCategory(Category subCategory) {
        subCategory.setParentCategory(this);
        this.subCategories.add(subCategory);
    }
}