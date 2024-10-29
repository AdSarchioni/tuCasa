package com.movi.tucasalab3.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> mListaInmuebles;
    private Context context;


    public ContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }
    public MutableLiveData<List<Inmueble>> getmListaInmuebles() {
        if(mListaInmuebles == null){
            mListaInmuebles = new MutableLiveData<>();
        }
        return mListaInmuebles;
    }
    public void imprimirInmuebles(){
        String token = ApiClient.obtenerToken(context);

        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        Call<List<Inmueble>> llamada = api.ObtenerInmuebles(token);


        llamada.enqueue(new Callback<List<Inmueble>>() {

            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {

                if(response.isSuccessful()){

                    mListaInmuebles.postValue(response.body());
                }
                else{
                    Log.d("salida respuesta ",response.raw().message());
                    Toast.makeText(context, "error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable throwable ) {
                Toast.makeText(context, "error en onFailure", Toast.LENGTH_SHORT).show();
                Log.e("Error22", "Error de red o configuración: " + throwable.getMessage());
            }
        });
    }



}