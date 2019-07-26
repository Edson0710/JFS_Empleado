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
    String nombre, id, empresa_nombre, imagen, id_empleado, id_empresa, correo, telefono, giro, direccion, calificacion, transporte, comisiones, bonos, otro1, otro2, otro3,
    profesion, puesto, sueldo, edad, estatura, nacionalidad, estado_civil, segundo_idioma, tercer_idioma, nivel_estudios, discapacidad;
    TextView tv_nombre, tv_empresa, tv_correo, tv_telefono, tv_giro, tv_direccion, tv_calificacion, tv_transporte, tv_comisiones, tv_bonos, tv_otro1, tv_otro2, tv_otro3,
    tv_profesion, tv_puesto, tv_sueldo, tv_edad, tv_estatura, tv_nacionaludad, tv_estado_civil, tv_segundo_idioma, tv_tercer_idioma, tv_nivel_estudios, tv_discapacidad;
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
        int desaparecer = getIntent().getExtras().getInt("desaparecer");
        correo = getIntent().getExtras().getString("correo");
        telefono = getIntent().getExtras().getString("telefono");
        giro = getIntent().getExtras().getString("giro");
        direccion = getIntent().getExtras().getString("direccion");
        calificacion = getIntent().getExtras().getString("calificacion");
        transporte = getIntent().getExtras().getString("transporte");
        comisiones = getIntent().getExtras().getString("comisiones");
        bonos = getIntent().getExtras().getString("bonos");
        otro1 = getIntent().getExtras().getString("otro1");
        otro2 = getIntent().getExtras().getString("otro2");
        otro3 = getIntent().getExtras().getString("otro3");
        profesion = getIntent().getExtras().getString("profesion");
        puesto = getIntent().getExtras().getString("puesto");
        sueldo = getIntent().getExtras().getString("sueldo");
        edad = getIntent().getExtras().getString("edad");
        estatura = getIntent().getExtras().getString("estatura");
        nacionalidad = getIntent().getExtras().getString("nacionalidad");
        estado_civil = getIntent().getExtras().getString("estado_civil");
        segundo_idioma = getIntent().getExtras().getString("segundo_idioma");
        tercer_idioma = getIntent().getExtras().getString("tercer_idioma");
        nivel_estudios = getIntent().getExtras().getString("nivel_estudios");
        discapacidad = getIntent().getExtras().getString("discapacidad");

        revisar();

        tv_nombre = findViewById(R.id.nombre);
        tv_empresa = findViewById(R.id.empresa);
        tv_calificacion = findViewById(R.id.calificacion);
        imageView = findViewById(R.id.imagen);
        comentarios = findViewById(R.id.comentarios);
        postularse = findViewById(R.id.postularse);
        tv_correo = findViewById(R.id.correo);
        tv_telefono = findViewById(R.id.telefono);
        tv_giro = findViewById(R.id.giro);
        tv_direccion = findViewById(R.id.direccion);
        tv_transporte = findViewById(R.id.transporte);
        tv_bonos = findViewById(R.id.bonos);
        tv_comisiones = findViewById(R.id.comisiones);
        tv_otro1 = findViewById(R.id.otro1);
        tv_otro2 = findViewById(R.id.otro2);
        tv_otro3 = findViewById(R.id.otro3);
        tv_profesion = findViewById(R.id.profesion);
        tv_puesto = findViewById(R.id.puesto);
        tv_sueldo = findViewById(R.id.sueldo);
        tv_edad  = findViewById(R.id.edad);
        tv_estatura = findViewById(R.id.estatura);
        tv_nacionaludad = findViewById(R.id.nacionalidad);
        tv_estado_civil = findViewById(R.id.estado_civil);
        tv_segundo_idioma = findViewById(R.id.segundo_idioma);
        tv_tercer_idioma = findViewById(R.id.tercer_idioma);
        tv_nivel_estudios = findViewById(R.id.nivel_estudios);
        tv_discapacidad = findViewById(R.id.discapacidad);

        if (desaparecer == 1){
        postularse.setVisibility(View.INVISIBLE);
        } else {
          postularse.setVisibility(View.VISIBLE);
        }

        tv_nombre.setText(nombre);
        tv_empresa.setText("Empresa: "+empresa_nombre);
        tv_calificacion.setText("Calificación: " + calificacion);
        tv_correo.setText("Correo: " + correo);
        tv_telefono.setText("Teléfono: " + telefono);
        tv_giro.setText("Giro empresa: " + giro);
        tv_direccion.setText("Dirección: " + direccion);
        tv_transporte.setText("Transporte: " + transporte);
        tv_comisiones.setText("Comisiones: " + comisiones);
        tv_bonos.setText("Bonos: " + bonos);
        tv_otro1.setText("Otro: " + otro1);
        tv_otro2.setText("Otro: " + otro2);
        tv_otro3.setText("Otro: " + otro3);
        tv_profesion.setText("Profesion recomendada: " + profesion);
        tv_puesto.setText("Puesto: " + puesto);
        tv_sueldo.setText("Sueldo: $" + sueldo);
        tv_edad.setText("Edad recomendada: " + edad + " años");
        tv_estatura.setText("Estatura recomendada: " + estatura + " cm");
        tv_nacionaludad.setText("Nacionalidad: " + nacionalidad);
        tv_estado_civil.setText("Estado Civil: " + estado_civil);
        tv_segundo_idioma.setText("Segundo idioma: " + segundo_idioma);
        tv_tercer_idioma.setText("Tercer idioma: " + tercer_idioma);
        tv_nivel_estudios.setText("Nivel de estudios: " + nivel_estudios);
        tv_discapacidad.setText("Discapacidad: " + discapacidad);

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

    public void revisar(){
        //Transporte
        if (transporte.equals("1")){transporte="Sí";} else {transporte="No";}
        //Comisiones
        if (comisiones.equals("1")){comisiones="Sí";} else {comisiones="No";}
        //Bonos
        if (bonos.equals("1")){bonos="Sí";} else {bonos="No";}

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
        if (nivel_estudios.equals("1")){nivel_estudios="Preescolar";}
        if (nivel_estudios.equals("2")){nivel_estudios="Primaria";}
        if (nivel_estudios.equals("3")){nivel_estudios="Secundaria";}
        if (nivel_estudios.equals("4")){nivel_estudios="Bachillerato general";}
        if (nivel_estudios.equals("5")){nivel_estudios="Bachillerato tecnologico";}
        if (nivel_estudios.equals("6")){nivel_estudios="Profesional tecnico";}
        if (nivel_estudios.equals("7")){nivel_estudios="Licenciatura";}
        if (nivel_estudios.equals("8")){nivel_estudios="Maestria";}
        if (nivel_estudios.equals("9")){nivel_estudios="Doctorado";}
        if (nivel_estudios.equals("10")){nivel_estudios="Otro";}
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
