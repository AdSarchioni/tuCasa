package com.movi.tucasalab3;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.movi.tucasalab3.models.LoginUs;
import com.movi.tucasalab3.retrofit.ApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> mUsuario;
    private Context context;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<String> getMUsuario () {
        if (mUsuario == null) {
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public void llamarLogin(String Clave, String Usuario) {
        // Crear instancia de login con los datos ingresados
        LoginUs login = new LoginUs(Usuario, Clave);

        // Crear instancia de API
        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        Call<String> call = api.login(login);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Datos correctos", Toast.LENGTH_SHORT).show();
                    Log.d("salida", response.body());

                    // Guardar correctamente con Bearer y un espacio
                    ApiClient.guardarToken(context, "Bearer " + response.body());

                    // Iniciar MainActivity
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Error de conexión con el servidor", Toast.LENGTH_SHORT).show();
                Log.e("Error", throwable.getMessage());  // Agregar log de error para más información
            }
        });
    }





}
