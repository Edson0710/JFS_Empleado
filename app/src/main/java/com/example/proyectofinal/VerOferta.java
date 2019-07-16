package com.example.proyectofinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class VerOferta extends AppCompatActivity {
    String nombre, id, empresa_nombre, imagen, id_empleado, id_empresa;
    TextView tv_nombre, tv_empresa;
    CircleImageView imageView;
    Button postularse, comentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_oferta);

        nombre = getIntent().getExtras().getString("nombre");
        empresa_nombre = getIntent().getExtras().getString("empresa_nombre");
        imagen = getIntent().getExtras().getString("imagen");
        id = getIntent().getExtras().getString("id");
        id_empresa = getIntent().getExtras().getString("id_empresa");
        id_empleado = obtenerId();

        tv_nombre = findViewById(R.id.nombre);
        tv_empresa = findViewById(R.id.empresa);
        imageView = findViewById(R.id.imagen);
        comentarios = findViewById(R.id.comentarios);
        postularse = findViewById(R.id.postularse);

        tv_nombre.setText(nombre);
        tv_empresa.setText("Empresa: "+empresa_nombre);
        Glide.with(VerOferta.this).load(imagen).into(imageView);

        postularse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Postularse();
                Intent intent = new Intent(VerOferta.this, OfertasEmpleos.class);
                startActivity(intent);
                Toast.makeText(VerOferta.this, "Te has postulado con éxito", Toast.LENGTH_SHORT).show();
            }
        });

        comentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerOferta.this, Comentarios.class);
                intent.putExtra("id", id_empresa);
                startActivity(intent);
            }
        });

    }

    public void Postularse(){
        String url = "http://jfsproyecto.online/postularseEmpleado.php?id_oferta=" + id +
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
        RequestQueue x = Volley.newRequestQueue(VerOferta.this);
        x.add(peticion);
    }

    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(VerOferta.this);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

}
