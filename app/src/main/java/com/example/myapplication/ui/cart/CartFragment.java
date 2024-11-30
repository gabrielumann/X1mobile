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

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCartBinding;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CartViewModel notificationsViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerViewCart;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<CartPOJO> cartItems = new ArrayList<>();
        cartItems.add(new CartPOJO("CORTEIZ - Jacket", "R$ 399,99", R.drawable.ic_home_black_24dp));
        cartItems.add(new CartPOJO("Other Jacket", "R$ 429,99", R.drawable.ic_notifications_black_24dp));

        CartAdapter adapter = new CartAdapter(cartItems);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}