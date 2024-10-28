package com.movi.tucasalab3.ui.inquilino;

import static com.movi.tucasalab3.retrofit.ApiClient.URLBASEIMG;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.movi.tucasalab3.R;
import com.movi.tucasalab3.models.Inmueble;
import com.movi.tucasalab3.ui.inmueble.InmuebleAdapter;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.InquilinoViewHolder> {
    private List<Inmueble> listaInmuInquilino;
    private LayoutInflater inflater;
    private Context context;

    public InquilinoAdapter(List<Inmueble> listaInmuInquilino, LayoutInflater inflater, Context context) {
        this.listaInmuInquilino = listaInmuInquilino;
        this.inflater = inflater;
        this.context = context;
    }


    @NonNull
    @Override
    public InquilinoAdapter.InquilinoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tarjetainquilino, parent, false);
        return new InquilinoAdapter.InquilinoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.InquilinoViewHolder holder, int position) {
        Inmueble inmueble = listaInmuInquilino.get(position);
        holder.tvDireccionInmuInqui.setText(inmueble.getDireccion());
        holder.tvInfoInmuInqui.setText(String.format("%s, %s ambientes, ", inmueble.getUso(), inmueble.getAmbientes()));

        // Cargar imagen (verificar que no sea nulo o vacío)
        if (inmueble.getImagen() != null && !inmueble.getImagen().isEmpty()) {
            try {
                Glide.with(context)
                        //.load( listaInmuebles.get(position).getImagen())
                        .load(URLBASEIMG + listaInmuInquilino.get(position).getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(210, 238)
                        .into(holder.ivInmuebleInquilino);

            } catch (NumberFormatException e) {
                // Manejar el caso si la imagen no es un ID de recurso
                Glide.with(context).load(inmueble.getImagen()).into(holder.ivInmuebleInquilino);
            }
        } else {
            // Imagen por defecto si es nula o vacía
            holder.ivInmuebleInquilino.setImageResource(R.drawable.tucasa);
        }
    }


    @Override
    public int getItemCount() {
        return listaInmuInquilino.size();
    }

    public class InquilinoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDireccionInmuInqui, tvInfoInmuInqui;
        private ImageView ivInmuebleInquilino;

        public InquilinoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccionInmuInqui = itemView.findViewById(R.id.tvDireccionInmuInqui);
            tvInfoInmuInqui = itemView.findViewById(R.id.tvInfoInmuInqui);
            ivInmuebleInquilino = itemView.findViewById(R.id.ivInmuebleInquilino);

      itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Inmueble inmueble = listaInmuInquilino.get(getAdapterPosition());
              Bundle bundle = new Bundle();
              bundle.putSerializable("iteminmuebleInquilino", inmueble);
              Navigation.findNavController(view).navigate(R.id.detalleInquilinoFragment, bundle);
          }
      });




       }
    }


}



