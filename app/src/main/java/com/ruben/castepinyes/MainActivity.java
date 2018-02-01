package com.ruben.castepinyes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import layout.fragment_pinyes_tresdecinc;
import layout.frament_fragment_pinyes;
import layout.pinya_de_cinc;
import layout.pinya_de_cuatre;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentColla.OnFragmentInteractionListener, FragmentPinya.OnFragmentInteractionListener, frament_fragment_pinyes.OnFragmentInteractionListener, FragmentInicio.OnFragmentInteractionListener, pinya_de_cuatre.OnFragmentInteractionListener, pinya_de_cinc.OnFragmentInteractionListener, fragment_pinyes_tresdecinc.OnFragmentInteractionListener, Mostrar_Colla.OnFragmentInteractionListener, EditarPersona.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening email to send a error", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"Castepinyes@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer Name," + "\n");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Abre el email"));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        MediaPlayer music = MediaPlayer.create(MainActivity.this, R.raw.musicacastellstoccastells);

        int id = item.getItemId();
        Fragment fragment = null;
        boolean musicasonando = false;
        boolean FragmentoSeleccionado = false;

        if (id == R.id.nav_colla) {
            setTitle("Colla");
            fragment = new FragmentColla();
            FragmentoSeleccionado = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            music.stop();

        } else if (id == R.id.nav_gallery) {
            setTitle("Pinyes");
            fragment = new FragmentPinya();
            FragmentoSeleccionado = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            music.stop();

        } else if (id == R.id.nav_inicio) {
            fragment = new FragmentInicio();
            FragmentoSeleccionado = true;
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            music.start();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        if (FragmentoSeleccionado == true) {

            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
