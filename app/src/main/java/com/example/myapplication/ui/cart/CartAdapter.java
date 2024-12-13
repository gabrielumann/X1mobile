package com.example.myapplication.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.Product;
import com.example.myapplication.data.base.Api;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> cartItems;

    public CartAdapter(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_card, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product item = cartItems.get(position);
        holder.productName.setText(item.getName());

        String formattedPrice = String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", item.getPrice());
        holder.price.setText(formattedPrice);

        String imageUrl = Api.BASE_URL + item.getProductImages().get(0).getImage();
        Picasso.get().load(imageUrl).into(holder.productImage);

    }


    @Override
    public int getItemCount() {
        return cartItems.size();
    }
    public double calculateTotal() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView price;
        ImageView productImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}
