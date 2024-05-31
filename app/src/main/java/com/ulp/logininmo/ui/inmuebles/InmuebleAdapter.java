package com.ulp.logininmo.ui.inmuebles;

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

import java.util.ArrayList;
import java.util.List;


public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.InmuebleViewHolder> {
    private List<Inmueble> inmuebles = new ArrayList<>();
    private Context context;
    public InmuebleAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new InmuebleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);
        holder.tvDireccion.setText(inmueble.getDireccion());
        holder.tvUso.setText(inmueble.getUso());
        holder.tvValor.setText(String.valueOf(inmueble.getValor()));
        holder.tvAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
        Glide.with(context)
                .load(urls + inmueble.getImagen()) // Suponiendo que 'imagen' es la URL relativa de la imagen
                .placeholder(null) // Imagen de reemplazo mientras se carga
                .error("null") // Imagen en caso de error
                .into(holder.foto);
        // Agregar más datos según sea necesario
        ((InmuebleViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                bundle.putSerializable("inmueble",inmueble);
                Navigation.findNavController((Activity) v.getContext(), R.id.nav_host_fragment_content_navigation).navigate(R.id.editarInmuebleFragment,bundle);

  }
});
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public void setInmuebles(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
        notifyDataSetChanged();
    }
    public static class InmuebleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDireccion;
        public TextView tvUso;
        public TextView tvValor;
        public TextView tvAmbientes;
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
            // Agregar más vistas según sea necesario
        }
    }
}
