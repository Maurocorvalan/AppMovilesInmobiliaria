package com.ulp.logininmo.ui.contratos;

import static com.ulp.logininmo.request.ApiClient.urls;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ulp.logininmo.R;
import com.ulp.logininmo.models.Inmueble;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.InmuebleViewHolder> {
    private List<Inmueble> listaInmueble;
    private Context context;
    private LayoutInflater li;

    public ContratoAdapter(List<Inmueble> listaInmueble, Context context, LayoutInflater li) {
        this.listaInmueble = listaInmueble;
        this.context = context;
        this.li = li;
    }

    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new InmuebleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
        Inmueble inmueble = listaInmueble.get(position);
        holder.tvDireccion.setText(inmueble.getDireccion());
        holder.tvUso.setText(inmueble.getUso());
        holder.tvValor.setText(String.valueOf(inmueble.getValor()));
        holder.tvAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
        Glide.with(context)
                .load(urls + inmueble.getImagen()) // Suponiendo que 'imagen' es la URL relativa de la imagen
                .placeholder(null) // Imagen de reemplazo mientras se carga
                .error("null") // Imagen en caso de error
                .into(holder.foto);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble);
                Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_content_navigation).navigate(R.id.verContrato, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaInmueble.size();
    }

    public void setInmuebles(List<Inmueble> inmuebles) {
        this.listaInmueble = inmuebles;
        notifyDataSetChanged();
    }

    public static class InmuebleViewHolder extends RecyclerView.ViewHolder {
        public TextView  tvDireccion, tvUso, tvValor, tvAmbientes;
        public ImageView foto;
        public CardView cardView;

        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvUso = itemView.findViewById(R.id.tvUso);
            tvValor = itemView.findViewById(R.id.tvValor);
            tvAmbientes = itemView.findViewById(R.id.tvAmbientes);
            foto = itemView.findViewById(R.id.foto);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
