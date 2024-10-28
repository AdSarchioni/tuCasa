package com.movi.tucasalab3.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movi.tucasalab3.R;
import com.movi.tucasalab3.databinding.FragmentDetalleInquilinoBinding;
import com.movi.tucasalab3.models.Inquilino;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel mViewModel;
    private FragmentDetalleInquilinoBinding binding;

    public static DetalleInquilinoFragment newInstance() {
        return new DetalleInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);

   mViewModel.getItemM().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
       @Override
       public void onChanged(Inquilino inquilino) {
           binding.tvNumeroInquilino.setText(String.valueOf(inquilino.getId_Inquilino()));
           binding.tvDniInquilino.setText(String.valueOf(inquilino.getDni()));
           binding.tvApellidoInquilino.setText(inquilino.getApellido());
           binding.tvNombreInquilino.setText(inquilino.getNombre());
           binding.tvTelefonoInquilino.setText(inquilino.getTelefono());
           binding.tvEmailInquilino.setText(inquilino.getEmail());
          // binding.cbEstadoInquilino.setChecked(inquilino.getEstado_Inquilino()==1);

       }
   });
   mViewModel.CargarInqui(getArguments());

   return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);
        // TODO: Use the ViewModel
    }

}