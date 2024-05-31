package com.ulp.logininmo.ui.home;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.ulp.logininmo.R;
import com.ulp.logininmo.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap googleMap;

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            HomeFragment.this.googleMap = googleMap;
            getLocationAndMoveCamera();
        }
    };
    ///
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
    }
    //
    private void getLocationAndMoveCamera() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Handle permissions here
            return;
        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(requireActivity(), location -> {
                    if (location != null && googleMap != null) {
                        LatLng ubicacionActual = new LatLng(location.getLatitude(), location.getLongitude());
                        LatLng ubiInmobiliaria = new LatLng(-33.301201, -66.335509);

                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(ubiInmobiliaria).title("Inmobiliaria Centro"));

                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubiInmobiliaria, 15));



                    }
                });
    }
}
