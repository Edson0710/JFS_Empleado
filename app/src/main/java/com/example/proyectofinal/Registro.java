package com.example.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity
        //implements View.OnClickListener
{

    Button boton_registrarse;
    Spinner EstadoCivil_opciones;
    Spinner Discapacidades_opciones;
    Spinner SegundoIdioma_opciones;
    Spinner TercerIdioma_opciones;
    Spinner NivelEstudios_opciones;

    EditText registro_Nombre, registro_Correo,
            registro_Contrasena, registro_ContrasenaConfirmar, registro_Edad, registro_Telefono,
            registro_Domicilio, registro_Estatura, registro_CURP,
            registro_Nacionalidad, registro_Ingreso, registro_Profesion;


    String EstadoCivil,NivelEstudios,SegundoIdioma,Tercer_idioma,Discapacidades;
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
        registro_Nacionalidad = findViewById(R.id.Registro_Nacionalidad);
        registro_Ingreso = findViewById(R.id.Registro_Ingreso);
        registro_Profesion = findViewById(R.id.Registro_Profesion);


        boton_registrarse = findViewById(R.id.boton_registrarse);



        //________________________________________avr

        boton_registrarse = (Button) findViewById(R.id.boton_registrarse);
        // boton_registrarse.setOnClickListener(this);


//-----------------------------------------------------------------------------------------------------------------//

        EstadoCivil_opciones = findViewById(R.id.Spinner_EstadoCivil);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (Registro.this, R.array.EstadoCivil_opciones, android.R.layout.simple_spinner_item);
        EstadoCivil_opciones.setAdapter(adapter);
        EstadoCivil_opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 EstadoCivil = EstadoCivil_opciones.getSelectedItem().toString();
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------///

        boton_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionBD();
                //Intent intentReg = new Intent(Registro.this, VistaPrincipal.class);
                //Registro.this.startActivity(intentReg);

            }
        });

    }
    public void ConexionBD(){
        String url = "http://192.168.10.26/Registro.php?nombre="+registro_Nombre.getText().toString()+"&correo="+registro_Correo.getText().toString()+"&contrasena="+registro_Contrasena.getText().toString()+"&edad="+registro_Edad.getText().toString()+"&telefono="+registro_Telefono.getText().toString()+"&domicilio="+registro_Domicilio.getText().toString()+"&estatura="+registro_Estatura.getText().toString()+
                "&curp="+registro_CURP.getText().toString()+"&nacionalidad="+registro_Nacionalidad.getText().toString()+"&ingreso="+registro_Ingreso.getText().toString()+"&profesion="+registro_Profesion.getText().toString()+"&estudios=Bueno&estado_civil=Casado&discapacidad=Muert&segundo_idioma=Nahuatl&tercer_idioma=Fransua";


        final Intent iniciarAdmin = new Intent(Registro.this,MainActivity.class);
        JsonObjectRequest peticion = new JsonObjectRequest
                (
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String valor = response.getString("sucess");
                                    switch(valor) {
                                        case "true":
                                            startActivity(iniciarAdmin);
                                            break;
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(Registro.this,"No conexion",Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(Registro.this);
        x.add(peticion);
    }


}
