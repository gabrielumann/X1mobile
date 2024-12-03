package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class ProductImage {
    private int id;
    private String image;
    private String type;
    @SerializedName("product_id")
    private int productId;
    @SerializedName("complementary_order")
    private Integer complementaryOrder;

    public int getId() {
        return id;
    }
    public String getImage() {
        return image;
    }
    public String getType() {
        return type;
    }
    public int getProductId() {
        return productId;
    }
    public Integer getComplementaryOrder() {
        return complementaryOrder;
    }
}
