package com.movi.tucasalab3.ui.pago;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movi.tucasalab3.models.Contrato;
import com.movi.tucasalab3.models.Pago;
import com.movi.tucasalab3.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Pago>> listaPagoM;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }
    public LiveData<List<Pago>> getListaM(){
        if(listaPagoM==null){

            listaPagoM=new MutableLiveData<>();
        }
        return listaPagoM;

    }

    public void cargarLista(Bundle bundle) {


        Contrato contrato = (Contrato) bundle.getSerializable("contratoPago");


        String token = ApiClient.obtenerToken(context);

        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        Call<List<Pago>> llamada = api.ObtenerPagosPorContrato(token, contrato.getId_Contrato());

        llamada.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if (response.isSuccessful()) {

                    Log.d("salida respuesta ", response.body().toString());

                    // no estan funcionando las fechas

                    listaPagoM.postValue(response.body());

                } else {

                    Log.d("salida respuesta ", response.body().toString());
                }
            }

            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Log.d("salida falla ", t.getMessage());
            }

        });

    }
}





