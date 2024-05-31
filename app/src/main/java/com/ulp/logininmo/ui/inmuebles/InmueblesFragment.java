package com.ulp.logininmo.ui.inmuebles;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ulp.logininmo.R;
import com.ulp.logininmo.models.Inmueble;
import com.ulp.logininmo.ui.inmuebles.InmuebleAdapter;
import com.ulp.logininmo.ui.inmuebles.InmueblesViewModel;
import java.util.List;

public class InmueblesFragment extends Fragment {
    private InmueblesViewModel inmueblesViewModel;
    private RecyclerView recyclerView;
    private InmuebleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inmuebles, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        int numberOfColumns = 2; // Define el número de columnas
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));

        adapter = new InmuebleAdapter(requireContext());
        recyclerView.setAdapter(adapter);

        inmueblesViewModel = new ViewModelProvider(this).get(InmueblesViewModel.class);
        inmueblesViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                adapter.setInmuebles(inmuebles);
            }
        });

        inmueblesViewModel.cargarInmuebles();

        FloatingActionButton btAggInmueble = view.findViewById(R.id.btAggInmueble);
        btAggInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(InmueblesFragment.this)
                        .navigate(R.id.action_nav_slideshow_to_agregar_inmueble);
            }
        });

        return view;
    }
}
