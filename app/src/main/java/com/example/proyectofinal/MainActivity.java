package com.example.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class MainActivity extends AppCompatActivity {

    TextView tv_registrar;
    EditText correoET, passET;
    String correo, pass;
    Button boton_iniciar;
    RadioButton sesion;
    private boolean isActivate;
    private static final String STRING_PREFERENCES = "preferencias";
    private static final String PREFERENCE_ESTADO_BUTTON = "estado.button";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (obtenerEstado()) {
            Intent intent = new Intent(MainActivity.this, MenuOpciones.class);
            intent.putExtra("opcion",0);
            startActivity(intent);
        }

        tv_registrar= (TextView) findViewById(R.id.tv_Registrar);
        correoET = (EditText) findViewById(R.id.Usuario);
        passET = (EditText) findViewById(R.id.Contraseña);
        sesion = (RadioButton) findViewById(R.id.sesion);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(intentReg);

            }
        });
        boton_iniciar = (Button) findViewById(R.id.boton_Iniciar);
        boton_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = correoET.getText().toString();
                pass = passET.getText().toString();
                if (correo.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }
        });

        isActivate = sesion.isChecked(); //DESACTIVADO

        sesion.setOnClickListener(new View.OnClickListener() {
            //ACTIVADO
            @Override
            public void onClick(View v) {
                if (isActivate) {
                    sesion.setChecked(false);
                }
                isActivate = sesion.isChecked();

            }
        });

    }

    public void login() {
            String url = "http://jfsproyecto.online/loginEmpleado.php?correo=" + correo +
                "&pass=" + pass;
        final Intent intent = new Intent(MainActivity.this, MenuOpciones.class);
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
                                            String id = response.getString("id");
                                            String nombre = response.getString("nombre");
                                            String correo = response.getString("correo");
                                            String imagen = response.getString("imagen");
                                            String puesto = response.getString("puesto");
                                            String ingreso = response.getString("ingreso");
                                            guardarId(id);
                                            guardarNombre(nombre);
                                            guardarCorreo(correo);
                                            guardarUrl(imagen);
                                            guardarPuesto(puesto);
                                            guardarIngreso(ingreso);
                                            guardarEstado();
                                            intent.putExtra("opcion", 0);
                                            startActivity(intent);
                                            break;
                                        case "FALLIDO":
                                            Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(MainActivity.this);
        x.add(peticion);
    }

    @Override
    public void onBackPressed() {
    }

    public void guardarId(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("ID", my_id);
        myEditor.commit();
    }

    public void guardarNombre(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("NOMBRE", my_id);
        myEditor.commit();
    }

    public void guardarCorreo(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("CORREO", my_id);
        myEditor.commit();
    }

    public void guardarUrl(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("IMAGEN", my_id);
        myEditor.commit();
    }

    public void guardarPuesto(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("PUESTO", my_id);
        myEditor.commit();
    }

    public void guardarIngreso(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("INGRESO", my_id);
        myEditor.commit();
    }

    //Funcion para cambiar el estado al cerrar sesion
    public static void changeEstado(Context c, boolean b) {
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON, b).apply();
    }

    //Funcion para guardar el estado de mantener sesion
    public void guardarEstado() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON, sesion.isChecked()).apply();
    }

    //Funcion para obtener si se mantiene sesion
    public boolean obtenerEstado() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON, false);
    }
}
