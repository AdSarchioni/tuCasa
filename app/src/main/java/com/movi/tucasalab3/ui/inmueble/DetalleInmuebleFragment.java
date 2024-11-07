package com.movi.tucasalab3.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.movi.tucasalab3.databinding.FragmentDetalleInmuebleBinding;
import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.retrofit.ApiClient;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel mViewModel;
    private FragmentDetalleInmuebleBinding binding;

    public static DetalleInmuebleFragment newInstance() {
        return new DetalleInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        View root = binding.getRoot();

        mViewModel.getItemM().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.TVNroPropietarioDetalle.setText(String.valueOf(inmueble.getId_Inmueble()));
                binding.TVAmbientesDetalle.setText(String.valueOf(inmueble.getAmbientes()));
                binding.TVDireccionDetalle.setText(inmueble.getDireccion());
                binding.TVPrecioDetalle.setText(String.valueOf(inmueble.getPrecio()));
                binding.TVUsoDetalle.setText(inmueble.getUso());
                binding.CBDisponibleDetalle.setChecked(inmueble.getEstado_Inmueble() == 1);
               binding.TVTipoDetalle.setText(inmueble.getServicios());
                Glide.with(getActivity())
                        .load(ApiClient.URLBASEIMG + inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(210, 238)
                        .into(binding.IMGDetalleItem);
            }
        });

        mViewModel.CargarItem(getArguments());

binding.CBDisponibleDetalle.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mViewModel.setEstado(binding.CBDisponibleDetalle.isChecked());
    }
});


            return root;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        // TODO: Use the ViewModel
    }

}
