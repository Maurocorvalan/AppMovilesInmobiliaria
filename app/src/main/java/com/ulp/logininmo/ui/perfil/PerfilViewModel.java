package com.ulp.logininmo.ui.perfil;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.logininmo.models.Propietario;
import com.ulp.logininmo.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> mPropietario;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Propietario> getMpropietario() {
        if (mPropietario == null) {
            mPropietario = new MutableLiveData<>();
        }
        return mPropietario;
    }

    public void llenarPerfil() {
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        String token = ApiClient.leerToken(getApplication());
        Call<Propietario> call = api.miPerfil(token);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    if (mPropietario != null) {
                        mPropietario.postValue(response.body());
                    }
                } else {
                    Toast.makeText(getApplication(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editarPerfil(Propietario p) {
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        String token = ApiClient.leerToken(getApplication());
        Call<Propietario> call = api.ActualizarPropietario(token, p);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    Log.d("carajo", "response");
                    mPropietario.postValue(response.body());
                    Toast.makeText(getApplication(), "Usuario Actualizado Correctamente", Toast.LENGTH_SHORT).show();

                } else {
                    Log.d("carajo", "Error: " + response.message());
                    Toast.makeText(getApplication(), "No se pudo actualizar el usauurio", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable throwable) {
                Log.d("carajo", "response");
                Toast.makeText(getApplication(), "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void cambiarContraseña(String contraseñaActual, String nuevaContraseña, String confirmarContraseña) {
        if (!nuevaContraseña.equals(confirmarContraseña)) {
            Toast.makeText(getApplication(), "La nueva contraseña y la confirmación no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        String token = ApiClient.leerToken(getApplication());
        Log.d("salida", "token cambiar contra" +token );
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        Call<Void> call = api.changePassword(token, contraseñaActual, nuevaContraseña);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("salida", response+"");
                    Toast.makeText(getApplication(), "La contraseña actual no es correcta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplication(), "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
