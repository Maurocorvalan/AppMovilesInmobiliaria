package com.ulp.logininmo.ui.inmuebles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.logininmo.databinding.FragmentAgregarInmuebleBinding;
import com.ulp.logininmo.models.Inmueble;

import java.io.IOException;

public class AgregarInmuebleFragment extends Fragment {
    private AgregarInmuebleViewModel mv;
    private FragmentAgregarInmuebleBinding binding;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mv = new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);

        abrirGaleria();

        mv.getUriMutable().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri){
                binding.imgInmuebleAdd.setImageURI(uri);
                try {
                    mv.getBytesFromUri(requireContext().getContentResolver(), uri);
                } catch (IOException e) {
                    throw new RuntimeException(e);
               }
}
        });

        binding.btAgregarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });

        binding.btAgregarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarInmueble();
            }
        });
    }

    private void agregarInmueble() {
        String direccion = binding.etDireccion.getText().toString();
        String uso = binding.spinnerUso.getSelectedItem().toString();
        String tipo = binding.spinnerTipo.getSelectedItem().toString();
        double latitud = Double.parseDouble(binding.etLatitud.getText().toString());
        double longitud = Double.parseDouble(binding.etLongitud.getText().toString());
        double valor = Double.parseDouble(binding.etValor.getText().toString());
        boolean disponible = binding.checkBox.isChecked();

        Inmueble inmueble = new Inmueble();
        inmueble.setDireccion(direccion);
        inmueble.setUso(uso);
        inmueble.setTipo(tipo);
        inmueble.setLatitud(latitud);
        inmueble.setLongitud(longitud);
        inmueble.setValor(valor);
        inmueble.setDisponible(disponible);

        mv.cargarinmueble(inmueble);
    }

    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                mv.recibirFoto(result);
                Log.d("AgregarInmuebleFragment", "Result: " + result);
            }
        });
    }
}
