package com.ulp.logininmo.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.logininmo.models.Inmueble;
import com.ulp.logininmo.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarInmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> mInmueble;
    public EditarInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Inmueble> getmInmueble(){
        if(mInmueble ==null){
            mInmueble=new MutableLiveData<>();
        }
        return mInmueble;
    }

    public void setmInmueble(MutableLiveData<Inmueble> mInmueble){
        this.mInmueble =mInmueble;
    }

    public void recuperarInmueble(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        if(inmueble!=null){
            mInmueble.setValue(inmueble);
            Log.d("ambar", inmueble.toString());


        }
    }

    public void updateInmueble(Inmueble inmueble){
        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        String token = ApiClient.leerToken(getApplication());
        Log.d("salidasa",inmueble.toString());
        Call<Inmueble> call = api.updateInmueble(token, inmueble);
        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplication(), "Inmueble Actualizado Correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("salida", "no se pudo acceder al inmueble");
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable throwable) {
                Log.d("salida", "error aca: "+throwable);
            }
        });

    }
}