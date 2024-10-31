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


//

//            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                return;
//            }
//            fused.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location) {
//                    if (location != null) {
//                        LatLng yo = new LatLng(location.getLatitude(), location.getLongitude());
//                        googleMap.addMarker(new MarkerOptions().position(yo).title("Yo"));
//                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yo, 15));
//                    }
//                }
//            });
        }
    }
}