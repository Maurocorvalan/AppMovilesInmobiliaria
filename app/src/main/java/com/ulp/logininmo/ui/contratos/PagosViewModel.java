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
import com.ulp.logininmo.models.Pago;
import com.ulp.logininmo.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pago>> pagos;
    private int id;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        this.pagos = new MutableLiveData<>();
    }

    public LiveData<List<Pago>> getPagos() {
        return pagos;
    }
    public void recuperarContrato(Bundle bundle){
        Contrato contrato = (Contrato) bundle.get("contrato");
        if(contrato != null){
            id = contrato.getIdContrato();
        }
    }

    public void cargarPagos() {
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        String token = ApiClient.leerToken(getApplication().getApplicationContext());
        Log.d("Pagos", id+"");
        Log.d("Pagos", token);
        if (token != null && !token.isEmpty()) {
            Call<List<Pago>> call = api.GetPagosPorContrato(token, id);
                call.enqueue(new Callback<List<Pago>>() {
                    @Override
                    public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                        if (response.isSuccessful()){
                            pagos.setValue(response.body());
                            Log.d("Pagos", "Pagos cargados exitosamente.");
                        }else{
                            Log.d("Pagos", "Error al cargar pagos: " + response.message());
                            Log.d("Pagos", "Response code: " + response.code());
                            Log.d("Pagos", "Response error body: " + response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Pago>> call, Throwable t) {
                        Log.e("InmueblesViewModel", "Error en la llamada: ", t);
                    }
                });
            } else{
                    Log.d("InmueblesViewModel", "Token no encontrado o vacío");
                  }
        if (pagos.getValue() == null || pagos.getValue().isEmpty()) {
        }
    }
}
