package com.example.myapplication.data.base;
import com.example.myapplication.data.Product;
import com.example.myapplication.data.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "http://10.0.2.2/X1-Clothes/";

    String BASE_URL_API = "http://10.0.2.2/X1-Clothes/api/";
    @GET("products")
    Call<ApiResponse<List<Product>>> getProducts();

    @FormUrlEncoded
    @POST("users/login")
    Call<ApiResponse<User>> loginWithPost(
            @Field("email") String email,
            @Field("password") String password
    );
}