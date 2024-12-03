package com.example.myapplication.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.data.Product;
import com.example.myapplication.data.ProductImage;
import com.example.myapplication.data.base.Api;
import com.example.myapplication.databinding.FragmentProductDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;


public class ProductDetailFragment extends Fragment {
    private static final String ARG_PRODUCT = "product";
    private FragmentProductDetailBinding binding;
    public static ProductDetailFragment newInstance(Product product) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (getArguments() != null) {
            Product product = (Product) getArguments().getSerializable(ARG_PRODUCT);
            if (product != null) {
                displayProductDetails(product);
                setupViewPager(product.getProductImages());
                setupArrowButtons(product.getProductImages());
            }
        }

        return root;
    }
    private void displayProductDetails(Product product) {
        TextView tvName = binding.productName;
        TextView tvBrand = binding.productBrand;
        TextView tvPrice = binding.productPrice;

        tvName.setText(product.getName());
        tvBrand.setText(product.getBrand());
        tvPrice.setText(String.format(Locale.getDefault(), "$%.2f", product.getPrice()));
    }
    private void setupViewPager(List<ProductImage> productImages) {
        ImageAdapter imageAdapter = new ImageAdapter(productImages);
        binding.viewPager.setAdapter(imageAdapter);
    }
    private void setupArrowButtons(final List<ProductImage> productImages) {
        binding.leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = binding.viewPager.getCurrentItem();
                if (currentPosition > 0) {
                    binding.viewPager.setCurrentItem(currentPosition - 1, true);
                }
            }
        });

        binding.rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = binding.viewPager.getCurrentItem();
                if (currentPosition < productImages.size() - 1) {
                    binding.viewPager.setCurrentItem(currentPosition + 1, true);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}