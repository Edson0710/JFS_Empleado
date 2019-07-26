package com.example.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;


public class VistaPrincipalFragment extends Fragment {
    Button Boton_OfertasEmpleos, Boton_TrabajosFinalizados, Boton_Curriculum, Boton_MisOfertas;
    TextView tv_nombre;
    CircleImageView imageView;

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


        Boton_OfertasEmpleos = rootview.findViewById(R.id.Button_OfertasEmpleos);
        Boton_OfertasEmpleos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OfertasEmpleos.class);
                startActivity(intent);
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
                Intent intent = new Intent (getContext(), VistaCurriculum.class);
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


}
