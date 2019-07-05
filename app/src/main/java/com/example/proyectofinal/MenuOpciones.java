package com.example.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuOpciones extends AppCompatActivity  /////////
        implements NavigationView.OnNavigationItemSelectedListener, InfoFragment.OnFragmentInteractionListener, VistaPrincipalFragment.OnFragmentInteractionListener{

    String id_empleado, nombre, correo, imagen;
    TextView nombreTextView, correoTextView;
    CircleImageView imageView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id_empleado = obtenerId();
        nombre = obtenerNombre();
        correo = obtenerCorreo();
        imagen = obtenerUrl();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /////////////////////////////
        Fragment fragment = new VistaPrincipalFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,fragment).commit();
        ///////////////////////////////

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        nombreTextView = view.findViewById(R.id.nombre);
        correoTextView = view.findViewById(R.id.correo);
        imageView = view.findViewById(R.id.imageView_User);
        nombreTextView.setText(nombre);
        correoTextView.setText(correo);
        Glide.with(getApplicationContext()).load(imagen).into(imageView);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
 ////////////////////////////////////////////////
        Fragment miFragment = null;
        boolean fragmentSeleccionado=false;
 /////////////////////////////////////////////////



        if (id == R.id.nav_Inicio) {
            miFragment = new VistaPrincipalFragment();
            fragmentSeleccionado=true;



        } else if (id==R.id.nav_Informacion_Perfil){
            Intent Informacion_Perfil = new Intent(MenuOpciones.this,EditarInformacion.class);
            startActivity(Informacion_Perfil);


        } else if (id == R.id.nav_CerrarSesion) {
            Intent CerrarSesion = new Intent(MenuOpciones.this, MainActivity.class);
            startActivity(CerrarSesion);



        } else if (id == R.id.nav_Soporte) {
            miFragment = new InfoFragment();
            fragmentSeleccionado=true;


        }

        if (fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, miFragment).commit();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public String obtenerId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MenuOpciones.this);
        String id_preference = preferences.getString("ID", "1");
        return id_preference;
    }

    public String obtenerNombre() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MenuOpciones.this);
        String id_preference = preferences.getString("NOMBRE", "1");
        return id_preference;
    }

    public String obtenerCorreo() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MenuOpciones.this);
        String id_preference = preferences.getString("CORREO", "1");
        return id_preference;
    }

    public String obtenerUrl() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MenuOpciones.this);
        String id_preference = preferences.getString("IMAGEN", "1");
        return id_preference;
    }


}
