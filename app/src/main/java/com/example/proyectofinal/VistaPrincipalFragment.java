package com.example.proyectofinal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import de.hdodenhof.circleimageview.CircleImageView;


public class VistaPrincipalFragment extends Fragment {
    Button Boton_OfertasEmpleos, Boton_TrabajosFinalizados, Boton_Curriculum, Boton_MisOfertas;
    TextView tv_nombre;
    CircleImageView imageView;
    String comentar;
    float calificar;
    int flag=0;
    private OnFragmentInteractionListener mListener;

    public VistaPrincipalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_vista_principal, container, false);
        tv_nombre = rootview.findViewById(R.id.nombre);
        imageView = rootview.findViewById(R.id.imagen);

        String nombre = obtenerNombre();
        tv_nombre.setText(nombre);
        String url = obtenerUrl();
        Glide.with(getContext()).load(url).into(imageView);

        pendiente();


        Boton_OfertasEmpleos = rootview.findViewById(R.id.Button_OfertasEmpleos);
        Boton_OfertasEmpleos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==0) {
                    Intent intent = new Intent(getContext(), Opciones.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Tienes un empleo pendiente a calificar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Boton_MisOfertas = rootview.findViewById(R.id.Button_MisOfertas);
        Boton_MisOfertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MisOfertas.class);
                startActivity(intent);
            }
        });

        Boton_Curriculum = rootview.findViewById(R.id.Button_Curriculum);
        Boton_Curriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VistaCurriculum.class);
                startActivity(intent);
            }
        });

        Boton_TrabajosFinalizados = rootview.findViewById(R.id.Button_TrabajosFinalizados);
        Boton_TrabajosFinalizados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TrabajosFinalizados.class);
                startActivity(intent);
            }
        });



        return rootview;

    }


    public String obtenerNombre() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id_preference = preferences.getString("NOMBRE", "1");
        return id_preference;
    }

    public String obtenerUrl() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String id_preference = preferences.getString("IMAGEN", "1");
        return id_preference;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void mensaje0() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(getContext());
        myBuild.setMessage("Tienes un empleo pendiente por calificar");
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mensaje1();
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

    public void mensaje1() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.calificar, null);
        myBuild.setView(dialogView);
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RatingBar rating = dialogView.findViewById(R.id.rating);
                calificar = rating.getRating();
                mensaje2();
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

    public void mensaje2() {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.comentar, null);
        myBuild.setView(dialogView);
        myBuild.setTitle("JFS");
        myBuild.setCancelable(false);
        myBuild.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText comentario = dialogView.findViewById(R.id.comentario);
                comentar = comentario.getText().toString().trim();
                bot();
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

    public void Calificar(){
        String id = obtenerId();
        String url = null;
        try {
            url = "http://jfsproyecto.online/calificarEmpleado.php?id_empleado=" + id +
                    "&calificacion=" + calificar + "&comentario=" + URLEncoder.encode(comentar, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
                        //Toast.makeText(getContext(), "Error conexión", Toast.LENGTH_SHORT).show();
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

    public void pendiente() {
        String id = obtenerId();
        String url = "http://jfsproyecto.online/pendienteEmpleado.php?id=" + id;
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
                                            flag = 1;
                                            mensaje0();
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
                        Toast.makeText(getContext(), "Error conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue x = Volley.newRequestQueue(getContext());
        x.add(peticion);
    }

    public void bot() {
        String id = obtenerId();
        String url = null;
        try {
            url = "http://jfsproyecto.online/botEmpleado.php?comentario=" + URLEncoder.encode(comentar, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
                                        case "NO":
                                            Toast.makeText(getContext(), "No se permite cierto lenguaje de tu comentario, vuelve a intentar", Toast.LENGTH_LONG).show();
                                            mensaje2();
                                            break;
                                        case "OK":
                                            Calificar();
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

}
