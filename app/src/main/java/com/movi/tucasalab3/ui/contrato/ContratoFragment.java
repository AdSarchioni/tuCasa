package com.movi.tucasalab3.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movi.tucasalab3.R;
import com.movi.tucasalab3.databinding.FragmentContratoBinding;
import com.movi.tucasalab3.databinding.FragmentInmuebleBinding;
import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.ui.inmueble.InmuebleAdapter;
import com.movi.tucasalab3.ui.inmueble.InmuebleViewModel;

import java.util.List;

public class ContratoFragment extends Fragment {

    private ContratoViewModel mViewModel;
    private FragmentContratoBinding binding;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

    mViewModel.getmListaInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
        @Override
        public void onChanged(List<Inmueble> inmuebles) {
            ContratoAdapter adapter = new ContratoAdapter(inmuebles,getLayoutInflater(),getContext());
            GridLayoutManager grid = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);

            binding.listaContratos.setAdapter(adapter);
            binding.listaContratos.setLayoutManager(grid);
        }
    });

        mViewModel.imprimirInmuebles();

        return root;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}