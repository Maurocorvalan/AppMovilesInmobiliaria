package com.ulp.logininmo.ui.Logout;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ulp.logininmo.MainActivity;
import com.ulp.logininmo.R;
import com.ulp.logininmo.databinding.FragmentLogOutBinding;
import com.ulp.logininmo.databinding.FragmentPerfilBinding;
import com.ulp.logininmo.request.ApiClient;

public class LogOutFragment extends Fragment {

    private LogOutViewModel mViewModel;
    private FragmentLogOutBinding binding;

    public static LogOutFragment newInstance() {
        return new LogOutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLogOutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button btnCerrarSesion = binding.btnSalir;

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();

            }
        });
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    private void showExitDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirmar Salida")
                .setMessage("¿Estás seguro de que deseas salir de la aplicación?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    ApiClient.deslogeo(requireContext());
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                })
                .setNegativeButton("No", null)
                .show();
    }

}
