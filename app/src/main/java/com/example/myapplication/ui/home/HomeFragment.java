package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerViewProducts;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<String> productList = new ArrayList<>();
        productList.add("CORTEIZ - Jaqueta Jeans Trucker C-Starz ");
        productList.add("Abobora - Jaqueta Jeans Trucker C-Starz ");
        productList.add("CORTEIZ - Jaqueta Jeans Trucker C-Starz ");
        productList.add("Abobora - Jaqueta Jeans Trucker C-Starz ");
        productList.add("CORTEIZ - Jaqueta Jeans Trucker C-Starz ");
        productList.add("Abobora - Jaqueta Jeans Trucker C-Starz ");
        productList.add("CORTEIZ - Jaqueta Jeans Trucker C-Starz ");
        productList.add("Abobora - Jaqueta Jeans Trucker C-Starz ");
        productList.add("CORTEIZ - Jaqueta Jeans Trucker C-Starz ");
        productList.add("Abobora - Jaqueta Jeans Trucker C-Starz ");
        productList.add("CORTEIZ - Jaqueta Jeans Trucker C-Starz ");
        productList.add("Abobora - Jaqueta Jeans Trucker C-Starz ");

        ProductAdapter adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}