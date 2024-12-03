package com.example.myapplication.data.base;
import com.example.myapplication.data.Product;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://10.0.2.2/X1-Clothes/";

    String BASE_URL_API = "http://10.0.2.2/X1-Clothes/api/";
    @GET("products")
    Call<ApiResponse<List<Product>>> getProducts();
}