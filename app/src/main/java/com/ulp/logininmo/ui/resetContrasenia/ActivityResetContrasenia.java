package com.ulp.logininmo.ui.resetContrasenia;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.logininmo.databinding.ActivityResetContraseniaBinding;

public class ActivityResetContrasenia extends AppCompatActivity {

    private ActivityResetContraseniaBinding binding;
    private ActivityResetContraseniaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetContraseniaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar ViewModel
        viewModel = new ViewModelProvider(this).get(ActivityResetContraseniaViewModel.class);

        // Observar cambios en el mensaje de respuesta
        viewModel.getMensajeRespuesta().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                Toast.makeText(ActivityResetContrasenia.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el listener del botón
        binding.btEnviarResetContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí debes obtener el email ingresado por el usuario y pasarlo al método resetearContraseña
                String email = binding.etMailContraReset.getText().toString();
                viewModel.resetearContraseña(email);
            }
        });
    }
}
