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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.Adaptadores.MisOfertasAdapter;
import com.example.proyectofinal.Adaptadores.OfertasAdapter;
import com.example.proyectofinal.Pojo.Oferta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MisOfertas extends AppCompatActivity {

    private String JSON_URL;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Oferta> lista;
    private RecyclerView recycler;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_ofertas);
        id = obtenerId();
        lista = new ArrayList<Oferta>();
        obtenerMisOfertas();
    }
    //Funcion para obtener la lista de ofertas
    public void obtenerMisOfertas() {
        JSON_URL = "http://jfsproyecto.online/verMisOfertasEmpleado.php?id=" + id;
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
                        oferta.setImagen(jsonObject.getString("imagen"));
                        oferta.setId_empresa(jsonObject.getString("id_empresa"));

                        lista.add(oferta);

                    } catch (JSONException e) {
                        e.getCause();
                    }


                }

                if (lista == null || lista.size() == 0) {
                    AlertDialog.Builder myBuild = new AlertDialog.Builder(MisOfertas.this);
                    myBuild.setMessage("No tienes ninguna oferta");
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
                Toast.makeText(MisOfertas.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue = Volley.newRequestQueue(MisOfertas.this);
        requestQueue.add(ArrayRequest);

    }

    public void setuprecyclerview(List<Oferta> lista) {
        recycler = (RecyclerView) findViewById(R.id.recyclerview_misofertas);
        MisOfertasAdapter myadapter = new MisOfertasAdapter(this, lista);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myadapter);
    }

    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MisOfertas.this);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MisOfertas.this, MenuOpciones.class);
        startActivity(intent);
    }
}