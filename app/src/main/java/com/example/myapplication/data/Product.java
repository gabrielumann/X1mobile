package com.example.myapplication.data;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Product {
    private int id;
    private String name;
    @SerializedName("price_brl")
    private double price;
    private String color;
    private String category;
    private String brand;
    private String size;

    @SerializedName("product_image")
    private List<ProductImage> productImages;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getSize() {
        return size;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }
}
