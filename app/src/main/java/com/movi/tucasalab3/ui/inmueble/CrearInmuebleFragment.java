package com.movi.tucasalab3.ui.inmueble;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.movi.tucasalab3.R;
import com.movi.tucasalab3.databinding.FragmentCrearInmuebleBinding;

public class CrearInmuebleFragment extends Fragment {

    private CrearInmuebleViewModel mViewModel;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;
    private FragmentCrearInmuebleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCrearInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicialización del ViewModel
        mViewModel = new ViewModelProvider(this).get(CrearInmuebleViewModel.class);

        // Inicialización del Spinner
        String[] tiposInmueble = {"Casa","Apartamento","Oficina","Local Comercial", "Departamento", "Estudio","Duplex","Chalet","Cabaña"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, tiposInmueble);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinTipoInmueble.setAdapter(adapter);

        binding.spinTipoInmueble.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTipo = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Seleccionaste: " + selectedTipo, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Opcional
            }
        });

        // Llamada a abrirGaleria
        abrirGaleria();

        // Observador para la URI de la imagen
        mViewModel.getUriMutable().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.inmuebleImage.setImageURI(uri);
            }
        });

        // Configuración del botón para cargar la imagen
        binding.btCargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    arl.launch(intent);

            }
        });

        binding.btCreainmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.cargarInmueble(view,
                        binding.etDireccion.getText().toString(),
                        binding.etUso.getText().toString(),
                        binding.etAmbientes.getText().toString(),
                        binding.etBano.getText().toString(),
                        binding.etCondicion.getText().toString(),
                        binding.etDescripcion.getText().toString(),
                        binding.etServicios.getText().toString(),
                        binding.etPrecio.getText().toString(),
                        binding.etSuperficie.getText().toString(),
                        binding.spinTipoInmueble.getSelectedItem().toString(),
                        binding.chekPatio.isChecked(),
                        binding.checkCochera.isChecked(),
                        binding.checkEstado.isChecked()

                );
            }
        });



        return root;
    }

    // Mueve la inicialización del ViewModel aquí y no en onActivityCreated.
    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                mViewModel.recibirFoto(result);
            }
        });
}
}