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
import com.example.proyectofinal.Adaptadores.BusquedaAdapter;
import com.example.proyectofinal.Pojo.Oferta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Busqueda extends AppCompatActivity {

    private String JSON_URL;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Oferta> lista;
    private RecyclerView recycler;
    String id;
    String profesion, sueldo, edad, estatura, nacionalidad, estado, estudios, segundo, tercer, discapacidad;
    int ImportanciaProfesion, ImportanciaSueldo, ImportanciaEstatura, ImportanciaEdad, ImportanciaNacionalidad, ImportanciaEstado, ImportanciaEstudios, ImportanciaSegundo, ImportanciaTercer, ImportanciaDiscapacidad;
    float total = 0, porcentaje;
    int importancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        profesion = getIntent().getExtras().getString("profesion").toLowerCase();
        ImportanciaProfesion = getIntent().getExtras().getInt("ImportanciaProfesion");
        sueldo = getIntent().getExtras().getString("sueldo");
        ImportanciaSueldo = getIntent().getExtras().getInt("ImportanciaSueldo");
        edad = getIntent().getExtras().getString("edad");
        ImportanciaEdad = getIntent().getExtras().getInt("ImportanciaEdad");
        estatura = getIntent().getExtras().getString("estatura");
        ImportanciaEstatura = getIntent().getExtras().getInt("ImportanciaEstatura");
        nacionalidad = getIntent().getExtras().getString("nacionalidad");
        ImportanciaNacionalidad = getIntent().getExtras().getInt("ImportanciaNacionalidad");
        estado = getIntent().getExtras().getString("estado");
        ImportanciaEstado = getIntent().getExtras().getInt("ImportanciaEstado");
        estudios = getIntent().getExtras().getString("estudios");
        ImportanciaEstudios = getIntent().getExtras().getInt("ImportanciaEstudios");
        segundo = getIntent().getExtras().getString("segundo");
        ImportanciaSegundo = getIntent().getExtras().getInt("ImportanciaSegundo");
        tercer = getIntent().getExtras().getString("tercer");
        ImportanciaTercer = getIntent().getExtras().getInt("ImportanciaTercer");
        discapacidad = getIntent().getExtras().getString("discapacidad");
        ImportanciaDiscapacidad = getIntent().getExtras().getInt("ImportanciaDiscapacidad");
        importancia = getIntent().getExtras().getInt("total");


        lista = new ArrayList<Oferta>();
        obtenerResultado();
    }

    //Funcion para obtener la lista de resultados
    public void obtenerResultado() {
        JSON_URL = "http://jfsproyecto.online/resultadosEmpleado.php";
        ArrayRequest = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        total = 0;
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
                        oferta.setNacionalidad(jsonObject.getString("nacionalidad"));
                        oferta.setEstado_civil(jsonObject.getString("estado_civil"));
                        oferta.setSegundo_idioma(jsonObject.getString("segundo_idioma"));
                        oferta.setTercer_idioma(jsonObject.getString("tercer_idioma"));
                        oferta.setDiscapacidad(jsonObject.getString("discapacidad"));
                        oferta.setNivel_estudios(jsonObject.getString("nivel_estudios"));

                        oferta.setEdad(jsonObject.getString("edad"));
                        if (jsonObject.getString("edad").equals(edad)) {
                            total += ImportanciaEdad;
                        }

                        oferta.setEstatura(jsonObject.getString("estatura"));
                        if (jsonObject.getString("estatura").equals(estatura)) {
                            total += ImportanciaEstatura;
                        }

                        oferta.setProfesion(jsonObject.getString("profesion"));
                        if (jsonObject.getString("profesion").toLowerCase().equals(profesion.toLowerCase())) {
                            total += ImportanciaProfesion;
                        }

                        oferta.setSueldo(jsonObject.getString("sueldo"));
                        if (jsonObject.getString("sueldo").equals(sueldo)) {
                            total += ImportanciaSueldo;
                        }

                        oferta.setEstado_civil(jsonObject.getString("estado_civil"));
                        if (jsonObject.getString("estado_civil").equals(estado)) {
                            total += ImportanciaEstado;
                        }
                        oferta.setNacionalidad(jsonObject.getString("nacionalidad"));
                        if (jsonObject.getString("nacionalidad").equals(nacionalidad)) {
                            total += ImportanciaNacionalidad;
                        }
                        oferta.setSegundo_idioma(jsonObject.getString("segundo_idioma"));
                        if (jsonObject.getString("segundo_idioma").equals(segundo)) {
                            total += ImportanciaSegundo;
                        }
                        oferta.setTercer_idioma(jsonObject.getString("tercer_idioma"));
                        if (jsonObject.getString("tercer_idioma").equals(tercer)) {
                            total += ImportanciaTercer;
                        }
                        oferta.setDiscapacidad(jsonObject.getString("discapacidad"));
                        if (jsonObject.getString("discapacidad").equals(discapacidad)) {
                            total += ImportanciaDiscapacidad;
                        }
                        oferta.setNivel_estudios(jsonObject.getString("nivel_estudios"));
                        if (jsonObject.getString("nivel_estudios").equals(estudios)) {
                            total += ImportanciaEstudios;
                        }
                        porcentaje = (total / importancia) * 100;
                        oferta.setPorcentaje(porcentaje);

                        if (porcentaje >= 50) {
                            lista.add(oferta);
                        }

                    } catch (JSONException e) {
                        e.getCause();
                    }

                }

                if (lista == null || lista.size() == 0) {
                    AlertDialog.Builder myBuild = new AlertDialog.Builder(Busqueda.this);
                    myBuild.setMessage("No hay resultados que coincidan más del 50% de los parametros seleccionados en las ofertas existentes\nSugerencias de búsqueda:\n- Intenta usar palabras más generales\n- Comprueba que no haya faltas de ortografía\n- Usa palabras completas y no abreviaturas");
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
                Toast.makeText(Busqueda.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue = Volley.newRequestQueue(Busqueda.this);
        requestQueue.add(ArrayRequest);

    }

    //Funcion para configurar el RecyclerView
    public void setuprecyclerview(List<Oferta> lista) {
        recycler = (RecyclerView) findViewById(R.id.recyclerview_busqueda);
        BusquedaAdapter myadapter = new BusquedaAdapter(this, lista);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(myadapter);
    }

}
