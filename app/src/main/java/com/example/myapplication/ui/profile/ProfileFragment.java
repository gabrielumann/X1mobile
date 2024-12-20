package com.example.myapplication.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.myapplication.data.Product;
import com.example.myapplication.data.User;
import com.example.myapplication.data.base.Api;
import com.example.myapplication.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import java.util.Locale;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if (getArguments() != null) {
            User user = (User) getArguments().getSerializable("user");
            if (user != null) {
                displayUserDetails(user);
            }
        }

        return root;
    }
    private void displayUserDetails(User user) {
        TextView tvName = binding.profileName;
        TextView tvEmail = binding.profileEmail;
        ImageView ivUser = binding.profileImage;

        tvName.setText(user.getFirstName());
        tvEmail.setText(user.getEmail());
        String imageUrl = Api.BASE_URL + user.getImage();
        Picasso.get().load(imageUrl).into(ivUser);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}