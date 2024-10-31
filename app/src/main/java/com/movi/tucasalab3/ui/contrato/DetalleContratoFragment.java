package com.movi.tucasalab3.ui.contrato;

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
import com.movi.tucasalab3.databinding.FragmentDetalleContratoBinding;
import com.movi.tucasalab3.models.Contrato;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DetalleContratoFragment extends Fragment {

    private DetalleContratoViewModel mViewModel;
    private FragmentDetalleContratoBinding binding;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleContratoBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(DetalleContratoViewModel.class);

        mViewModel.getItemM().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {

                binding.editTextIdContrato.setText("Numero Contrato: "+String.valueOf(contrato.getId_Contrato()));

                LocalDate dateInicio = LocalDate.parse(contrato.getFecha_Inicio(), DateTimeFormatter.ISO_DATE_TIME);
                String SdateInicio = dateInicio.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                binding.editTextFechaInicio.setText("Inicio Contrato: "+SdateInicio);

                LocalDate dateFin = LocalDate.parse(contrato.getFecha_Finalizacion(), DateTimeFormatter.ISO_DATE_TIME);
                String SdateFin = dateFin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                binding.editTextFechaFinalizacion.setText("Fin Contrato: "+SdateFin);

                binding.editTextIdInquilino.setText("Inquilino: "+String.valueOf(contrato.getInquilino().getNombre()+"  "+contrato.getInquilino().getApellido()));
                binding.editTextIdInmueble.setText("Inmueble: "+String.valueOf(contrato.getId_Inmueble())+"Direc: "+contrato.getInmueble().getDireccion());
                binding.editTextMonto.setText("Monto: "+String.valueOf(contrato.getMonto()));
                binding.ediTextPropietarios.setText("Propietario: "+contrato.getInmueble().getPropietario().getNombre()+ "  "+contrato.getInmueble().getPropietario().getApellido());
                binding.ediTextMesesAlquilados.setText("Meses de Alquiler: "+String.valueOf(contrato.getMeses()));
                binding.textViewInmueble.setText("Otros Datos del Inmueble: "+contrato.getInmueble().getCondicion()+ "Uso: "+contrato.getInmueble().getUso()+ "Servicios:  "+contrato.getInmueble().getServicios());
                binding.textViewInquilino.setText("Otros Datos del Inquilino: "+contrato.getInquilino().getEmail()+ "Tel:  "+contrato.getInquilino().getTelefono() );



            }

        });
        binding.btPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.verPagos(view);
            }
        });


        mViewModel.CargarItem(getArguments());

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}