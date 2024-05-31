package com.ulp.logininmo.ui.contratos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ulp.logininmo.R;
import com.ulp.logininmo.databinding.FragmentContratosBinding;
import com.ulp.logininmo.databinding.FragmentInmueblesBinding;
import com.ulp.logininmo.models.Contrato;
import com.ulp.logininmo.models.Inmueble;

import java.util.List;

public class ContratosFragment extends Fragment {

    private ContratosViewModel mViewModel;

    private FragmentInmueblesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContratosViewModel mViewModel =
                new ViewModelProvider(this).get(ContratosViewModel.class);

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        mViewModel.getListaMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                ContratoAdapter adapter = new ContratoAdapter(inmuebles,requireContext(),getLayoutInflater());
                RecyclerView rc= binding.recyclerView;
                int numberOfColumns = 2;
                rc.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
                rc.setAdapter(adapter);

            }
        });
        mViewModel.cargarInmuebles();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}