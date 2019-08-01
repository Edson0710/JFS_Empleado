package com.example.proyectofinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Registro extends AppCompatActivity
        //implements View.OnClickListener
{

    Button boton_registrarse;
    Spinner EstadoCivil_opciones;
    Spinner Discapacidades_opciones;
    Spinner SegundoIdioma_opciones;
    Spinner TercerIdioma_opciones;
    Spinner NivelEstudios_opciones;
    Spinner Nacionalidad_opciones;

    String UPLOAD_URL = "http://jfsproyecto.online/registroEmpleado.php";

    EditText registro_Nombre, registro_Correo,
            registro_Contrasena, registro_ContrasenaConfirmar, registro_Edad, registro_Telefono,
            registro_Domicilio, registro_Estatura, registro_CURP, registro_Ingreso, registro_Profesion, registro_Puesto;
    CircleImageView imagen;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;


    String EstadoCivil, NivelEstudios, SegundoIdioma, Tercer_idioma, Discapacidades, Nacionalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        boton_registrarse = (Button) findViewById(R.id.boton_registrarse);


        registro_Nombre = findViewById(R.id.Registro_Nombre);
        registro_Correo = findViewById(R.id.Registro_Correo);
        registro_Contrasena = findViewById(R.id.Registro_Contraseña);
        registro_ContrasenaConfirmar = findViewById(R.id.Registro_ContraseñaConfirmar);
        registro_Edad = findViewById(R.id.Registro_Edad);
        registro_Telefono = findViewById(R.id.Registro_Telefono);
        registro_Domicilio = findViewById(R.id.Registro_Domicilio);
        registro_Estatura = findViewById(R.id.Registro_Estatura);
        registro_CURP = findViewById(R.id.Registro_CURP);
        registro_Ingreso = findViewById(R.id.Registro_Ingreso);
        registro_Profesion = findViewById(R.id.Registro_Profesion);
        registro_Puesto = findViewById(R.id.Registro_Puesto);
        imagen = findViewById(R.id.Registro_Fotografia);


        boton_registrarse = findViewById(R.id.boton_registrarse);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });//Imagen

//-----------------------------------------------------------------------------------------------------------------//

        Nacionalidad_opciones = findViewById(R.id.Spinner_Nacionalidad);

        ArrayAdapter<CharSequence> adapter0 = ArrayAdapter.createFromResource
                (Registro.this, R.array.Nacionalidad_opciones, android.R.layout.simple_spinner_item);
        Nacionalidad_opciones.setAdapter(adapter0);
        Nacionalidad_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Nacionalidad = Nacionalidad_opciones.getSelectedItem().toString();
                if (Nacionalidad.equals("Aleman")) {
                    Nacionalidad = "1";
                } else if (Nacionalidad.equals("Argentino")) {
                    Nacionalidad = "2";
                } else if (Nacionalidad.equals("Brasileño")) {
                    Nacionalidad = "3";
                } else if (Nacionalidad.equals("Canadiense")) {
                    Nacionalidad = "4";
                } else if (Nacionalidad.equals("Chileno")) {
                    Nacionalidad = "5";
                } else if (Nacionalidad.equals("Chino")) {
                    Nacionalidad = "6";
                } else if (Nacionalidad.equals("Español")) {
                    Nacionalidad = "7";
                } else if (Nacionalidad.equals("Estadounidense")) {
                    Nacionalidad = "8";
                } else if (Nacionalidad.equals("Frances")) {
                    Nacionalidad = "9";
                } else if (Nacionalidad.equals("Indio")) {
                    Nacionalidad = "10";
                } else if (Nacionalidad.equals("Italiano")) {
                    Nacionalidad = "11";
                } else if (Nacionalidad.equals("Japones")) {
                    Nacionalidad = "12";
                } else if (Nacionalidad.equals("Mexicano")) {
                    Nacionalidad = "13";
                } else if (Nacionalidad.equals("Ruso")) {
                    Nacionalidad = "14";
                } else if (Nacionalidad.equals("Otro")) {
                    Nacionalidad = "15";
                } else {
                    Nacionalidad = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//


//-----------------------------------------------------------------------------------------------------------------//

        EstadoCivil_opciones = findViewById(R.id.Spinner_EstadoCivil);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (Registro.this, R.array.EstadoCivil_opciones, android.R.layout.simple_spinner_item);
        EstadoCivil_opciones.setAdapter(adapter);
        EstadoCivil_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EstadoCivil = EstadoCivil_opciones.getSelectedItem().toString();
                if (EstadoCivil.equals("Soltero")) {
                    EstadoCivil = "1";
                } else if (EstadoCivil.equals("Divorciado")) {
                    EstadoCivil = "2";
                } else if (EstadoCivil.equals("Viudo")) {
                    EstadoCivil = "3";
                } else if (EstadoCivil.equals("Casado")) {
                    EstadoCivil = "4";
                } else if (EstadoCivil.equals("Union libre")) {
                    EstadoCivil = "5";
                } else {
                    EstadoCivil = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//

        Discapacidades_opciones = findViewById(R.id.Spinner_Discapacidades);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource
                (Registro.this, R.array.Discapacidades_opciones, android.R.layout.simple_spinner_item);
        Discapacidades_opciones.setAdapter(adapter1);
        Discapacidades_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Discapacidades = Discapacidades_opciones.getSelectedItem().toString();
                if (Discapacidades.equals("Auditiva")) {
                    Discapacidades = "1";
                } else if (Discapacidades.equals("Mental Intelectual")) {
                    Discapacidades = "2";
                } else if (Discapacidades.equals("Mental Psicosocial")) {
                    Discapacidades = "3";
                } else if (Discapacidades.equals("Motriz")) {
                    Discapacidades = "4";
                } else if (Discapacidades.equals("Verbal")) {
                    Discapacidades = "5";
                } else if (Discapacidades.equals("Visual")) {
                    Discapacidades = "6";
                } else if (Discapacidades.equals("Ninguna")) {
                    Discapacidades = "7";
                } else {
                    Discapacidades = "0";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//

        SegundoIdioma_opciones = findViewById(R.id.Spinner_SegundoIdioma);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource
                (Registro.this, R.array.SegundoIdioma_opciones, android.R.layout.simple_spinner_item);
        SegundoIdioma_opciones.setAdapter(adapter2);
        SegundoIdioma_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SegundoIdioma = SegundoIdioma_opciones.getSelectedItem().toString();
                if (SegundoIdioma.equals("Ingles")) {
                    SegundoIdioma = "1";
                } else if (SegundoIdioma.equals("Chino")) {
                    SegundoIdioma = "2";
                } else if (SegundoIdioma.equals("Frances")) {
                    SegundoIdioma = "3";
                } else if (SegundoIdioma.equals("Aleman")) {
                    SegundoIdioma = "4";
                } else if (SegundoIdioma.equals("Italiano")) {
                    SegundoIdioma = "5";
                } else if (SegundoIdioma.equals("Ruso")) {
                    SegundoIdioma = "6";
                } else if (SegundoIdioma.equals("Japones")) {
                    SegundoIdioma = "7";
                } else if (SegundoIdioma.equals("Portugues")) {
                    SegundoIdioma = "8";
                } else if (SegundoIdioma.equals("Otro")) {
                    SegundoIdioma = "9";
                } else if (SegundoIdioma.equals("Ninguno")) {
                    SegundoIdioma = "10";
                } else {
                    SegundoIdioma = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//

        TercerIdioma_opciones = findViewById(R.id.Spinner_TercerIdioma);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource
                (Registro.this, R.array.TercerIdioma_opciones, android.R.layout.simple_spinner_item);
        TercerIdioma_opciones.setAdapter(adapter3);
        TercerIdioma_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Tercer_idioma = TercerIdioma_opciones.getSelectedItem().toString();
                if (Tercer_idioma.equals("Ingles")) {
                    Tercer_idioma = "1";
                } else if (Tercer_idioma.equals("Chino")) {
                    Tercer_idioma = "2";
                } else if (Tercer_idioma.equals("Frances")) {
                    Tercer_idioma = "3";
                } else if (Tercer_idioma.equals("Aleman")) {
                    Tercer_idioma = "4";
                } else if (Tercer_idioma.equals("Italiano")) {
                    Tercer_idioma = "5";
                } else if (Tercer_idioma.equals("Ruso")) {
                    Tercer_idioma = "6";
                } else if (Tercer_idioma.equals("Japones")) {
                    Tercer_idioma = "7";
                } else if (Tercer_idioma.equals("Portugues")) {
                    Tercer_idioma = "8";
                } else if (Tercer_idioma.equals("Otro")) {
                    Tercer_idioma = "9";
                } else if (Tercer_idioma.equals("Ninguno")) {
                    Tercer_idioma = "10";
                } else {
                    Tercer_idioma = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//

        NivelEstudios_opciones = findViewById(R.id.Spinner_estudios);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource
                (Registro.this, R.array.NivelEstudios_opciones, android.R.layout.simple_spinner_item);
        NivelEstudios_opciones.setAdapter(adapter4);
        NivelEstudios_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NivelEstudios = NivelEstudios_opciones.getSelectedItem().toString();
                if (NivelEstudios.equals("Preescolar")) {
                    NivelEstudios = "1";
                } else if (NivelEstudios.equals("Primaria")) {
                    NivelEstudios = "2";
                } else if (NivelEstudios.equals("Secundaria")) {
                    NivelEstudios = "3";
                } else if (NivelEstudios.equals("Bachillerato general")) {
                    NivelEstudios = "4";
                } else if (NivelEstudios.equals("Bachillerato tecnologico")) {
                    NivelEstudios = "5";
                } else if (NivelEstudios.equals("Profesional tecnico")) {
                    NivelEstudios = "6";
                } else if (NivelEstudios.equals("Licenciatura")) {
                    NivelEstudios = "7";
                } else if (NivelEstudios.equals("Maestria")) {
                    NivelEstudios = "8";
                } else if (NivelEstudios.equals("Doctorado")) {
                    NivelEstudios = "9";
                } else if (NivelEstudios.equals("Otro")) {
                    NivelEstudios = "10";
                } else {
                    NivelEstudios = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------///

        boton_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidarDatosLlenos()) {
                        if (Contras()) {
                        Validar();
                    } else {
                        Toast.makeText(Registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }
        });

    }

    //------------------Imagen-----------------------------------------------------------------------------------//

    public String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                imagen.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //--------------------------------------------------------------------------------------------------//

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
                        Toast.makeText(Registro.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(Registro.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);


                //Creación de parámetros
                Map<String, String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put("nombre", registro_Nombre.getText().toString().trim());
                params.put("correo", registro_Correo.getText().toString().trim());
                params.put("contra", registro_Contrasena.getText().toString().trim());
                params.put("edad", registro_Edad.getText().toString().trim());
                params.put("direccion", registro_Domicilio.getText().toString().trim());
                params.put("telefono", registro_Telefono.getText().toString().trim());
                params.put("estatura", registro_Estatura.getText().toString().trim());
                params.put("curp", registro_CURP.getText().toString().trim());
                params.put("nacionalidad", Nacionalidad);
                params.put("ingreso", registro_Ingreso.getText().toString().trim());
                params.put("puesto", registro_Puesto.getText().toString().trim());
                params.put("profesion", registro_Profesion.getText().toString().trim());
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

    public boolean Contras() {
        if (registro_Contrasena.getText().toString().trim().equals(registro_ContrasenaConfirmar.getText().toString().trim())) {
            return true;
        } else {
            return false;
        }
    }

    public void Validar() {
        String url = "http://jfsproyecto.online/validarcorreoEmpleado.php?correo=" + registro_Correo.getText().toString().trim();
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

                                    switch (valor) {
                                        case "NO":
                                            Registrar();
                                            Intent intent = new Intent(Registro.this, MainActivity.class);
                                            startActivity(intent);
                                            break;
                                        case "SI":
                                            Toast.makeText(Registro.this, "Este correo ya esta siendo utilizado", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Registro.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(Registro.this);
        x.add(peticion);
    }

    public boolean ValidarDatosLlenos() {
        String nombre, correo, contra1, contra2, edad, estatura, telefono, domicilio, curp, profesion, ingreso, puesto;
        nombre = registro_Nombre.getText().toString().trim();
        correo = registro_Correo.getText().toString().trim();
        contra1 = registro_Contrasena.getText().toString().trim();
        contra2 = registro_ContrasenaConfirmar.getText().toString().trim();
        edad = registro_Edad.getText().toString().trim();
        estatura = registro_Estatura.getText().toString().trim();
        telefono = registro_Telefono.getText().toString().trim();
        domicilio = registro_Domicilio.getText().toString().trim();
        curp =  registro_CURP.getText().toString().trim();
        profesion = registro_Profesion.getText().toString().trim();
        ingreso = registro_Ingreso.getText().toString().trim();
        puesto = registro_Puesto.getText().toString().trim();
        if (nombre.equals("") || correo.equals("") || contra1.equals("") || contra2.equals("") ||
                edad.equals("") || estatura.equals("") || telefono.equals("") || domicilio.equals("") ||
               curp.equals("") || EstadoCivil.equals("0") || Nacionalidad.equals("0") ||
                profesion.equals("") || ingreso.equals("") || puesto.equals("") ||
                NivelEstudios.equals("0") || SegundoIdioma.equals("0") || Tercer_idioma.equals("0") || Discapacidades.equals("0")) {
            Toast.makeText(Registro.this, "Complete de manera correcta los campos", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (registro_Telefono.getText().toString().trim().length() == 10){
                if (registro_CURP.getText().toString().trim().length() == 18){
                    return true;
                } else {
                    Toast.makeText(Registro.this, "Ingrese una CURP válida", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else{
                Toast.makeText(Registro.this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }


}
