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

import com.bumptech.glide.Glide;
import com.example.proyectofinal.Pojo.Oferta;
import com.example.proyectofinal.R;
import com.example.proyectofinal.VerOferta;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.MyViewHolder> {

    private Context mContext;
    private List<Oferta> mData;
    String nombre, id, empresa_nombre, imagen, id_empresa, correo, telefono, giro, direccion, calificacion, transporte, comisiones, bonos,
    otro1, otro2, otro3, profesion, puesto, sueldo, edad, estatura, nacionalidad, estado_civil, segundo_idioma, tercer_idioma, discapacidad, nivel_estudios;

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
        holder.tv_profesion.setText(mData.get(position).getPuesto());
        Glide.with(mContext).load(mData.get(position).getImagen()).into(holder.imageView);
        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = holder.tv_nombre.getText().toString();
                id = mData.get(holder.getAdapterPosition()).getId();
                empresa_nombre = mData.get(holder.getAdapterPosition()).getEmpresa_nombre();
                imagen = mData.get(holder.getAdapterPosition()).getImagen();
                id_empresa = mData.get(holder.getAdapterPosition()).getId_empresa();
                correo = mData.get(holder.getAdapterPosition()).getCorreo();
                telefono = mData.get(holder.getAdapterPosition()).getTelefono();
                giro = mData.get(holder.getAdapterPosition()).getGiro();
                direccion = mData.get(holder.getAdapterPosition()).getDireccion();
                calificacion = mData.get(holder.getAdapterPosition()).getCalificacion();
                transporte = mData.get(holder.getAdapterPosition()).getTransporte();
                comisiones = mData.get(holder.getAdapterPosition()).getComisiones();
                bonos = mData.get(holder.getAdapterPosition()).getBonos();
                otro1 = mData.get(holder.getAdapterPosition()).getOtro1();
                otro2 = mData.get(holder.getAdapterPosition()).getOtro2();
                otro3 = mData.get(holder.getAdapterPosition()).getOtro3();
                profesion = mData.get(holder.getAdapterPosition()).getProfesion();
                puesto = mData.get(holder.getAdapterPosition()).getPuesto();
                sueldo = mData.get(holder.getAdapterPosition()).getSueldo();
                edad = mData.get(holder.getAdapterPosition()).getEdad();
                estatura = mData.get(holder.getAdapterPosition()).getEstatura();
                nacionalidad = mData.get(holder.getAdapterPosition()).getNacionalidad();
                estado_civil = mData.get(holder.getAdapterPosition()).getEstado_civil();
                segundo_idioma = mData.get(holder.getAdapterPosition()).getSegundo_idioma();
                tercer_idioma = mData.get(holder.getAdapterPosition()).getTercer_idioma();
                nivel_estudios = mData.get(holder.getAdapterPosition()).getNivel_estudios();
                discapacidad = mData.get(holder.getAdapterPosition()).getDiscapacidad();
                Intent intent = new Intent(mContext, VerOferta.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("id", id);
                intent.putExtra("empresa_nombre", empresa_nombre);
                intent.putExtra("imagen", imagen);
                intent.putExtra("id_empresa", id_empresa);
                intent.putExtra("correo", correo);
                intent.putExtra("telefono", telefono);
                intent.putExtra("giro", giro);
                intent.putExtra("direccion", direccion);
                intent.putExtra("calificacion", calificacion);
                intent.putExtra("transporte", transporte);
                intent.putExtra("comisiones", comisiones);
                intent.putExtra("bonos", bonos);
                intent.putExtra("otro1", otro1);
                intent.putExtra("otro2", otro2);
                intent.putExtra("otro3", otro3);
                intent.putExtra("profesion", profesion);
                intent.putExtra("puesto", puesto);
                intent.putExtra("sueldo", sueldo);
                intent.putExtra("edad", edad);
                intent.putExtra("estatura", estatura);
                intent.putExtra("nacionalidad", nacionalidad);
                intent.putExtra("estado_civil", estado_civil);
                intent.putExtra("segundo_idioma", segundo_idioma);
                intent.putExtra("tercer_idioma", tercer_idioma);
                intent.putExtra("nivel_estudios", nivel_estudios);
                intent.putExtra("discapacidad", discapacidad);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre, tv_profesion;
        Button ver;
        CircleImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_profesion = itemView.findViewById(R.id.tv_profesion);
            ver = itemView.findViewById(R.id.ver_oferta);
            imageView = itemView.findViewById(R.id.imagen);
        }

    }


}