package com.example.proyectofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.Adaptadores.OfertasAdapter;
import com.example.proyectofinal.Pojo.Oferta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OfertasEmpleos extends AppCompatActivity {

    private String JSON_URL;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Oferta> lista;
    private RecyclerView recycler;
    String id;
    Button actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertas_empleos);
        actualizar = findViewById(R.id.actualizar);
        lista = new ArrayList<Oferta>();
        obtenerOfertas();

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfertasEmpleos.this, OfertasEmpleos.class);
                startActivity(intent);
            }
        });
}

    public void obtenerOfertas() {
        id = obtenerId();
        JSON_URL = "http://jfsproyecto.online/verOfertasEmpleado.php?activo=" + 1 + "&id=" + id;
        ArrayRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Oferta oferta = new Oferta();
                        oferta.setNombre(jsonObject.getString("nombre"));
                        oferta.setId(jsonObject.getString("id"));
                        oferta.setEmpresa_nombre(jsonObject.getString("empresa_nombre"));
                        oferta.setCalificacion(jsonObject.getString("calificacion"));
                        oferta.setImagen(jsonObject.getString("imagen"));
                        oferta.setId_empresa(jsonObject.getString("id_empresa"));
                        oferta.setProfesion(jsonObject.getString("profesion"));
                        oferta.setPuesto(jsonObject.getString("puesto"));
                        oferta.setCorreo(jsonObject.getString("correo"));
                        oferta.setTelefono(jsonObject.getString("telefono"));
                        oferta.setGiro(jsonObject.getString("giro"));
                        oferta.setDireccion(jsonObject.getString("direccion"));
                        oferta.setTransporte(jsonObject.getString("transporte"));
                        oferta.setComisiones(jsonObject.getString("comisiones"));
                        oferta.setBonos(jsonObject.getString("bonos"));
                        oferta.setOtro1(jsonObject.getString("otro1"));
                        oferta.setOtro2(jsonObject.getString("otro2"));
                        oferta.setOtro3(jsonObject.getString("otro3"));
                        oferta.setSueldo(jsonObject.getString("sueldo"));
                        oferta.setEdad(jsonObject.getString("edad"));
                        oferta.setEstatura(jsonObject.getString("estatura"));
                        oferta.setNacionalidad(jsonObject.getString("nacionalidad"));
                        oferta.setEstado_civil(jsonObject.getString("estado_civil"));
                        oferta.setSegundo_idioma(jsonObject.getString("segundo_idioma"));
                        oferta.setTercer_idioma(jsonObject.getString("tercer_idioma"));
                        oferta.setDiscapacidad(jsonObject.getString("discapacidad"));
                        oferta.setNivel_estudios(jsonObject.getString("nivel_estudios"));

                        lista.add(oferta);

                    } catch (JSONException e) {
                        e.getCause();
                    }


                }

                if (lista == null || lista.size() == 0) {
                    AlertDialog.Builder myBuild = new AlertDialog.Builder(OfertasEmpleos.this);
                    myBuild.setMessage("No existen ofertas");
                    myBuild.setTitle("JFS");
                    myBuild.setCancelable(false);
                    myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = myBuild.create();
                    dialog.show();
                }


                setuprecyclerview(lista);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OfertasEmpleos.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue = Volley.newRequestQueue(OfertasEmpleos.this);
        requestQueue.add(ArrayRequest);

    }

    public void setuprecyclerview(List<Oferta> lista) {
        recycler = (RecyclerView) findViewById(R.id.recyclerview_ofertas);
        OfertasAdapter myadapter = new OfertasAdapter(this, lista);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myadapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OfertasEmpleos.this, MenuOpciones.class);
        intent.putExtra("opcion", 0);
        startActivity(intent);
    }

    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(OfertasEmpleos.this);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }
}