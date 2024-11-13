package com.example.myapplication.ui.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private final List<CategoryPOJO> categoryItemList;

    public CategoryAdapter( List<CategoryPOJO> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryPOJO category = categoryItemList.get(position);

        holder.textCategory.setText(category.getTitle());
        holder.iconCategory.setImageResource(category.getImageResId());
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textCategory;
        ImageView iconCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textCategory = itemView.findViewById(R.id.txtCategory);
            iconCategory = itemView.findViewById(R.id.ivIcon);
        }
    }
}
