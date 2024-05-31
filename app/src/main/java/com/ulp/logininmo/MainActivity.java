package com.ulp.logininmo;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CALL_LOG;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.logininmo.databinding.ActivityMainBinding;
import com.ulp.logininmo.ui.resetContrasenia.ActivityResetContrasenia;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        solicitarPermisos();
        vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding.btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etUsuario.getText().toString();
                String password = binding.etClave.getText().toString();
                vm.logeo(email, password);
                binding.etUsuario.setText("");
                binding.etClave.setText("");
            }
        });

        binding.btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplication(), ActivityResetContrasenia.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplication().startActivity(intent);

            }
        });
        vm.getEstadoMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        vm.iniciarSensor(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        vm.detenerSensor();
    }

    private void solicitarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{CALL_PHONE}, 2000);
            }
            if (checkSelfPermission(READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{READ_CALL_LOG}, 2000);
            }
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                    && (checkSelfPermission(ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)  ||
                    (checkSelfPermission(ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)){
                requestPermissions(new String[]{ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION},1000);
            }
        }
    }
}
