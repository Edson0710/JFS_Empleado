package com.example.proyectofinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class Codigo extends AppCompatActivity {
    String nombre, correo, contra, edad, estatura, telefono, domicilio, curp, profesion, ingreso, puesto;
    String EstadoCivil, NivelEstudios, SegundoIdioma, Tercer_idioma, Discapacidades, Nacionalidad;
    String UPLOAD_URL = "http://jfsproyecto.online/registroEmpleado.php";
    EditText validar;
    int dato;
    String cod;
    Button comprobar;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);

        validar = findViewById(R.id.codigo);
        comprobar = findViewById(R.id.comprobar);

        nombre = getIntent().getExtras().getString("nombre");
        correo = getIntent().getExtras().getString("correo");
        contra = getIntent().getExtras().getString("contra");
        edad = getIntent().getExtras().getString("edad");
        estatura = getIntent().getExtras().getString("estatura");
        telefono = getIntent().getExtras().getString("telefono");
        domicilio = getIntent().getExtras().getString("domicilio");
        curp = getIntent().getExtras().getString("curp");
        profesion = getIntent().getExtras().getString("profesion");
        puesto = getIntent().getExtras().getString("puesto");
        ingreso = getIntent().getExtras().getString("ingreso");
        Nacionalidad = getIntent().getExtras().getString("nacionalidad");
        EstadoCivil = getIntent().getExtras().getString("estado");
        NivelEstudios = getIntent().getExtras().getString("estudios");
        SegundoIdioma = getIntent().getExtras().getString("segundo");
        Tercer_idioma = getIntent().getExtras().getString("tercer");
        Discapacidades = getIntent().getExtras().getString("discapacidad");
        bitmap = getIntent().getParcelableExtra("bitmap");

        Random r = new Random();
        final int numero = r.nextInt(1111 - 1) + 7777;

        validacion(numero, correo);

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cod = validar.getText().toString().trim();
                dato = Integer.parseInt(cod);
                if (dato == numero) {
                    Registrar();
                    Intent intent = new Intent(Codigo.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Codigo.this, "El codigo no coincide", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void validacion(int codigo, String correo) {
        String url = "http://jfsproyecto.online/enviarCodigo.php?correo=" + correo + "&codigo=" + codigo;
        final JsonObjectRequest peticion = new JsonObjectRequest
                (
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String valor = response.getString("Estado");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(Codigo.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(Codigo.this);
        x.add(peticion);
    }

    private void Registrar() {
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this, "Registrando...", "Espere por favor...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(Codigo.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(Codigo.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);


                //Creación de parámetros
                Map<String, String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put("nombre", nombre);
                params.put("correo", correo);
                params.put("contra", contra);
                params.put("edad", edad);
                params.put("direccion", domicilio);
                params.put("telefono", telefono);
                params.put("estatura", estatura);
                params.put("curp", curp);
                params.put("nacionalidad", Nacionalidad);
                params.put("ingreso", ingreso);
                params.put("puesto", puesto);
                params.put("profesion", profesion);
                params.put("idioma1", SegundoIdioma);
                params.put("idioma2", Tercer_idioma);
                params.put("estado", EstadoCivil);
                params.put("estudios", NivelEstudios);
                params.put("discapacidad", Discapacidades);
                params.put("imagen", imagen);
                //Parámetros de retorno
                return params;
            }
        };

        //Creación de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }

    public String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


}

