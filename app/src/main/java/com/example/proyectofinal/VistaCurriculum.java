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

public class VistaCurriculum extends AppCompatActivity {
    TextView tv_nombre, tv_correo, tv_telefono, tv_edad, tv_estatura, tv_direccion, tv_profesion, tv_ingreso, tv_estadocivil, tv_nacionalidad, tv_segundo_idioma, tv_tercer_idioma, tv_discapacidad, tv_estudios;
    CircleImageView imageView;
    String id, nombre, correo, imagen, telefono, edad, estatura, direccion, profesion, ingreso, estado_civil, nacionalidad, segundo_idioma, tercer_idioma, discapacidad, estudios;
    Button editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_curriculum);

        tv_nombre = findViewById(R.id.nombre);
        tv_correo = findViewById(R.id.correo);
        imageView = findViewById(R.id.imagen);
        tv_telefono = findViewById(R.id.telefono);
        tv_edad = findViewById(R.id.edad);
        tv_estatura = findViewById(R.id.estatura);
        tv_direccion = findViewById(R.id.direccion);
        tv_profesion = findViewById(R.id.profesion);
        tv_ingreso = findViewById(R.id.ingreso);
        tv_estadocivil = findViewById(R.id.estado_civil);
        tv_nacionalidad = findViewById(R.id.nacionalidad);
        tv_segundo_idioma = findViewById(R.id.segundo_idioma);
        tv_tercer_idioma = findViewById(R.id.tercer_idioma);
        tv_discapacidad = findViewById(R.id.discapacidad);
        tv_estudios = findViewById(R.id.nivel_estudios);
        editar = findViewById(R.id.editar);

        informacion();

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VistaCurriculum.this, MenuOpciones.class);
                intent.putExtra("opcion", 1);
                startActivity(intent);
            }
        });
    }

    public void informacion() {
        id = obtenerId();
        String url = "http://jfsproyecto.online/informacionEmpleado.php?id=" + id;
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
                                            nombre = response.getString("nombre");
                                            correo = response.getString("correo");
                                            imagen = response.getString("imagen");
                                            telefono = response.getString("telefono");
                                            edad = response.getString("edad");
                                            estatura = response.getString("estatura");
                                            direccion = response.getString("direccion");
                                            profesion = response.getString("profesion");
                                            ingreso = response.getString("ingreso");
                                            estado_civil = response.getString("estado_civil");
                                            nacionalidad = response.getString("nacionalidad");
                                            segundo_idioma = response.getString("segundo_idioma");
                                            tercer_idioma = response.getString("tercer_idioma");
                                            discapacidad = response.getString("discapacidad");
                                            estudios = response.getString("nivel_estudios");

                                            revisar();

                                            tv_nombre.setText("Nombre:\n" + nombre);
                                            tv_correo.setText("Correo:\n" + correo);
                                            Glide.with(VistaCurriculum.this).load(imagen).into(imageView);
                                            tv_telefono.setText("Teléfono: " + telefono);
                                            tv_edad.setText("Edad: " + edad + " años");
                                            tv_estatura.setText("Estatura: " + estatura + " cm");
                                            tv_direccion.setText("Dirección: " + direccion);
                                            tv_profesion.setText("Profesion: " + profesion);
                                            tv_ingreso.setText("Ingreso deseado: $" + ingreso);
                                            tv_estadocivil.setText("Estado civil: " + estado_civil);
                                            tv_nacionalidad.setText("Nacionalidad: " + nacionalidad);
                                            tv_segundo_idioma.setText("Segundo idioma: " + segundo_idioma);
                                            tv_tercer_idioma.setText("Tercer idioma: " + tercer_idioma);
                                            tv_discapacidad.setText("Discapacidad: " + discapacidad);
                                            tv_estudios.setText("Nivel de estudios: " + estudios);
                                            break;
                                        case "FALLIDO":
                                            Toast.makeText(VistaCurriculum.this, "Fallo de conexión", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(VistaCurriculum.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(VistaCurriculum.this);
        x.add(peticion);
    }

    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(VistaCurriculum.this);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

    public void revisar(){
        //Estado civil
        if (estado_civil.equals("1")){estado_civil="Soltero";}
        if (estado_civil.equals("2")){estado_civil="Divorciado";}
        if (estado_civil.equals("3")){estado_civil="Viudo";}
        if (estado_civil.equals("4")){estado_civil="Casado";}
        if (estado_civil.equals("5")){estado_civil="Union libre";}
        //Idiomas 2
        if (segundo_idioma.equals("1")){segundo_idioma="Ingles";}
        if (segundo_idioma.equals("2")){segundo_idioma="Chino";}
        if (segundo_idioma.equals("3")){segundo_idioma="Frances";}
        if (segundo_idioma.equals("4")){segundo_idioma="Aleman";}
        if (segundo_idioma.equals("5")){segundo_idioma="Italiano";}
        if (segundo_idioma.equals("6")){segundo_idioma="Ruso";}
        if (segundo_idioma.equals("7")){segundo_idioma="Japones";}
        if (segundo_idioma.equals("8")){segundo_idioma="Portugues";}
        if (segundo_idioma.equals("9")){segundo_idioma="Otro";}
        if (segundo_idioma.equals("10")){segundo_idioma="Ninguno";}
        //Idiomas 3
        if (tercer_idioma.equals("1")){tercer_idioma="Ingles";}
        if (tercer_idioma.equals("2")){tercer_idioma="Chino";}
        if (tercer_idioma.equals("3")){tercer_idioma="Frances";}
        if (tercer_idioma.equals("4")){tercer_idioma="Aleman";}
        if (tercer_idioma.equals("5")){tercer_idioma="Italiano";}
        if (tercer_idioma.equals("6")){tercer_idioma="Ruso";}
        if (tercer_idioma.equals("7")){tercer_idioma="Japones";}
        if (tercer_idioma.equals("8")){tercer_idioma="Portugues";}
        if (tercer_idioma.equals("9")){tercer_idioma="Otro";}
        if (tercer_idioma.equals("10")){tercer_idioma="Ninguno";}
        //Discapacidad
        if (discapacidad.equals("1")){discapacidad="Auditiva";}
        if (discapacidad.equals("2")){discapacidad="Mental Intelectual";}
        if (discapacidad.equals("3")){discapacidad="Mental Psicosocial";}
        if (discapacidad.equals("4")){discapacidad="Motriz";}
        if (discapacidad.equals("5")){discapacidad="Verbal";}
        if (discapacidad.equals("6")){discapacidad="Visual";}
        if (discapacidad.equals("7")){discapacidad="Ninguno";}
        //Estudios
        if (estudios.equals("1")){estudios="Preescolar";}
        if (estudios.equals("2")){estudios="Primaria";}
        if (estudios.equals("3")){estudios="Secundaria";}
        if (estudios.equals("4")){estudios="Bachillerato general";}
        if (estudios.equals("5")){estudios="Bachillerato tecnologico";}
        if (estudios.equals("6")){estudios="Profesional tecnico";}
        if (estudios.equals("7")){estudios="Licenciatura";}
        if (estudios.equals("8")){estudios="Maestria";}
        if (estudios.equals("9")){estudios="Doctorado";}
        if (estudios.equals("10")){estudios="Otro";}
        //Nacionalidad
        if (nacionalidad.equals("1")){nacionalidad="Aleman";}
        if (nacionalidad.equals("2")){nacionalidad="Argentino";}
        if (nacionalidad.equals("3")){nacionalidad="Brasileño";}
        if (nacionalidad.equals("4")){nacionalidad="Canadiense";}
        if (nacionalidad.equals("5")){nacionalidad="Chileno";}
        if (nacionalidad.equals("6")){nacionalidad="Chino";}
        if (nacionalidad.equals("7")){nacionalidad="Español";}
        if (nacionalidad.equals("8")){nacionalidad="Estadounidense";}
        if (nacionalidad.equals("9")){nacionalidad="Frances";}
        if (nacionalidad.equals("10")){nacionalidad="Indio";}
        if (nacionalidad.equals("11")){nacionalidad="Italiano";}
        if (nacionalidad.equals("12")){nacionalidad="Japones";}
        if (nacionalidad.equals("13")){nacionalidad="Mexicano";}
        if (nacionalidad.equals("14")){nacionalidad="Ruso";}
        if (nacionalidad.equals("15")){nacionalidad="Otro";}



    }
}
