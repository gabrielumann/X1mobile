package com.example.myapplication.ui.cart;

import android.content.SharedPreferences;
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
import java.util.Locale;


public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CartViewModel notificationsViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        List<Product> cartProducts = Cart.getInstance().getCartItems();
        setRecyclerView(cartProducts);
        changeTextVisibility(cartProducts.size());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void setRecyclerView(List<Product> products){
        RecyclerView recyclerView = binding.recyclerViewCart;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CartAdapter adapter = new CartAdapter(products);
        recyclerView.setAdapter(adapter);
        updateTotalPrice(adapter);
        changeTextVisibility(products.size());

    }
    private void updateTotalPrice(CartAdapter adapter) {
        double total = adapter.calculateTotal();
        double shipping = 0;
        if(total > 1000){
            alterShipping(shipping);
        }else{
            shipping = 30;
        }
        
        TextView Tvtotal = binding.txtTotal;
        String formattedTotal = String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", total + shipping);
        Tvtotal.setText(formattedTotal);
    }
    
    private void alterShipping( double shipping){
        shipping = 0;
        TextView tvShipping = binding.txtShipping;
        tvShipping.setText(String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", shipping));
        
        
    }
    private void changeTextVisibility(int cartSize){
        TextView emptyCartMessage = binding.emptyCartMessage;

        if (cartSize == 0) {
            emptyCartMessage.setVisibility(View.VISIBLE);
        } else {
            emptyCartMessage.setVisibility(View.GONE);
        }
    }
}