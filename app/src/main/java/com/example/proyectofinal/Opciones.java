package com.example.proyectofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Opciones extends AppCompatActivity {

    Button buscar1, buscar2, buscar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        buscar1 = findViewById(R.id.buscar1);
        buscar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Opciones.this, OfertasEmpleos.class);
                startActivity(intent);
            }
        });

        buscar2 = findViewById(R.id.buscar2);
        buscar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Opciones.this, NuevasOfertas.class);
                startActivity(intent);
            }
        });

        buscar3 = findViewById(R.id.buscar3);
        buscar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Opciones.this, CrearBusqueda.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Opciones.this, MenuOpciones.class);
        intent.putExtra("opcion", 0);
        startActivity(intent);
    }
}

