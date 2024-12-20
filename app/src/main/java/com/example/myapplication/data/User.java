package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private String email;
    private String password;
    private String image;
    private String cpf;

    public User(int id, String cpf, String image, String password, String lastName, String email, String firstName) {
        this.id = id;
        this.cpf = cpf;
        this.image = image;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.firstName = firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
}
