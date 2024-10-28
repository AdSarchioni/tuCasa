package com.movi.tucasalab3.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movi.tucasalab3.R;
import com.movi.tucasalab3.databinding.FragmentPerfilBinding;
import com.movi.tucasalab3.models.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private FragmentPerfilBinding binding;
    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel= new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.getmPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                binding.etPerfilNroPropietario.setText(String.valueOf(p.getId_Propietario()));
                binding.etPerfilDni.setText(p.getDni());
                binding.etPerfilNombre.setText(p.getNombre());
                binding.etPerfilApellido.setText(p.getApellido());
                binding.etPerfilTelefono.setText(p.getTelefono());
                binding.etPerfilEmail.setText(p.getEmail());
                binding.etPerfilDireccion.setText(p.getDireccion());

            }
        });

        mViewModel.cargarPerfil();

        binding.btnPerfilEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dni = binding.etPerfilDni.getText().toString();
                String nombre = binding.etPerfilNombre.getText().toString();
                String apellido = binding.etPerfilApellido.getText().toString();
                String telefono = binding.etPerfilTelefono.getText().toString();
                String direccion = binding.etPerfilDireccion.getText().toString();

                mViewModel.editarPropietario(dni, nombre, apellido, direccion, telefono);
            }
        });
binding.btnCambiarContra.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        NavHostFragment.findNavController(PerfilFragment.this)
                .navigate(R.id.action_nav_perfil_to_cambioCFragment);
    }
});

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

}