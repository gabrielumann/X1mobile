package com.example.myapplication.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        EditText etEmail = binding.editEmail;
        EditText etPassword = binding.editPassword;
        Button btnLogin = binding.btnLogin;
        Button btnCreate = binding.btnCreate;
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.registerFragment);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        loginViewModel.login(email, password);
                    } else {
                        Toast.makeText(getContext(), "E-mail inválido!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });

        loginViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            Toast.makeText(getContext(), "Bem-vindo " + user.getFirstName(), Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);

            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_profile, bundle);
        });

        loginViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();

        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}