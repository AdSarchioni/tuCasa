package com.movi.tucasalab3.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.movi.tucasalab3.R;
import com.movi.tucasalab3.models.Contrato;
import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> itemM;
    private Context context;
    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Contrato> getItemM(){
        if(itemM==null){

            itemM=new MutableLiveData<>();
        }
        return itemM;

    }


    public void CargarItem(Bundle bundle) {

        Inmueble item = (Inmueble) bundle.getSerializable("contrato");


        String token = ApiClient.obtenerToken(context);

        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        Call<Contrato> llamada = api.ObtenerContratoPorInmueble(token, item.getId_Inmueble());

        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){

                    Log.d("salida respuesta ",response.body().toString());

                    // no estan funcionando las fechas

                    itemM.postValue(response.body());

                }else {
                    Toast.makeText(context, "No hay Pagos", Toast.LENGTH_SHORT).show();
                   // Log.d("salida respuesta ",response.body().toString());
                }
            }

            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("salida falla ",t.getMessage());
            }

        });

    }
    public void verPagos(View view) {

        Bundle bundle = new Bundle();

        Contrato contrato = itemM.getValue();

        bundle.putSerializable("contratoPago", contrato);


        Navigation.findNavController(view).
                navigate(R.id.pagosFragment,bundle);

    }




}