package com.example.myapplication.ui.cart;

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

import com.example.myapplication.data.Product;
import com.example.myapplication.databinding.FragmentCartBinding;
import com.example.myapplication.model.cart.Cart;

import java.util.List;


public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CartViewModel notificationsViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<Product> cartProducts = Cart.getInstance().getCartItems();
        if (cartProducts != null){
            setRecyclerView(cartProducts);
            changeProductsVisibility(cartProducts.size());
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setRecyclerView(List<Product> products){
        RecyclerView recyclerView = binding.recyclerViewCart;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CartAdapter adapter = new CartAdapter(products);
        recyclerView.setAdapter(adapter);
        changeProductsVisibility(products.size());
    }

    private void changeProductsVisibility(int cartSize){
        TextView emptyCartMessage = binding.emptyCartMessage;
        RecyclerView recyclerView = binding.recyclerViewCart;

        if (cartSize == 0) {
            emptyCartMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyCartMessage.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}