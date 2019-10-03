package com.example.proyectofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EditarInformacion extends Fragment {
    Spinner EstadoCivil_opciones;
    Spinner Discapacidades_opciones;
    Spinner SegundoIdioma_opciones;
    Spinner TercerIdioma_opciones;
    Spinner NivelEstudios_opciones;
    Spinner Nacionalidad_opciones;
    EditText et_nombre, et_correo, et_telefono, et_direccion, et_edad, et_estatura, et_profesion, et_ingreso, et_puesto;
    String EstadoCivil, NivelEstudios, SegundoIdioma, Tercer_idioma, Discapacidades, Nacionalidad;
    String nombre, id, puesto, profesion, ingreso, edad, estatura, correo, telefono, direccion;
    Button guardar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.activity_editar_informacion, container, false);

        et_nombre = rootview.findViewById(R.id.nombre);
        et_correo = rootview.findViewById(R.id.correo);
        et_telefono = rootview.findViewById(R.id.telefono);
        et_direccion = rootview.findViewById(R.id.direccion);
        et_edad = rootview.findViewById(R.id.edad);
        et_estatura = rootview.findViewById(R.id.estatura);
        et_profesion = rootview.findViewById(R.id.profesion);
        et_ingreso = rootview.findViewById(R.id.ingreso);
        et_puesto = rootview.findViewById(R.id.puesto);
        guardar = rootview.findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje();
            }
        });
        informacion();
        //-----------------------------------------------------------------------------------------------------------------//

        Nacionalidad_opciones = rootview.findViewById(R.id.Spinner_Nacionalidad);

        ArrayAdapter<CharSequence> adapter0 = ArrayAdapter.createFromResource
                (getContext(), R.array.Nacionalidad_opciones, android.R.layout.simple_spinner_dropdown_item);
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

        EstadoCivil_opciones = rootview.findViewById(R.id.Spinner_EstadoCivil);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (getContext(), R.array.EstadoCivil_opciones, android.R.layout.simple_spinner_dropdown_item);
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

        Discapacidades_opciones = rootview.findViewById(R.id.Spinner_Discapacidades);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource
                (getContext(), R.array.Discapacidades_opciones, android.R.layout.simple_spinner_dropdown_item);
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

        SegundoIdioma_opciones = rootview.findViewById(R.id.Spinner_SegundoIdioma);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource
                (getContext(), R.array.SegundoIdioma_opciones, android.R.layout.simple_spinner_dropdown_item);
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

        TercerIdioma_opciones = rootview.findViewById(R.id.Spinner_TercerIdioma);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource
                (getContext(), R.array.TercerIdioma_opciones, android.R.layout.simple_spinner_dropdown_item);
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

        NivelEstudios_opciones = rootview.findViewById(R.id.Spinner_estudios);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource
                (getContext(), R.array.NivelEstudios_opciones, android.R.layout.simple_spinner_dropdown_item);
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



        return rootview;
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
                                            telefono = response.getString("telefono");
                                            direccion = response.getString("direccion");
                                            edad = response.getString("edad");
                                            estatura = response.getString("estatura");
                                            profesion = response.getString("profesion");
                                            ingreso = response.getString("ingreso");
                                            Nacionalidad = response.getString("nacionalidad");
                                            EstadoCivil = response.getString("estado_civil");
                                            SegundoIdioma = response.getString("segundo_idioma");
                                            Tercer_idioma = response.getString("tercer_idioma");
                                            Discapacidades = response.getString("discapacidad");
                                            NivelEstudios = response.getString("nivel_estudios");
                                            puesto = response.getString("puesto");

                                            Nacionalidad_opciones.setSelection(Integer.parseInt(Nacionalidad));
                                            EstadoCivil_opciones.setSelection(Integer.parseInt(EstadoCivil));
                                            SegundoIdioma_opciones.setSelection(Integer.parseInt(SegundoIdioma));
                                            TercerIdioma_opciones.setSelection(Integer.parseInt(Tercer_idioma));
                                            NivelEstudios_opciones.setSelection(Integer.parseInt(NivelEstudios));
                                            Discapacidades_opciones.setSelection(Integer.parseInt(Discapacidades));


                                            et_nombre.setText(nombre);
                                            et_correo.setText(correo);
                                            et_telefono.setText(telefono);
                                            et_direccion.setText(direccion);
                                            et_edad.setText(edad);
                                            et_estatura.setText(estatura);
                                            et_profesion.setText(profesion);
                                            et_ingreso.setText(ingreso);
                                            et_puesto.setText(puesto);
                                            break;
                                        case "FALLIDO":
                                            Toast.makeText(getContext(), "Fallo de conexión", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(getContext());
        x.add(peticion);
    }

    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

    public void mensaje() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(getContext());
        myBuild.setMessage("¿Estás seguro de que quieres guardar los cambios?");
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                guardarCambios();
                Toast.makeText(getContext(), "Cambios guardados", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MenuOpciones.class);
                intent.putExtra("opcion",1);
                startActivity(intent);
            }
        });
        myBuild.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = myBuild.create();
        dialog.show();
    }


    public void guardarCambios() {
        id = obtenerId();
        nombre = et_nombre.getText().toString().trim();
        correo = et_correo.getText().toString().trim();
        profesion = et_profesion.getText().toString().trim();
        telefono = et_telefono.getText().toString().trim();
        direccion = et_telefono.getText().toString().trim();
        edad = et_edad.getText().toString().trim();
        estatura = et_estatura.getText().toString().trim();
        ingreso = et_ingreso.getText().toString().trim();
        puesto = et_puesto.getText().toString().trim();
        guardarNombre(nombre);
        guardarCorreo(correo);
        guardarPuesto(puesto);
        guardarIngreso(ingreso);
        String url = null;
        try {
            url = "http://jfsproyecto.online/editarInfoEmpleado.php?nombre=" + URLEncoder.encode(nombre, "UTF-8") + "&id=" + id
                    + "&profesion=" + URLEncoder.encode(profesion, "UTF-8")
                    + "&ingreso=" + ingreso + "&edad=" + edad + "&estatura=" + estatura + "&nacionalidad=" + Nacionalidad
                    + "&correo=" + correo + "&telefono=" + telefono + "&direccion=" + direccion
                    + "&estado=" + EstadoCivil + "&segundo=" + SegundoIdioma + "&tercer=" + Tercer_idioma + "&discapacidad=" + Discapacidades
                    + "&estudios=" + NivelEstudios + "&puesto=" + URLEncoder.encode(puesto, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

                                            break;
                                        case "SI":
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
                        //Toast.makeText(Crear_oferta.this, "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(getContext());
        x.add(peticion);
    }

    public void guardarNombre(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("NOMBRE", my_id);
        myEditor.commit();
    }

    public void guardarCorreo(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("CORREO", my_id);
        myEditor.commit();
    }

    public void guardarPuesto(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("PUESTO", my_id);
        myEditor.commit();
    }

    public void guardarIngreso(String my_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor myEditor = preferences.edit();
        myEditor.putString("INGRESO", my_id);
        myEditor.commit();
    }
}
