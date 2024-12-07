package com.example.myapplication.model.cart;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.data.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<Product> cartItems;

    private Cart() {
        cartItems = new ArrayList<>();

    }

    public static Cart getInstance(){
        if(instance == null){
            instance = new Cart();
        }
        return instance;
    }

    public void addToCart(Product product){
        cartItems.add(product);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public List<Product> getCartItems() {
        return cartItems;
    }
}

