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
import com.example.proyectofinal.Adaptadores.ComentariosAdapter;
import com.example.proyectofinal.Adaptadores.FinalizadosAdapter;
import com.example.proyectofinal.Pojo.Comentario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrabajosFinalizados extends AppCompatActivity {
    private String JSON_URL;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Comentario> lista;
    private RecyclerView recycler;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos_finalizados);
        id = obtenerId();
        lista = new ArrayList<Comentario>();
        obtenerOfertas();
    }

    public void obtenerOfertas() {
        JSON_URL = "http://jfsproyecto.online/finalizadosEmpleado.php?id=" + id;
        ArrayRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Comentario comentario = new Comentario();
                        comentario.setComentario(jsonObject.getString("comentario"));
                        comentario.setCalificacion(jsonObject.getString("calificacion"));
                        comentario.setNombre(jsonObject.getString("nombre"));

                        lista.add(comentario);


                    } catch (JSONException e) {
                        e.getCause();
                    }


                }

                if (lista == null || lista.size() == 0) {
                    AlertDialog.Builder myBuild = new AlertDialog.Builder(TrabajosFinalizados.this);
                    myBuild.setMessage("Aún no has finalizado ningun trabajo");
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
                Toast.makeText(TrabajosFinalizados.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue = Volley.newRequestQueue(TrabajosFinalizados.this);
        requestQueue.add(ArrayRequest);

    }

    public void setuprecyclerview(List<Comentario> lista) {
        recycler = (RecyclerView) findViewById(R.id.recyclerview_finalizados);
        FinalizadosAdapter myadapter = new FinalizadosAdapter(this, lista);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myadapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TrabajosFinalizados.this, MenuOpciones.class);
        intent.putExtra("opcion", 0);
        startActivity(intent);
    }

    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TrabajosFinalizados.this);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }
}
