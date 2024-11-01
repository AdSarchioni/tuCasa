package com.movi.tucasalab3;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.movi.tucasalab3.models.Propietario;
import com.movi.tucasalab3.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Propietario> propietarioM;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<Propietario> getpropietarioM() {
        if (propietarioM == null) {
            propietarioM = new MutableLiveData<>();
        }
        return propietarioM;
    }

    public void datosPropietario() {

        String token = ApiClient.obtenerToken(context);

        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        Call<Propietario> llamada = api.perfil(token);

        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {

                    propietarioM.postValue(response.body());

                        Log.d("salida3 ",response.body().getApellido());

                } else {

                    Log.d("salida respuesta ",response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                //  Log.d("salida falla ",t.getMessage());

            }
        });


    }
}