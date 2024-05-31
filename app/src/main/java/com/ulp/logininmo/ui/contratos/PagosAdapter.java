package com.ulp.logininmo.ui.contratos;

import static com.ulp.logininmo.request.ApiClient.urls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ulp.logininmo.R;
import com.ulp.logininmo.models.Inmueble;
import com.ulp.logininmo.models.Pago;
import com.ulp.logininmo.ui.inmuebles.InmuebleAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagosAdapter  extends RecyclerView.Adapter<PagosAdapter.PagosViewHolder> {
    private List<Pago> pagos = new ArrayList<>();
    private Context context;

    public PagosAdapter( Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public PagosAdapter.PagosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempagos, parent, false);
        return new PagosAdapter.PagosViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.PagosViewHolder holder, int position) {
        Pago pago = pagos.get(position);
        holder.tvIdPago.setText(String.valueOf(pago.getIdPago()));
        holder.tvCodigoContratoPago.setText(String.valueOf(pago.getIdContrato()));
        holder.tvMonto.setText(String.valueOf(pago.getMonto()));
        holder.tvFechaPago.setText(String.valueOf(pago.getFechaPago()));

    }
    @Override
    public int getItemCount() {
        return pagos.size();
    }
    public void setPagos(List<Pago> pagos){
        this.pagos = pagos;
        notifyDataSetChanged();
    }



    public static class PagosViewHolder extends RecyclerView.ViewHolder{
        public TextView tvIdPago, tvCodigoContratoPago, tvMonto, tvFechaPago;

        public PagosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdPago = itemView.findViewById(R.id.tvIdPago);
            tvCodigoContratoPago = itemView.findViewById(R.id.tvCodigoContratoPago);
            tvMonto = itemView.findViewById(R.id.tvMonto);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);




        }
    }

}
