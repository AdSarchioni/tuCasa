package com.movi.tucasalab3.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movi.tucasalab3.R;
import com.movi.tucasalab3.databinding.FragmentInmuebleBinding;
import com.movi.tucasalab3.models.Inmueble;

import java.util.ArrayList;
import java.util.List;

public class InmuebleFragment extends Fragment {

    private InmuebleViewModel mViewModel;
    private FragmentInmuebleBinding binding;


    public static InmuebleFragment newInstance() {
        return new InmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


      mViewModel.getmListaInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
          @Override
          public void onChanged(List<Inmueble> inmuebles) {

              InmuebleAdapter adapter = new InmuebleAdapter(inmuebles,getLayoutInflater(),getContext());
              GridLayoutManager grid = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);

              binding.listaInmuebles.setAdapter(adapter);
              binding.listaInmuebles.setLayoutManager(grid);

          }
      });
        binding.btfFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).
                        navigate(R.id.crearInmuebleFragment);


            }
        });

       mViewModel.imprimirInmuebles();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        // TODO: Use the ViewModel
    }

}