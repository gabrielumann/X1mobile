package com.example.myapplication.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.Product;
import com.example.myapplication.data.base.ApiResponse;
import com.example.myapplication.data.base.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public HomeViewModel() {
        fetchProducts();
    }
    public LiveData<List<Product>> getProducts() {
        return products;
    }
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
    private void fetchProducts() {
        Call<ApiResponse<List<Product>>> call = RetrofitClient.getInstance().getMyApi().getProducts();
        call.enqueue(new Callback<ApiResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Product>>> call, Response<ApiResponse<List<Product>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.postValue(response.body().getData());
                } else {
                    if(response.code() == 404){
                        errorMessage.postValue("Erro: Nenhum Produto Foi Encontrado!");
                    }else {
                        errorMessage.postValue("Erro: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse<List<Product>>> call, @NonNull Throwable t) {
                errorMessage.postValue("Falha na requisição: " + t.getMessage());
                Log.e("Error", t.getMessage(), t);
            }
        });
    }
}

