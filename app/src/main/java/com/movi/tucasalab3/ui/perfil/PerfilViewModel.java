package com.movi.tucasalab3.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.movi.tucasalab3.models.Propietario;
import com.movi.tucasalab3.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> mPropietario;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }
    public LiveData<Propietario> getmPropietario() {
        if (mPropietario == null) {
            mPropietario = new MutableLiveData<>();
        }
        return mPropietario;
}
    public void editarPropietario(String dni, String nombre, String apellido, String direccion, String telefono) {
        // Validar que el DNI solo contenga números usando regex
        if (!dni.matches("\\d+")) {  // \\d+ significa que solo se permiten dígitos (números)
            Toast.makeText(context, "El DNI solo debe contener números", Toast.LENGTH_SHORT).show();
            return;  // Terminar la ejecución si el DNI no es válido
        }



        // Crear el objeto propietario con los datos proporcionados
        Propietario propietario = new Propietario( dni, nombre, apellido, direccion, telefono);

        // Obtener la API y el token
        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        String token = ApiClient.obtenerToken(context);

        // Llamar al método PUT de la API para actualizar los datos del propietario
        Call<Propietario> call = api.editarPropietario(token, propietario);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(context, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al actualizar el perfil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void cargarPerfil(){
        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        String token = ApiClient.obtenerToken(context);
        Log.d("Token", token);
        Call<Propietario>call = api.perfil(token);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        mPropietario.setValue(response.body());
                        Log.d("salida2", response.body().toString());

                    }else{
                        Toast.makeText(context, "No hay datos de propietario", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Error de servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable throwable) {

            }
        });

    }



}