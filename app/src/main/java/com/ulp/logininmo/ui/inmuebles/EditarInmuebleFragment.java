package com.ulp.logininmo.ui.inmuebles;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import static com.ulp.logininmo.request.ApiClient.urls;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ulp.logininmo.R;
import com.ulp.logininmo.databinding.FragmentEditarInmuebleBinding;
import com.ulp.logininmo.models.Inmueble;

public class EditarInmuebleFragment extends Fragment {

    private EditarInmuebleViewModel vm;
    private FragmentEditarInmuebleBinding binding;
    private Inmueble inmueble;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inmueble=new Inmueble();
        EditarInmuebleViewModel vm = new ViewModelProvider(this).get(EditarInmuebleViewModel.class);
        binding = FragmentEditarInmuebleBinding.inflate(inflater, container, false);
        View view= binding.getRoot();

        vm.getmInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmuebles) {
                inmueble=inmuebles;

                    binding.tvIdInmueble.setText(String.valueOf(inmueble.getIdInmueble()));
                    binding.tvDireccionI.setText(inmueble.getDireccion());
                    binding.tvUsoI.setText(inmueble.getUso());
                    binding.tvAmbientesI.setText(String.valueOf(inmueble.getAmbientes()));
                    binding.tvLatitudI.setText(String.valueOf(inmueble.getLatitud()));
                    binding.tvLongitudI.setText(String.valueOf(inmueble.getLatitud()));
                    binding.tvValorI.setText(String.valueOf(inmueble.getValor()));
                    binding.checkDisponible.setChecked(inmueble.getDisponible());
                    Glide.with(getContext())
                            .load(urls + inmueble.getImagen())
                            .placeholder(null)
                            .error("null")
                            .into(binding.imgInmuebleD);



            }
        });

        binding.btEditarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inmueble.setDisponible(binding.checkDisponible.isChecked());
                vm.updateInmueble(inmueble);
            }
        });
        vm.recuperarInmueble(getArguments());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(EditarInmuebleViewModel.class);
        // TODO: Use the ViewModel
    }

}