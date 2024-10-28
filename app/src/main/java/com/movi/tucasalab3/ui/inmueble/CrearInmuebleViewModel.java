package com.movi.tucasalab3.ui.inmueble;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.movi.tucasalab3.R;
import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.retrofit.ApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearInmuebleViewModel extends AndroidViewModel {
    private Uri uri;
    private MutableLiveData<Uri> uriMutableLiveData;
    private Context context;

    public CrearInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Uri> getUriMutable() {
        if (uriMutableLiveData == null) {
            uriMutableLiveData = new MutableLiveData<>();
        }
        return uriMutableLiveData;
    }

    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            uri = data.getData();
            uriMutableLiveData.setValue(uri);
        }
    }
    // Método para obtener la ruta real del archivo desde la URI
    public String getRealPathFromURI() {
        // Inicializa la variable `result` que almacenará la ruta real del archivo
        String result = null;

        // Verifica si `uri` no es nulo, lo que indica que se ha pasado una URI válida
        if (uri != null) {
            // Define un array de strings `proj` que contiene la columna de interés:
            // MediaStore.Images.Media.DATA, que contiene la ruta del archivo de imagen
            String[] proj = { MediaStore.Images.Media.DATA };

            // Crea un cursor consultando el ContentResolver para acceder a los datos
            // del almacenamiento de imágenes, usando la URI y el array `proj`
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);

            // Verifica que el cursor no sea nulo, lo cual significa que la consulta fue exitosa
            if (cursor != null) {
                // Mueve el cursor al primer resultado (debería haber solo uno en este caso)
                if (cursor.moveToFirst()) {
                    // Obtiene el índice de la columna `DATA` en el cursor
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    // Usa el índice para obtener la ruta del archivo como un String
                    result = cursor.getString(column_index);
                }
                // Cierra el cursor para liberar los recursos
                cursor.close();
            }
        }
        // Retorna la ruta real del archivo o `null` si no se encontró
        return result;
    }

    public void cargarInmueble(View view, String direccion, String uso, String ambientes, String bano, String condicion,
                               String descripcion, String servicios, String precio, String superficie, String tipo,
                               boolean patio, boolean cochera, boolean estado) {

        // Convierte el tipo de inmueble a un entero según la opción seleccionada
        int tipoInt;
        switch (tipo) {
            case "Casa":
                tipoInt = 1;
                break;
            case "Departamento":
                tipoInt = 2;
                break;
            case "Local":
                tipoInt = 3;
                break;
            default:
                tipoInt = 0; // Usamos 0 como valor por defecto si el tipo no es reconocido
                break;
        }

        // Conversión de ambientes, tamano (superficie), y precio a sus tipos respectivos
        int ambientesInt = Integer.parseInt(ambientes);
        double tamanoDouble = Double.parseDouble(superficie);
        double precioDouble = Double.parseDouble(precio);

        String imagePath = getRealPathFromURI();

        if (imagePath != null) {

           // imagePath = imagePath.toLowerCase(); // Convierte la ruta a minúsculas
            File file = new File(imagePath);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
            MultipartBody.Part imagen = MultipartBody.Part.createFormData("imagen", file.getName(), requestFile);

            RequestBody Direccion = RequestBody.create(MediaType.parse("application/json"), direccion);
            RequestBody Uso = RequestBody.create(MediaType.parse("application/json"), uso);
            RequestBody Ambientes = RequestBody.create(MediaType.parse("application/json"), String.valueOf(ambientesInt));
            RequestBody Bano = RequestBody.create(MediaType.parse("application/json"), bano);
            RequestBody Condicion = RequestBody.create(MediaType.parse("application/json"), condicion);
            RequestBody Servicios = RequestBody.create(MediaType.parse("application/json"), servicios);
            RequestBody Tamano = RequestBody.create(MediaType.parse("application/json"), String.valueOf(tamanoDouble));
            RequestBody Descripcion = RequestBody.create(MediaType.parse("application/json"), descripcion);
            RequestBody Patio = RequestBody.create(MediaType.parse("application/json"), String.valueOf(patio ? 1 : 0));
            RequestBody Cochera = RequestBody.create(MediaType.parse("application/json"), String.valueOf(cochera ? 1 : 0));
            RequestBody Estado_Inmueble = RequestBody.create(MediaType.parse("application/json"), String.valueOf(estado ? 1 : 0));
            RequestBody id_Tipo_Inmueble = RequestBody.create(MediaType.parse("application/json"), String.valueOf(tipoInt));
            RequestBody Precio = RequestBody.create(MediaType.parse("application/json"), String.valueOf(precioDouble));

            // Llama a tu servicio API para crear el inmueble, pasando estos RequestBody como parámetros.

             String token = ApiClient.obtenerToken(context);

            ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliaria(context);
            Call<Inmueble> llamada = api.crearInmueble(token, Direccion, Uso, Bano, Condicion, Descripcion, Servicios, Tamano,
                    Patio, Cochera, id_Tipo_Inmueble, Ambientes, Precio, Estado_Inmueble, imagen);

            llamada.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful()) {

                        Toast.makeText(context, "Inmueble Agregado", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.nav_inmueble);

                    } else {

                        try {
                            String errorResponse = response.errorBody().string();
                            String errorMessage = "";

                            try {
                                JSONObject jsonObject = new JSONObject(errorResponse);
                                if (jsonObject.has("errors")) {

                                    Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                // Si no se pudo analizar como JSON, el mensaje de error es la respuesta tal cual
                                errorMessage = errorResponse;
                            }

                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                public void onFailure(Call<Inmueble> call, Throwable t) {
                    Log.d("salida falla ",t.getMessage());
                }

            });


        }else{
            Toast.makeText(context, "La imagen es obligatoria", Toast.LENGTH_SHORT).show();
        }






    }






}



