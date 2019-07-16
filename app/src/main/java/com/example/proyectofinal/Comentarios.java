package com.example.proyectofinal;

import android.content.DialogInterface;
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
import com.example.proyectofinal.Pojo.Comentario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Comentarios extends AppCompatActivity {
    private String JSON_URL;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Comentario> lista;
    private RecyclerView recycler;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);
        id = getIntent().getExtras().getString("id");
        lista = new ArrayList<Comentario>();
        obtenerOfertas();
    }

    public void obtenerOfertas() {
        JSON_URL = "http://jfsproyecto.online/comentariosEmpleado.php?id=" + id;
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

                        lista.add(comentario);


                    } catch (JSONException e) {
                        e.getCause();
                    }


                }

                if (lista == null || lista.size() == 0) {
                    AlertDialog.Builder myBuild = new AlertDialog.Builder(Comentarios.this);
                    myBuild.setMessage("Esta Empresa no cuenta con comentarios");
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
                Toast.makeText(Comentarios.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue = Volley.newRequestQueue(Comentarios.this);
        requestQueue.add(ArrayRequest);

    }

    public void setuprecyclerview(List<Comentario> lista) {
        recycler = (RecyclerView) findViewById(R.id.recyclerview_comentarios);
        ComentariosAdapter myadapter = new ComentariosAdapter(this, lista);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myadapter);
    }
}
