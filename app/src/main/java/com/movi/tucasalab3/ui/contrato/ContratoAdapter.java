package com.movi.tucasalab3.ui.contrato;



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
import com.movi.tucasalab3.ui.inquilino.InquilinoAdapter;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ContratoViewHolder> {
    private List<Inmueble> listaInmuebleContrato;
    private LayoutInflater inflater;
    private Context context;

    public ContratoAdapter(List<Inmueble>listaInmuebleContrato, LayoutInflater inflater, Context context){
        this.listaInmuebleContrato = listaInmuebleContrato;
        this.inflater = inflater;
        this.context = context;
    }
    @NonNull
    @Override
    public ContratoAdapter.ContratoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tarjeta_contrato, parent, false);
        return new ContratoAdapter.ContratoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.ContratoViewHolder holder, int position) {
        Inmueble inmueble = listaInmuebleContrato.get(position);
        holder.tvInmuebleContratoDireccion.setText(inmueble.getDireccion());
        holder.tvInmuebleContratoInfo.setText(String.format("%s, %s ambientes, ", inmueble.getUso(), inmueble.getAmbientes()));

        // Cargar imagen (verificar que no sea nulo o vacío)
        if (inmueble.getImagen() != null && !inmueble.getImagen().isEmpty()) {
            try {
                Glide.with(context)
                        //.load( listaInmuebles.get(position).getImagen())
                        .load(URLBASEIMG + listaInmuebleContrato.get(position).getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .override(210, 238)
                        .into(holder.ivInmuebleContrato);

            } catch (NumberFormatException e) {
                // Manejar el caso si la imagen no es un ID de recurso
                Glide.with(context).load(inmueble.getImagen()).into(holder.ivInmuebleContrato);
            }
        } else {
            // Imagen por defecto si es nula o vacía
            holder.ivInmuebleContrato.setImageResource(R.drawable.tucasa);
        }
    }

    @Override
    public int getItemCount() {
        return listaInmuebleContrato.size();
    }


    public class ContratoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvInmuebleContratoDireccion, tvInmuebleContratoInfo;
        private ImageView ivInmuebleContrato;

        public ContratoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInmuebleContratoDireccion = itemView.findViewById(R.id.tvInmuebleContratoDireccion);
            tvInmuebleContratoInfo = itemView.findViewById(R.id.tvInmuebleContratoInfo);
            ivInmuebleContrato = itemView.findViewById(R.id.ivInmuebleContrato);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Inmueble inmueble = listaInmuebleContrato.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("contrato", inmueble);
                    Navigation.findNavController(view).navigate(R.id.detalleContratoFragment, bundle);
                }
            });
        }
    }

    }
