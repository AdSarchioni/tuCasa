package com.movi.tucasalab3.ui.inquilino;

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
import com.movi.tucasalab3.databinding.FragmentInmuebleBinding;
import com.movi.tucasalab3.databinding.FragmentInquilinoBinding;
import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.ui.inmueble.InmuebleViewModel;

import java.util.List;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel mViewModel;
    private FragmentInquilinoBinding binding;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

     mViewModel.getListaM().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
         @Override
         public void onChanged(List<Inmueble> inmuebles) {
             InquilinoAdapter adapter = new InquilinoAdapter(inmuebles, getLayoutInflater(), getContext());
             GridLayoutManager grid = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);

        binding.listaInmuAlquilados.setAdapter(adapter);
        binding.listaInmuAlquilados.setLayoutManager(grid);


         }
     });

mViewModel.obtenerInmueblesAlquilados();



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        // TODO: Use the ViewModel
    }

}