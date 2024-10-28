package com.movi.tucasalab3.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Inmueble>> listaInmuebleAlquiladosM;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Inmueble>> getListaM(){
        if(listaInmuebleAlquiladosM==null){

            listaInmuebleAlquiladosM=new MutableLiveData<>();
        }
        return listaInmuebleAlquiladosM;

    }

    public void obtenerInmueblesAlquilados(){

        String token = ApiClient.obtenerToken(context);

        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        Call<List<Inmueble>> llamada = api.ObtenerInmueblesAlquilados(token);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){


                    listaInmuebleAlquiladosM.postValue(response.body());

                }else {

                    Log.d("salida respuesta ",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable throwable) {
                Log.d("salida falla ",throwable.getMessage());
            }
        });
    }

}
