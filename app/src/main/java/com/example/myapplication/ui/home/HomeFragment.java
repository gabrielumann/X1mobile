package com.example.myapplication.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.Product;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getProducts().observe(getViewLifecycleOwner(), this::setRecyclerView);

        homeViewModel.getErrorMessage().observe(getViewLifecycleOwner(), this::showErrorMessage);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setRecyclerView(List<Product> products){
        RecyclerView recyclerView = binding.recyclerViewProducts;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        ProductAdapter adapter = new ProductAdapter(getContext(), products);
        recyclerView.setAdapter(adapter);
    }

    public void showErrorMessage(String errorMessage){
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG ).show();
    }

}