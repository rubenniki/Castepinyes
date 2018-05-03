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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import layout.fragment_pinyes_tresdecinc;
import layout.frament_fragment_pinyes;
import layout.pinya_de_cinc;
import layout.pinya_de_cuatre;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentColla.OnFragmentInteractionListener, FragmentPinya.OnFragmentInteractionListener, frament_fragment_pinyes.OnFragmentInteractionListener, FragmentInicio.OnFragmentInteractionListener, pinya_de_cuatre.OnFragmentInteractionListener, pinya_de_cinc.OnFragmentInteractionListener, fragment_pinyes_tresdecinc.OnFragmentInteractionListener, Mostrar_Colla.OnFragmentInteractionListener, EditarPersona.OnFragmentInteractionListener {

    private ArrayList<String> arrayList =new ArrayList();
    private ArrayAdapter<String> arrayAdapter;
    private DatabaseReference databaseReference;

    private Bundle bundle=new Bundle();
    private  String string;
    private String nombrePersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        getUserColla();

        checkFotos();



    }

    private void checkFotos() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd");
        final String formattedDate = df.format(c);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("data");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String string = String.valueOf(dataSnapshot.child("data").getValue());
                if (!string.contains(formattedDate)){
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference storageRef = storage.getReference();
                    StorageReference desertRef = storageRef.child(nombrePersona.concat("/")).child("pinya"+string);


                    desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // File deleted successfully
                        }
                    });
                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String string = String.valueOf(dataSnapshot.child("data").getValue());


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String string = String.valueOf(dataSnapshot.child("data").getValue());


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                String string = String.valueOf(dataSnapshot.child("data").getValue());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getUserColla() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("cuentasDePinyas");
        arrayAdapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, arrayList);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                string = (String) dataSnapshot.getValue();

                if (user.getEmail().contains(dataSnapshot.getKey())){
                    nombrePersona=string;
                }



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                string = (String) dataSnapshot.getValue();

                if (user.getEmail().contains(dataSnapshot.getKey())){
                    nombrePersona=string;
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                string = (String) dataSnapshot.getValue();

                if (user.getEmail().contains(dataSnapshot.getKey())){
                    nombrePersona=string;
                }

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                string = (String) dataSnapshot.getValue();

                if (user.getEmail().contains(dataSnapshot.getKey())){
                    nombrePersona=string;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this,"hola",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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
            bundle.putString("usuario",nombrePersona);
            fragment.setArguments(bundle);
            FragmentoSeleccionado = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            music.stop();

        }else if (id == R.id.nav_visor) {
            setTitle("Pinyes");
            fragment = new SelectorDePinyes();
            bundle.putString("usuario",nombrePersona);
            fragment.setArguments(bundle);
            FragmentoSeleccionado = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            music.stop();

        } else if (id == R.id.nav_inicio) {
            fragment = new FragmentInicio();
            FragmentoSeleccionado = true;
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            music.start();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL,
                    new String[]{"Castepinyes@gmail.com"});  //developer 's email
            Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer Name," + "\n");  //Email 's Greeting text
            startActivity(Intent.createChooser(Email, "Abre el email"));

        }
        if (FragmentoSeleccionado) {

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