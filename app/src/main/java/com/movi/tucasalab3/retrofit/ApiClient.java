package com.movi.tucasalab3.retrofit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.movi.tucasalab3.models.CambioC;
import com.movi.tucasalab3.models.Contrato;
import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.models.Inquilino;
import com.movi.tucasalab3.models.LoginUs;
import com.movi.tucasalab3.models.Pago;
import com.movi.tucasalab3.models.Propietario;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public class ApiClient {

    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("datos", 0);
        }
        return sp;
    }
    public static void guardarToken(Context context, String token){
        SharedPreferences shared = conectar(context);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public static String obtenerToken(Context context){
        SharedPreferences shared = conectar(context);
        return shared.getString("token", "");
    }
    public static void Salir(Activity activity){

        new AlertDialog.Builder(activity)
                .setTitle("Salir de la aplicacion")
                .setMessage("Esta seguro que quiere salir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //   activity.finish();
                        SharedPreferences shared = activity.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();

                        editor.putString("token", "");
                        editor.commit();
                        activity.finishAndRemoveTask();
                        activity.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(activity,"Continua la aplicacion", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


    public static final String URLBASE = "http://192.168.1.102:5028/api/";
    public static final String URLBASEIMG = "http://192.168.1.102:5028/img/";
    public static InmobiliariaService getApiInmobiliaria(Context context){


        //parseo del elemento json a objeto java
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))//para objetso complejos como fechas
                .build();

        return retrofit.create(InmobiliariaService.class);
    }


    public interface InmobiliariaService {
        @POST("propietarios/login")
        Call<String> login(@Body LoginUs login);

        //get perfil del propietario
        @GET("propietarios/perfil")
        Call<Propietario> perfil(@Header("Authorization") String token);

        // PUT para editar perfil del propietario
        @PUT("propietarios/editar_perfil")
        Call<Propietario> editarPropietario(@Header("Authorization") String token, @Body Propietario propietario);

        // Método para cambiar la contraseña
        @PUT("propietarios/cambiar_contrasena")
        Call<String> cambiarContrasena(@Header("Authorization") String token, @Body CambioC cambioC);

        @GET("inmueble/ObtenerInmuebles")
        Call<List<Inmueble>> ObtenerInmuebles(@Header("Authorization") String token);

        @PUT("inmueble/actualizarInmueble")
        Call<Inmueble> ActualizarInmueble(@Header("Authorization") String token, @Body Inmueble inmueble);

        @Multipart
        @POST("inmueble/crearInmueble")
        Call<Inmueble> crearInmueble(@Header("Authorization") String token,
                                     @Part("Direccion") RequestBody Direccion,
                                     @Part("Uso") RequestBody Uso,
                                     @Part("Bano") RequestBody Bano,
                                     @Part("Condicion") RequestBody Condicion,
                                     @Part("Descripcion") RequestBody Descripcion,
                                     @Part("Servicios") RequestBody Servicios,
                                     @Part("Tamano") RequestBody Tamano,
                                     @Part("Patio") RequestBody Patio,
                                     @Part("Cochera") RequestBody Cochera,
                                     @Part("id_Tipo_Inmueble") RequestBody id_Tipo_Inmueble,
                                     @Part("Ambientes") RequestBody Ambientes,
                                     @Part("Precio") RequestBody Precio,
                                     @Part("Estado_Inmueble") RequestBody Estado_Inmueble,
                                     @Part MultipartBody.Part imagen);

        @GET("inmueble/obtenerAlquilados")
        Call<List<Inmueble>> ObtenerInmueblesAlquilados(@Header("Authorization") String token);

        /*-------------Inquilino------------------*/
        @GET("inquilinos/obtenerInquilinoPorInmueble")
        Call<Inquilino> ObtenerInquilinoPorInmueble(@Header("Authorization") String token, @Query("id") int id);

        /*-------------Contrato------------------*/
        @GET("contrato/obtenerContratoPorInmueble")
        Call<Contrato> ObtenerContratoPorInmueble(@Header("Authorization") String token, @Query("id") int id);

        /*-------------Pago------------------*/
        @GET("Pago/ObtenerPagosPorContrato")
        Call<List<Pago>> ObtenerPagosPorContrato(@Header("Authorization") String token, @Query("id") int id);

        @FormUrlEncoded
        @POST("propietarios/email")
        Call<String> olvidoContrasena(@Field("email") String email);


        @GET("propietarios/token")
        Call<String> resetContrasena(@Header("Authorization") String token);
    }




    }









