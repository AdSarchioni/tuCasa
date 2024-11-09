package com.movi.tucasalab3.ui.pago;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movi.tucasalab3.R;
import com.movi.tucasalab3.models.Pago;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.PagoViewHolder> {

    private List<Pago> listaPagos;
    private LayoutInflater inflater;
    private Context context;

    public PagoAdapter(List<Pago> listaPagos, LayoutInflater inflater, Context context) {
        this.listaPagos = listaPagos;
        this.inflater = inflater;
        this.context = context;
    }


    @NonNull
    @Override
    public PagoAdapter.PagoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = inflater.inflate(R.layout.tarjeta_pago, parent, false);
       return new PagoAdapter.PagoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.PagoViewHolder holder, int position) {
        Pago pago = listaPagos.get(position);
        holder.tvCodigoPago.setText(String.valueOf(pago.getId_Pago()));
        holder.tvImportePago.setText(String .valueOf(pago.getImporte()));
        holder.tvNContrato.setText(String .valueOf(pago.getContrato().getId_Contrato()));
        LocalDate fechaPago = LocalDate.parse(listaPagos.get(position).getFecha(), DateTimeFormatter.ISO_DATE_TIME);
        String SfechaPago = fechaPago.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        holder.tvFechaPago.setText(SfechaPago);


        holder.tvNroPago.setText(String .valueOf(pago.getCuotaPaga()));
    }

    @Override
    public int getItemCount() {
        return listaPagos.size();
    }

    public class PagoViewHolder extends RecyclerView.ViewHolder {
           private TextView tvCodigoPago;
           private TextView tvImportePago;
           private TextView tvFechaPago;
           private TextView tvNroPago;
           private TextView tvNContrato;

        public PagoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoPago = itemView.findViewById(R.id.TVCodigoPago);
            tvImportePago = itemView.findViewById(R.id.TVInportePago);
            tvFechaPago = itemView.findViewById(R.id.TVFechaPago);
            tvNroPago = itemView.findViewById(R.id.TVNroPago);
            tvNContrato = itemView.findViewById(R.id.tvNContrato);
    }
}
}



