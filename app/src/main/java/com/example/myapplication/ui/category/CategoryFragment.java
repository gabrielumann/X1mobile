package com.example.myapplication.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCategoryBinding;
import com.example.myapplication.ui.home.ProductAdapter;

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
        categoryItemList.add(new CategoryPOJO( "Lançamentos", R.drawable.baseline_anchor_24));
        categoryItemList.add(new CategoryPOJO("Calçados", R.drawable.baseline_anchor_24));
        categoryItemList.add(new CategoryPOJO("Vestuário", R.drawable.baseline_anchor_24));
        categoryItemList.add(new CategoryPOJO("Acessórios", R.drawable.baseline_anchor_24));

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