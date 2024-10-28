package com.movi.tucasalab3.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {
    private Context context;

    private Inmueble inmueble;

    private MutableLiveData<Inmueble> itemM;

    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application;

    }
    public LiveData<Inmueble> getItemM(){
        if(itemM==null){

            itemM=new MutableLiveData<>();
        }
        return itemM;


}
    public void CargarItem(Bundle bundle) {

         inmueble = (Inmueble) bundle.getSerializable("iteminmueble");

        itemM.setValue(inmueble);

    }
    public void setEstado(boolean checked) {
        // Asegúrate de que 'inmueble' no sea null

            // Asignar el estado con base en el valor booleano
            inmueble.setEstado_Inmueble(checked ? 1 : 0);

            String token = ApiClient.obtenerToken(context);

            ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
            Call<Inmueble> llamada = api.ActualizarInmueble(token, inmueble);
                  Log.d("salida inmueble2", inmueble.toString());
            llamada.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful()) {
                        // Actualizar el valor en el LiveData o la UI
                        itemM.postValue(response.body());
                        Log.d("salida inmueble4", response.body().toString());
                        Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Error al modificar el inmueble", Toast.LENGTH_SHORT).show();
                        Log.d("salida inmueble3", response.toString());
                    }
                }

                @Override
                public void onFailure(Call<Inmueble> call, Throwable throwable) {
                    Log.e("Error", throwable.getMessage());
                }
            });

    }





}