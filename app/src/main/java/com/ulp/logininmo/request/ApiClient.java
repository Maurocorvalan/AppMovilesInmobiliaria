package com.ulp.logininmo.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ulp.logininmo.models.Contrato;
import com.ulp.logininmo.models.Inmueble;
import com.ulp.logininmo.models.Pago;
import com.ulp.logininmo.models.Propietario;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiClient {
    private static  String URL = "http://192.168.1.6:5000/";
    private static MisEndPoints mep;
    public static  String urls = "http://192.168.1.6:5000/";

    public static MisEndPoints getEndPoints() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mep = retrofit.create(MisEndPoints.class);
        return mep;
    }

    public interface MisEndPoints {
        @FormUrlEncoded
        @POST("api/Propietarios/login")
        Call<String> login(@Field("Usuario") String u, @Field("Clave") String c);

        @GET("api/Propietarios")
        Call<Propietario> miPerfil(@Header("Authorization") String token);

        @PUT("api/Propietarios/actualizar")
        Call<Propietario> ActualizarPropietario(@Header("Authorization") String token, @Body Propietario p);

        @GET("api/Inmuebles")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);


        @GET("api/Inmuebles/GetContratoVigente")
        Call<List<Inmueble>> GetContratoVigente(@Header("Authorization") String token);

        @Multipart
        @POST("api/Inmuebles/cargar")
        Call<Inmueble> CargarInmueble(@Header("Authorization") String token,
                                      @Part MultipartBody.Part imagen,
                                      @Part("inmueble") RequestBody inmuebleBody);

        @PUT("api/Inmuebles/actualizar")
        Call<Inmueble> updateInmueble(@Header("Authorization") String token,
                                      @Body Inmueble inmueble);

        @GET("api/contratos/inmueble/{id}")
        Call<Contrato> obtenerContratoPorInmueble(
                @Header("Authorization") String token,
                @Path("id") int id
        );

        @GET("api/pagos/contrato/{id}")
        Call<List<Pago>>GetPagosPorContrato(
                @Header("Authorization") String token,
                @Path("id") int id
        );
        @FormUrlEncoded
        @POST("api/Propietarios/email")
        Call<String> resetearContraseña(@Field("email") String email);

        @PUT("api/Propietarios/changePassword")
        @FormUrlEncoded
        Call<Void> changePassword(
                @Header("Authorization") String token,
                @Field("currentPassword") String currentPassword,
                @Field("newPassword") String newPassword
        );
        }


    public static void guardarToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String leerToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        return sp.getString("token", null);
    }

    public static void deslogeo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", "");
        editor.apply();
        mep = null;

    }
}
