package com.example.myapplication.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.FragmentCategoryBinding;
import com.example.myapplication.model.category.CategoryPOJO;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoryViewModel dashboardViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerViewCategory;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<CategoryPOJO> categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryPOJO("Calçados"));
        categoryItemList.add(new CategoryPOJO("Vestuário"));
        categoryItemList.add(new CategoryPOJO("Acessórios"));

        CategoryAdapter adapter = new CategoryAdapter(categoryItemList);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}