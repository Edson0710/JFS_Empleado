package com.example.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class VistaPrincipalFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button Boton_OfertasEmpleos;
    Button Boton_TrabajosFinalizados;
    Button Boton_Curriculum;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public VistaPrincipalFragment() {
        // Required empty public constructor
    }

    public static VistaPrincipalFragment newInstance(String param1, String param2) {
        VistaPrincipalFragment fragment = new VistaPrincipalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_vista_principal, container, false);


        Boton_OfertasEmpleos = rootview.findViewById(R.id.Button_OfertasEmpleos);
        Boton_OfertasEmpleos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OfertasEmpleos.class);
                startActivity(intent);
            }
        });


        Boton_TrabajosFinalizados = rootview.findViewById(R.id.Button_TrabajosFinalizados);
        Boton_TrabajosFinalizados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getContext(), TrabajosFinalizados.class);
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
