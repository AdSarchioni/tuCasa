package com.movi.tucasalab3.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.models.Inquilino;
import com.movi.tucasalab3.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInquilinoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inquilino> inquiM;
    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Inquilino> getItemM(){
        if(inquiM==null){

            inquiM=new MutableLiveData<>();
        }
        return inquiM;

    }
    public void CargarInqui(Bundle bundle) {


        Inmueble item = (Inmueble) bundle.getSerializable("iteminmuebleInquilino");
        Log.d("salida1 ",item.toString());

        String token = ApiClient.obtenerToken(context);
        Log.d("salida2 ",token);
        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);

        Call<Inquilino> llamada = api.ObtenerInquilinoPorInmueble(token, item.getId_Inmueble());

        Log.d("salida5 ", String.valueOf(item.getId_Inmueble()));
        Log.d("salida6 ", llamada.toString());
        llamada.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // La respuesta es exitosa y no es nula
                    Log.d("salida6", "Respuesta exitosa: " + response.body().toString());
                    inquiM.postValue(response.body());
                } else {
                    // Respuesta no exitosa o el cuerpo es nulo
                    if (response.body() == null) {
                        Log.e("salida respuesta", "El cuerpo de la respuesta es nulo.");
                    } else {
                        Log.e("salida respuesta", "Respuesta no exitosa: " + response.toString());
                    }
                }
            }


            public void onFailure(Call<Inquilino> call, Throwable t) {
                Log.d("salida falla ",t.getMessage());
            }

        });


    }

}