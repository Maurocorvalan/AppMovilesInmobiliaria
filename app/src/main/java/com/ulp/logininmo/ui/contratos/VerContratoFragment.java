package com.ulp.logininmo.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ulp.logininmo.R;
import com.ulp.logininmo.databinding.FragmentVerContratoBinding;
import com.ulp.logininmo.models.Contrato;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VerContratoFragment extends Fragment {

    private VerContratoViewModel vm;
    private FragmentVerContratoBinding binding;
    private Contrato contrato;

    public static VerContratoFragment newInstance() {
        return new VerContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentVerContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(VerContratoViewModel.class);
        contrato = new Contrato();
        vm.getContratoM().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contratos) {
                contrato = contratos;
                LocalDate fechaInicio = LocalDate.parse(contrato.getFechaInicio(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate fechaFinalizacion = LocalDate.parse(contrato.getFechaFinalizacion(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String fechaInicioFormateada = fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String fechaFinalizacionFormateada = fechaFinalizacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                binding.tvCodigoContrato.setText(contrato.getIdContrato() + "");

                binding.tvFechaInicio.setText(fechaInicioFormateada);
                binding.tvFechaFinalizacion.setText(fechaFinalizacionFormateada );
                binding.tvMontoAlquiler.setText(String.valueOf(contrato.getMontoAlquiler()));
                binding.tvInquilino.setText(contrato.getInquilino().getNombre());
                binding.tvInmuebleC.setText(contrato.getInmueble().getDireccion());
            }
        });
        vm.recuperarInmueble(getArguments());
        vm.llenarContrato();
        Button btPagos = root.findViewById(R.id.btPagos);
        btPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("contrato",contrato);
                Navigation.findNavController((Activity) root.getContext(), R.id.nav_host_fragment_content_navigation).navigate(R.id.pagosFragment,bundle);
            }
        });
        return root;
    }

}

