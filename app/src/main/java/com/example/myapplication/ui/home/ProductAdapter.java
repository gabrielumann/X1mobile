package com.example.myapplication.ui.home;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.Product;
import com.example.myapplication.data.base.Api;
import com.example.myapplication.ui.product.ProductDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final Context context;
    private final List<Product> products;
    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productName.setText(product.getName());
        String imageUrl = Api.BASE_URL + product.getProductImages().get(0).getImage();
        Picasso.get().load(imageUrl).into(holder.productImage);

        holder.buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar o Bundle com os dados do produto
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", product); // Adiciona o produto ao bundle

                // Usando o NavController para navegar para o ProductDetailFragment
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.productDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        ImageView productImage;
        Button buttonBuy;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonBuy = itemView.findViewById(R.id.button_buy);
            productName = itemView.findViewById(R.id.product_name);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}