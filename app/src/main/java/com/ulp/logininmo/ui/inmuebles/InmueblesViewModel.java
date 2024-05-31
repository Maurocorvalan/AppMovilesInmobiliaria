package com.ulp.logininmo.ui.inmuebles;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.logininmo.models.Inmueble;
import com.ulp.logininmo.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> inmuebles;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        inmuebles = new MutableLiveData<>();
    }

    public LiveData<List<Inmueble>> getInmuebles() {
        return inmuebles;
    }

    public void cargarInmuebles() {
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        String token = ApiClient.leerToken(getApplication().getApplicationContext());
        if (token != null && !token.isEmpty()) {
            Call<List<Inmueble>> call = api.obtenerInmuebles(token);
            call.enqueue(new Callback<List<Inmueble>>() {
                @Override
                public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                    if (response.isSuccessful()) {
                        inmuebles.setValue(response.body());
                        Log.d("InmueblesViewModel", "Inmuebles cargados exitosamente.");
                    } else {
                        Log.d("InmueblesViewModel", "Error al cargar inmuebles: " + response.message());
                        Log.d("InmueblesViewModel", "Response code: " + response.code());
                        Log.d("InmueblesViewModel", "Response error body: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                    Log.e("InmueblesViewModel", "Error en la llamada: ", t);
                }
            });
        } else {
            Log.d("InmueblesViewModel", "Token no encontrado o vacío");
        }
    }
}
