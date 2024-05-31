package com.ulp.logininmo.ui.resetContrasenia;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ulp.logininmo.models.Propietario;
import com.ulp.logininmo.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityResetContraseniaViewModel extends AndroidViewModel {

    private MutableLiveData<String> mensajeRespuesta;

    public ActivityResetContraseniaViewModel(@NonNull Application application) {
        super(application);
        mensajeRespuesta = new MutableLiveData<>();
    }

    public MutableLiveData<String> getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void resetearContraseña(String email) {
        ApiClient.MisEndPoints apiService = ApiClient.getEndPoints();
        Call<String> call = apiService.resetearContraseña(email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Mail enviado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "  Mail no encontrado", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ResetContraseña", "Error: " + t.getMessage());
                Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_SHORT).show();

                mensajeRespuesta.setValue("Error al restablecer la contraseña. Por favor, inténtelo de nuevo más tarde.");
            }
        });
    }
}
