package com.movi.tucasalab3.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.movi.tucasalab3.models.CambioC;
import com.movi.tucasalab3.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambioCViewModel extends AndroidViewModel {
    private Context context;

    public CambioCViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public void cambiarContrasena(String contrasenaActual, String contrasenaNueva) {
        if(contrasenaActual.isEmpty()||contrasenaNueva.isEmpty()){
            Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }


        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        String token = ApiClient.obtenerToken(context);

        // Crear el objeto CambiarContrasenaView con las contraseñas
        CambioC cambioC = new CambioC(contrasenaActual, contrasenaNueva);

        // Hacer la llamada a la API
        Call<String> call = api.cambiarContrasena(token, cambioC);

        Log.d("CambioCViewModel", "Token:"+token+"cambioC:"+cambioC.getContrasenaActual()+"cambioC:"+cambioC.getContrasenaNueva());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    // Mostrar mensaje de éxito
                    Toast.makeText(context, "Contraseña cambiada con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    // Mostrar mensaje de error
                    Toast.makeText(context, "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Manejar errores
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


}
}