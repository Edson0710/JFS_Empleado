package com.example.proyectofinal.Adaptadores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.proyectofinal.MisOfertas;
import com.example.proyectofinal.OfertasEmpleos;
import com.example.proyectofinal.Pojo.Oferta;
import com.example.proyectofinal.R;
import com.example.proyectofinal.VerOferta;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MisOfertasAdapter extends RecyclerView.Adapter<MisOfertasAdapter.MyViewHolder> {

    private Context mContext;
    private List<Oferta> mData;
    String nombre, id, id_empleado, empresa_nombre, imagen, id_empresa;

    public MisOfertasAdapter(Context mContext, List<Oferta> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public MisOfertasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view;
        view = View.inflate(mContext, R.layout.item_mioferta, null);

        final MisOfertasAdapter.MyViewHolder viewHolder = new MisOfertasAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MisOfertasAdapter.MyViewHolder holder, final int position) {

        holder.tv_nombre.setText(mData.get(position).getNombre());
        holder.tv_profesion.setText(mData.get(position).getPuesto());
        Glide.with(mContext).load(mData.get(position).getImagen()).into(holder.imageView);
        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VerOferta.class);
                nombre = holder.tv_nombre.getText().toString();
                id = mData.get(holder.getAdapterPosition()).getId();
                empresa_nombre = mData.get(holder.getAdapterPosition()).getEmpresa_nombre();
                imagen = mData.get(holder.getAdapterPosition()).getImagen();
                id_empresa = mData.get(holder.getAdapterPosition()).getId_empresa();
                intent.putExtra("nombre", nombre);
                intent.putExtra("id", id);
                intent.putExtra("empresa_nombre", empresa_nombre);
                intent.putExtra("imagen", imagen);
                intent.putExtra("id_empresa", id_empresa);
                intent.putExtra("desaparecer", 1);
                mContext.startActivity(intent);
            }
        });

        holder.cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = mData.get(holder.getAdapterPosition()).getId();
                id_empleado = obtenerId();
                Mensaje();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre, tv_profesion;
        Button ver, cancelar;
        CircleImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);


            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_profesion = itemView.findViewById(R.id.tv_profesion);
            ver = itemView.findViewById(R.id.ver_oferta);
            cancelar = itemView.findViewById(R.id.cancelar);
            imageView = itemView.findViewById(R.id.imagen);

        }

    }

    public void Cancelar(){
        String url = "http://jfsproyecto.online/cancelarEmpleado.php?id_oferta=" + id +
                "&id_empleado=" + id_empleado;
        JsonObjectRequest peticion = new JsonObjectRequest
                (
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String valor = response.getString("Estado");

                                    switch (valor) {
                                        case "EXITOSO":
                                            break;
                                        case "FALLIDO":
                                            break;
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(VerOferta.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(mContext);
        x.add(peticion);
    }

    public void Mensaje(){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(mContext);
        myBuild.setMessage("¿Estás seguro de que quieres cancelar tu postulación?");
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Cancelar();
                Toast.makeText(mContext, "Has cancelado con éxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, MisOfertas.class);
                mContext.startActivity(intent);
            }
        });
        myBuild.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = myBuild.create();
        dialog.show();
    }

    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }



}