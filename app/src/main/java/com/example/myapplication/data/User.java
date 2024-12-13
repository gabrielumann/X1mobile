package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class User {
    private int id;
    @SerializedName("first_name")
    private String firstName;
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }
}
