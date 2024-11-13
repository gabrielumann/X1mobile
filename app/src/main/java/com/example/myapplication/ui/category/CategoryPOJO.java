package com.example.myapplication.ui.category;

public class CategoryPOJO {
    private String title;
    private int imageResId;

    public CategoryPOJO( String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}
