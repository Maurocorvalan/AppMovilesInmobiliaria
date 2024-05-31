package com.ulp.logininmo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.logininmo.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> estadoM;
    private int activador = 0;
    private static final int SHAKE_THRESHOLD = 20;
    private SensorManager sensorManager;
    private ManejaSensor sensorListener;
    private MutableLiveData<String> estadoMensaje = new MutableLiveData<>();


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        estadoM = new MutableLiveData<>();

    }

    public LiveData<Boolean> getEstadoM() {
        if (estadoM == null) {
            estadoM = new MutableLiveData<>();
        }
        return estadoM;
    }
    public LiveData<String> getEstadoMensaje() {
        return estadoMensaje;
    }

    public void iniciarSensor(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> listasensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (listasensores.size() > 0) {
            sensorListener = new ManejaSensor(context);
            sensorManager.registerListener(sensorListener, listasensores.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void detenerSensor() {
        if (sensorManager != null && sensorListener != null) {
            sensorManager.unregisterListener(sensorListener);
            sensorManager = null;
            sensorListener = null;
        }
    }

    private class ManejaSensor implements SensorEventListener {
        private final Context context;

        private ManejaSensor(Context context) {
            this.context = context;
        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            float gForce = (float) Math.sqrt(x * x + y * y + z * z);
            if (gForce > SHAKE_THRESHOLD) {
                activador++;
            } else {
                activador = 0;
            }
            if (activador > 2) {
                activador = 0;
                realizarLlamada(context);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    }

    private void realizarLlamada(Context context) {
        Intent intentLlamada = new Intent(Intent.ACTION_CALL);
        String numeroTelefono = "tel:2664731516";
        intentLlamada.setData(Uri.parse(numeroTelefono));
        intentLlamada.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            context.startActivity(intentLlamada);
        } catch (SecurityException e) {
            Toast.makeText(context, "Permiso de llamada no concedido", Toast.LENGTH_SHORT).show();
        }
    }



    public void logeo(String usuario, String clave) {
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        Call<String> call = api.login(usuario, clave);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("salida", response.body());
                    String token = response.body();
                    guardarToken("Bearer " + token);
                    estadoM.setValue(true);
                    Log.d("salida", "inicio exitoso");
                    Intent intent = new Intent(getApplication(), navigation.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                } else {
                    estadoM.setValue(false);
                    estadoMensaje.setValue("Usuario o contraseña incorrectos");
                    Log.d("salida", "incorrecto");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.d("salida", "fallo");
            }
        });
    }

    private void guardarToken(String token) {
        ApiClient.guardarToken(getApplication(), token);
    }
}
