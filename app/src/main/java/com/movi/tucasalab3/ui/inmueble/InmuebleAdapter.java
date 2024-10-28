package com.movi.tucasalab3.ui.inmueble;

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
import com.movi.tucasalab3.retrofit.ApiClient;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.InmuebleViewHolder> {
   private List<Inmueble> listaInmuebles;
   private LayoutInflater inflater;
   private Context context;

    public InmuebleAdapter(List<Inmueble> listaInmuebles, LayoutInflater inflater, Context context) {
        this.listaInmuebles = listaInmuebles;
        this.inflater = inflater;
        this.context = context;
    }

    @NonNull
    @Override
    public InmuebleAdapter.InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tarjeta, parent, false);
        return new InmuebleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.InmuebleViewHolder holder, int position) {
        Inmueble inmueble = listaInmuebles.get(position);

        // Convierte el ID del inmueble a String
        holder.tvNombre.setText(String.valueOf(inmueble.getId_Inmueble()));

        // Muestra la dirección
        holder.tvDireccion.setText(inmueble.getDireccion());

        // Muestra la información adicional del inmueble
        holder.tvInfo.setText(inmueble.getUso() + ", "
                + inmueble.getAmbientes() + " ambientes, "
              //  + inmueble.getBanos() + " baños, "

//                + (inmueble.getCochera() == 1 ? "Con cochera" : "Sin cochera") + ", "
//                + (inmueble.getPatio() == 1 ? "Con patio" : "Sin patio") + ", "
                + "$" + inmueble.getPrecio() );
           //     + inmueble.getSuperficie() + " m²")


        // Cargar imagen (verificar que no sea nulo o vacío)
        if (inmueble.getImagen() != null && !inmueble.getImagen().isEmpty()) {
            try {
                Glide.with(context)
                        //.load( listaInmuebles.get(position).getImagen())
                        .load(URLBASEIMG + listaInmuebles.get(position).getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(210,238)
                        .into(holder.ivFotoCasa);

            } catch (NumberFormatException e) {
                // Manejar el caso si la imagen no es un ID de recurso
                Glide.with(context).load(inmueble.getImagen()).into(holder.ivFotoCasa);
            }
        } else {
            // Imagen por defecto si es nula o vacía
            holder.ivFotoCasa.setImageResource(R.drawable.tucasa);
        }
    }



    @Override
    public int getItemCount() {
        return listaInmuebles.size();
    }



    public class InmuebleViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre, tvDireccion, tvInfo;
        private ImageView ivFotoCasa;

        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvInfo = itemView.findViewById(R.id.tvInfo);
            ivFotoCasa = itemView.findViewById(R.id.ivFotoCasa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicion = getAdapterPosition();

                    Inmueble inmueble = listaInmuebles.get(posicion);

                    Bundle bundle = new Bundle();

                    bundle.putSerializable("iteminmueble", inmueble);

                    Navigation.findNavController(view).navigate(R.id.detalleInmuebleFragment,bundle);
                }
            });
        }
        }
    }
