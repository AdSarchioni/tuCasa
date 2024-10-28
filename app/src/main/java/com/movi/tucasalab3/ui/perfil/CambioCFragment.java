package com.movi.tucasalab3.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movi.tucasalab3.R;
import com.movi.tucasalab3.databinding.FragmentCambioCBinding;
import com.movi.tucasalab3.databinding.FragmentPerfilBinding;

public class CambioCFragment extends Fragment {

    private CambioCViewModel mViewModel;
    private FragmentCambioCBinding binding;

    public static CambioCFragment newInstance() {
        return new CambioCFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel= new ViewModelProvider(this).get(CambioCViewModel.class);
        binding = FragmentCambioCBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnCambioC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contrasenaActual = binding.etClaActual.getText().toString();
                String contrasenaNueva = binding.etClaNueva.getText().toString();

                mViewModel.cambiarContrasena(contrasenaActual, contrasenaNueva);
            }
        });
        return root;
    }











    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CambioCViewModel.class);
        // TODO: Use the ViewModel
    }

}