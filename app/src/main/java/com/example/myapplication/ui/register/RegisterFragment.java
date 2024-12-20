package com.example.myapplication.ui.register;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RegisterViewModel registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        EditText etFirstName = binding.editFirstName;
        EditText etLastName = binding.editLastName;
        EditText etCpf = binding.editCpf;
        EditText etEmail = binding.editEmail;
        EditText etPassword = binding.editPassword;
        EditText etPasswordConfirmed = binding.editPasswordConfirm;

        Button btnlogin = binding.btnLogin;
        Button btnRegister = binding.btnRegister;

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.popBackStack();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String cpf = etCpf.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String passwordConfirmed = etPasswordConfirmed.getText().toString().trim();

                if (!isEmptyFields(firstName, lastName, cpf, email, password, passwordConfirmed)) {

                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        if (password.equals(passwordConfirmed)){
                            registerViewModel.register(firstName, lastName, cpf, email, password, passwordConfirmed);
                        }else {
                            Toast.makeText(getContext(), "As senhas devem ser iguais", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "E-mail inválido!", Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(getContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }

            }
        });

        registerViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            Toast.makeText(getContext(), "Usuário " + user.getFirstName() + " criado com sucesso!", Toast.LENGTH_SHORT).show();

        });

        registerViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();

        });

        return binding.getRoot();
    }

    private boolean isEmptyFields(String firstName, String lastName, String cpf, String email, String password, String passwordConfirmed){
        return email.isEmpty() || password.isEmpty() || cpf.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || passwordConfirmed.isEmpty();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}