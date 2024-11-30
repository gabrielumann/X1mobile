package com.example.myapplication.ui.cart;

public class CartPOJO{
    private String productName;
    private String price;
    private int imageResource;

    public CartPOJO(String productName, String price, int imageResource) {
        this.productName = productName;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }
}

