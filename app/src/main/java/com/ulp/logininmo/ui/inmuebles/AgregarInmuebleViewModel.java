package com.ulp.logininmo.ui.inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import com.google.gson.Gson;
import com.ulp.logininmo.models.Inmueble;
import com.ulp.logininmo.models.Propietario;
import com.ulp.logininmo.request.ApiClient;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Uri> uriMutableLiveData;
    private MutableLiveData<Inmueble> mInmueble;
    private static Inmueble inmueblelleno;

    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
        inmueblelleno = new Inmueble();
    }

    public LiveData<Uri> getUriMutable() {
        if (uriMutableLiveData == null) {
            uriMutableLiveData = new MutableLiveData<>();
        }
        return uriMutableLiveData;
    }

    public LiveData<Inmueble> getmInmueble() {
        if (mInmueble == null) {
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public void getBytesFromUri(ContentResolver contentResolver, Uri uri) throws IOException {
        InputStream inputStream = contentResolver.openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        inmueblelleno.setBait(byteArrayOutputStream.toByteArray());
    }

    public void cargarinmueble(Inmueble inmueble) {
        byte[] by=inmueblelleno.getBait();
        inmueblelleno.setBait(null);

        String inmuebleJson = new Gson().toJson(inmueble);
        Log.d("cala", "Inmueble JSON: " + inmuebleJson);

        RequestBody inmuebleBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), inmuebleJson);

        byte[] imagenBytes = by;

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imagenBytes);
        MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestFile);

        ApiClient.MisEndPoints api = ApiClient.getEndPoints();
        String token = ApiClient.leerToken(getApplication());
        Call<Inmueble> call = api.CargarInmueble(token, imagenPart, inmuebleBody);
        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Log.d("carajo", "Inmueble enviado exitosamente");
                    Toast.makeText(getApplication(), "Inmueble Creado Correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("carajo", "Error: " + response.message());
                    Toast.makeText(getApplication(), "No se pudo actualizar el Inmueble", Toast.LENGTH_SHORT).show();
               }
}

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.d("carajo", "Fallo: " + t.getMessage());
            }
        });
    }


    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri uri = data.getData();
            Log.d("salada", uri.toString());
            uriMutableLiveData.setValue(uri);
        }
    }
}







