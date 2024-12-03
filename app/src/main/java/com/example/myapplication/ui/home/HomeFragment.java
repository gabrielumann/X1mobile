package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.Product;
import com.example.myapplication.data.base.ApiResponse;
import com.example.myapplication.data.base.RetrofitClient;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fetchProducts();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void fetchProducts() {
        Call<ApiResponse<List<Product>>> call = RetrofitClient.getInstance().getMyApi().getProducts();

        call.enqueue(new Callback<ApiResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Product>>> call, Response<ApiResponse<List<Product>>> response) {
                ApiResponse<List<Product>> apiResponse = response.body();
                List<Product> productList = apiResponse.getData();

                if (productList != null) {
                    RecyclerView recyclerView = binding.recyclerViewProducts;
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    ProductAdapter adapter = new ProductAdapter(getContext(), productList);
                    recyclerView.setAdapter(adapter);

                } else {

                    Toast.makeText(getContext(), "Nenhum produto encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse<List<Product>>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Falha na requisição", Toast.LENGTH_LONG).show();
                Log.e("Error", t.getMessage(), t);
            }
        });
    }

}