package com.movi.tucasalab3;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.movi.tucasalab3.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {
    private LoginActivityViewModel vm;
    private ActivityLoginBinding binding;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        setContentView(binding.getRoot());

        vm.getMUsuario().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                vm.llamarLogin(binding.etClave.getText().toString(), binding.etUsuario.getText().toString());

            }
        });
        //veo si en el formulario esta puesto el correo para resetar la contraseña
        binding.tvOlvidoContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = binding.etUsuario.getText().toString();

                    vm.olvidoContrasena(correo);

            }
        });


        


      //  controlo que el usuario sea correcto y le pregunto si realmente quiere seretear
        vm.getTokenValid().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isValid) {

                // Si el token es válido, sigue como está
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Confirmación")
                        .setMessage("¿Está seguro de que desea resetear su contraseña?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                vm.resetContrasena();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
        vm.getTokenInvalido().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                // si el token es false muestra un mensaje de error
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Error")
                        .setMessage("El usuario ingresado es incorrecto.")
                        .setPositiveButton("Volver", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); // Cierra el diálogo
                            }
                        })
                        .show();


            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);

        }
        // Inicializa el SensorManager para gestionar sensores del dispositivo.
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Obtiene el sensor de aceleración del dispositivo.
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Registra el listener de eventos del sensor, especificando el tipo de sensor y la frecuencia de actualización.
        senSensorManager.registerListener((SensorEventListener) this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Llama al método `HacerLlamada` del ViewModel y le pasa el evento del sensor.
        vm.HacerLlamada(sensorEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onPause(){
        super.onPause();
        // Libera el listener del sensor cuando la actividad está en pausa para ahorrar recursos.
        senSensorManager.unregisterListener(this);

    }
    protected void onResume(){
        super.onResume();
        // Registra nuevamente el listener del sensor para detectar agitación cuando la actividad está activa.
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


}