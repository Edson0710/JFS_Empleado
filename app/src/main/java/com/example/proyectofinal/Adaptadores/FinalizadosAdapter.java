package com.example.proyectofinal.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectofinal.Pojo.Comentario;
import com.example.proyectofinal.R;

import java.util.List;

public class FinalizadosAdapter extends RecyclerView.Adapter<FinalizadosAdapter.MyViewHolder> {

    private Context mContext;
    private List<Comentario> mData;

    public FinalizadosAdapter(Context mContext, List<Comentario> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public FinalizadosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        view = View.inflate(mContext, R.layout.item_finalizados, null);

        final FinalizadosAdapter.MyViewHolder viewHolder = new FinalizadosAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FinalizadosAdapter.MyViewHolder holder, int position) {

        holder.tv_nombre.setText(mData.get(position).getNombre());
        holder.tv_comentarios.setText(mData.get(position).getComentario());
        holder.tv_calificacion.setText("Calificacion: "+mData.get(position).getCalificacion());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre;
        TextView tv_comentarios;
        TextView tv_calificacion;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_calificacion = itemView.findViewById(R.id.tv_calificacion);
            tv_comentarios = itemView.findViewById(R.id.tv_comentario);
            tv_nombre = itemView.findViewById(R.id.tv_nombre);

        }

    }


}