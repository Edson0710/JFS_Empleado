package com.example.proyectofinal.Adaptadores;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.Pojo.Oferta;
import com.example.proyectofinal.R;
import com.example.proyectofinal.VerOferta;


import java.util.List;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.MyViewHolder> {

    private Context mContext;
    private List<Oferta> mData;
    String nombre, id, empresa_nombre, imagen, id_empresa;

    public OfertasAdapter(Context mContext, List<Oferta> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public OfertasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        view = View.inflate(mContext, R.layout.item_oferta, null);

        final OfertasAdapter.MyViewHolder viewHolder = new OfertasAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tv_nombre.setText(mData.get(position).getNombre());

        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = holder.tv_nombre.getText().toString();
                id = mData.get(holder.getAdapterPosition()).getId();
                empresa_nombre = mData.get(holder.getAdapterPosition()).getEmpresa_nombre();
                imagen = mData.get(holder.getAdapterPosition()).getImagen();
                id_empresa = mData.get(holder.getAdapterPosition()).getId_empresa();
                Intent intent = new Intent(mContext, VerOferta.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("id", id);
                intent.putExtra("empresa_nombre", empresa_nombre);
                intent.putExtra("imagen", imagen);
                intent.putExtra("id_empresa", id_empresa);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre;
        Button ver;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            ver = itemView.findViewById(R.id.ver_oferta);

        }

    }


}