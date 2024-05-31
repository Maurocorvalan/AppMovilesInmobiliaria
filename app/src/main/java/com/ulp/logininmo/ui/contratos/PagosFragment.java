package com.ulp.logininmo.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulp.logininmo.R;
import com.ulp.logininmo.models.Pago;

import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel vm;
    private RecyclerView recyclerView;
    private PagosAdapter adapter;

    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagos, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PagosAdapter(requireContext());
        recyclerView.setAdapter(adapter);

        vm = new ViewModelProvider(this).get(PagosViewModel.class);
        vm.getPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                adapter.setPagos(pagos);
            }
        });
        vm.recuperarContrato(getArguments());
        vm.cargarPagos();
        return view;
    }


}