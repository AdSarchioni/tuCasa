package com.movi.tucasalab3;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.net.Uri;
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
    private MutableLiveData<Boolean> tokenValido;
    private MutableLiveData<Boolean> tokenInvalido;

    private String token;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 1000;


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
    public LiveData<Boolean> getTokenValid() {

        if (tokenValido == null) {
            tokenValido = new MutableLiveData<>();
        }
            return tokenValido;
    }
    public LiveData<Boolean> getTokenInvalido() {
        if (tokenInvalido == null) {
            tokenInvalido = new MutableLiveData<>();
        }
        return tokenInvalido;
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





    public void olvidoContrasena(String email) {
        if (email.isEmpty()) {
            Toast.makeText(getApplication(), "Por favor, ingrese su correo", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiClient.InmobiliariaService api=ApiClient.getApiInmobiliaria(context);
        Call<String> llamada= api.olvidoContrasena(email);
        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                token = response.body();
                if (token != null && !token.isEmpty()) {
                    tokenValido.setValue(true);

                } else {
                    tokenInvalido.setValue(true);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
            }
        });


    }
    public void resetContrasena() {
        ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
        String bearerToken = "Bearer " + token;

        Call<String> llamada = api.resetContrasena(bearerToken);
        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getApplication(), "Se envio a su Correo la nueva contraseña ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
            }
        });
    }


public void HacerLlamada(SensorEvent sensor) {
    // Verifica que el tipo de sensor sea un acelerómetro antes de proceder.
    if (sensor.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

        // Obtiene los valores de aceleración en los ejes X, Y, y Z.
        float x = sensor.values[0];
        float y = sensor.values[1];
        float z = sensor.values[2];

        // Guarda el tiempo actual en milisegundos.
        long curTime = System.currentTimeMillis();

        // Comprueba si han pasado al menos 100 ms desde la última actualización
        // para evitar ejecutar el código demasiado rápido.
        if ((curTime - lastUpdate) > 100) {
            long diffTime = (curTime - lastUpdate); // Calcula el tiempo transcurrido.
            lastUpdate = curTime; // Actualiza el último tiempo de verificación.

            // Calcula la velocidad de cambio de posición dividiendo la diferencia en los ejes entre el tiempo transcurrido.
            float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

            // Verifica si la velocidad excede el umbral de agitación.
            if (speed > SHAKE_THRESHOLD) {
                // Número de teléfono a llamar cuando se detecta la agitación.
                String TelefonoI = "2665104886";

                // Crea un Intent para realizar una llamada.
                Intent intent = new Intent(Intent.ACTION_CALL);

                // Define el número de teléfono en el Intent.
                intent.setData(Uri.parse("tel:" + TelefonoI));

                // Añade una bandera para iniciar la actividad de la llamada en una nueva tarea.
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Inicia la llamada a través del contexto.
                context.startActivity(intent);
            }

            // Actualiza los valores anteriores de aceleración para el próximo cálculo.
            last_x = x;
            last_y = y;
            last_z = z;
        }
    }
}


}
