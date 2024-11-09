package com.movi.tucasalab3.ui.home;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class HomeViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<MapaActual> mMapaActual;
    private FusedLocationProviderClient fused;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        fused = LocationServices.getFusedLocationProviderClient(application);
        context = application.getApplicationContext();
    }

    public LiveData<MapaActual> getMmapaActual() {
        if (mMapaActual == null) mMapaActual = new MutableLiveData<>();
        return mMapaActual;
    }

    public void obtenerMapa() {
        mMapaActual.setValue(new MapaActual());
    }

    public class MapaActual implements OnMapReadyCallback {
        LatLng INMOBILIARIA = new LatLng(-33.280576, -66.332482);

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(INMOBILIARIA).title("INMOBILIARIA").snippet("La mejor"));

            float zoomLevel = 14.0f; // Ajusta el nivel de zoom según sea necesario
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(INMOBILIARIA, zoomLevel));
        }
    }
}
