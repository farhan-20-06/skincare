package com.skincare.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a skincare product
 * Demonstrates encapsulation, equals/hashCode implementation
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String brand;
    private String category;
    
    // Default constructor
    public Product() {}
    
    // Parameterized constructor
    public Product(String id, String name, String brand, String category) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
    }
    
    // Constructor without brand and category (optional fields)
    public Product(String id, String name) {
        this(id, name, "", "");
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    // Business logic methods
    public boolean hasValidData() {
        return id != null && !id.trim().isEmpty() && 
               name != null && !name.trim().isEmpty();
    }
    
    public String getDisplayName() {
        StringBuilder display = new StringBuilder(name);
        if (brand != null && !brand.trim().isEmpty()) {
            display.append(" by ").append(brand);
        }
        if (category != null && !category.trim().isEmpty()) {
            display.append(" (").append(category).append(")");
        }
        return display.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(id, product.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
