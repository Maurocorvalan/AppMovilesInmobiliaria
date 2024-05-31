package com.ulp.logininmo.ui.contratos;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.logininmo.models.Contrato;
import com.ulp.logininmo.models.Inmueble;
import com.ulp.logininmo.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerContratoViewModel extends AndroidViewModel {
    private static int id;
    private MutableLiveData<Inmueble> inmuebleM;
    private MutableLiveData<Contrato> contratoM;

    public VerContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmueble() {
        if (inmuebleM == null) {
            inmuebleM = new MutableLiveData<>();
        }
        return inmuebleM;
    }

    public LiveData<Contrato> getContratoM() {
        if (contratoM == null) {
            contratoM = new MutableLiveData<>();
        }
        return contratoM;
    }

    public void recuperarInmueble(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        if (inmueble != null) {
            id = inmueble.getIdInmueble();
        }
    }

    public void llenarContrato() {
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        String token = ApiClient.leerToken(getApplication());
        Log.d("salida",token);
        Log.d("salida",id+"");


        if (token != null && !token.isEmpty()) {
            Call<Contrato> call = api.obtenerContratoPorInmueble(token, id);
            call.enqueue(new Callback<Contrato>() {
                @Override
                public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                    if (response.isSuccessful()) {
                        contratoM.postValue(response.body());
                        Log.d("salidassss", contratoM.toString());
                    } else {
                        Log.d("salida","estas aca");
 }
                }

                @Override
                public void onFailure(Call<Contrato> call, Throwable throwable) {
                    Toast.makeText(getApplication(), "Error en la solicitud", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplication(), "Token no válido", Toast.LENGTH_SHORT).show();
        }
    }
}
