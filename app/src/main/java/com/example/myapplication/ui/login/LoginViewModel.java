package com.example.myapplication.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.User;
import com.example.myapplication.data.base.ApiResponse;
import com.example.myapplication.data.base.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<User> user = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public LiveData<User> getUserLiveData() {
        return user;
    }
    public LiveData<String> getErrorMessage() {
        return error;
    }

    public void login(String email, String password) {
        Call<ApiResponse<User>> call = RetrofitClient.getInstance().getMyApi().loginWithPost(email, password);

        call.enqueue(new Callback<ApiResponse<User>>() {
            @Override
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                ApiResponse<User> apiResponse = response.body();
                if (response.isSuccessful() && apiResponse != null) {
//                    Log.e("TEste", "teste no if");
                    user.postValue(apiResponse.getData());
                } else {
                    error.postValue("Senha ou Email Inválidos!");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                error.postValue("Falha na requisição: " + t.getMessage());
                Log.e("Error", t.getMessage(), t);
            }
        });
    }
}