package com.ulp.logininmo.ui.perfil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ulp.logininmo.R;
import com.ulp.logininmo.databinding.FragmentPerfilBinding;
import com.ulp.logininmo.models.Propietario;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel pvm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pvm = new ViewModelProvider(this).get(PerfilViewModel.class);
        pvm.getMpropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                if(propietario != null){
                    binding.etIdPropietario.setText(propietario.getIdPropietario()+"");
                    binding.etNombre.setText(propietario.getNombre());
                    binding.etApellido.setText(propietario.getApellido());
                    binding.etDni.setText(propietario.getDni());
                    binding.etMail.setText(propietario.getEmail().toString());
                    binding.etTelefono.setText(propietario.getTelefono());
                }else{
                    Toast.makeText(getContext(), "Propietario no encontrado", Toast.LENGTH_SHORT).show();

                }
            }
        });
        binding.btGuardarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario p = new Propietario(
                        binding.etNombre.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etDni.getText().toString(),
                        binding.etMail.getText().toString(),
                        binding.etTelefono.getText().toString());
                pvm.editarPerfil(p);
            }
        });
        binding.btCambiarContrasenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etContraActual.setVisibility(View.VISIBLE);
                binding.etContraConfirmar.setVisibility(View.VISIBLE);
                binding.etContraNueva.setVisibility(View.VISIBLE);
                binding.btAplicarContra.setVisibility(View.VISIBLE);
            }
        });
        binding.btAplicarContra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String contraseñaActual = binding.etContraActual.getText().toString();
                String nuevaContraseña = binding.etContraNueva.getText().toString();
                String confirmarContraseña = binding.etContraConfirmar.getText().toString();

                // Llamar al método para cambiar la contraseña en el ViewModel
                pvm.cambiarContraseña(contraseñaActual, nuevaContraseña, confirmarContraseña);
            }

        });

        pvm.llenarPerfil();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}