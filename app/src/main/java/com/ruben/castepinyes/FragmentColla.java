package com.ruben.castepinyes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentColla.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentColla#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentColla extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnConsultar, btnGuardar, btnEditar, btnEliminar;
    private EditText etNombre, etApellido1, etTelefono;
    private ProgressDialog progressDialog;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private Bundle bundle = new Bundle();

    private DatabaseReference databaseReference;
    private String colla, nombrePersona, apellidoPersona;
    private ArrayList<String> arrayList = new ArrayList();
    private ArrayAdapter<String> arrayAdapter;

    private AdView mAdView;

    private boolean estaPersona;

    public FragmentColla() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentColla.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentColla newInstance(String param1, String param2) {
        FragmentColla fragment = new FragmentColla();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_colla, container, false);
        if (R.id.fragmentColla == v.getId()) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        etNombre = (EditText) v.findViewById(R.id.etNombre);
        etApellido1 = (EditText) v.findViewById(R.id.etApellido1);
        etTelefono = (EditText) v.findViewById(R.id.etTelefono);

        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");


        mAdView = v.findViewById(R.id.publi);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getEmail().equals("rubenniki@gmail.com")) {

            colla = "Collaviladecans";
            databaseReference = FirebaseDatabase.getInstance().getReference(colla).child("Personas Colla");
        } else {
            colla = "Personas Colla";
            databaseReference = FirebaseDatabase.getInstance().getReference(colla).child("Mal");
        }


        progressDialog = new ProgressDialog(getActivity());

        btnConsultar = v.findViewById(R.id.btnConsultar);

        btnConsultar.setOnClickListener(this);

        btnGuardar = v.findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(this);

        btnEditar = v.findViewById(R.id.btnEditar);

        btnEditar.setOnClickListener(this);
        btnEliminar = v.findViewById(R.id.btnEliminar);

        btnEliminar.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(colla).child("Personas Colla");

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                nombrePersona = String.valueOf(dataSnapshot.child("nombre").getValue());
                nombrePersona = nombrePersona + String.valueOf(dataSnapshot.child("apellido1").getValue());
                arrayList.add(nombrePersona);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                nombrePersona = String.valueOf(dataSnapshot.child("nombre").getValue());
                nombrePersona = nombrePersona + String.valueOf(dataSnapshot.child("apellido1").getValue());
                arrayList.add(nombrePersona);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                nombrePersona = String.valueOf(dataSnapshot.child("nombre").getValue());

                arrayList.add(nombrePersona);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                nombrePersona = String.valueOf(dataSnapshot.child("nombre").getValue());

                arrayList.add(nombrePersona);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void guardarPersonaCollaInformation() {
        String nombre = etNombre.getText().toString().trim();
        String apellido1 = etApellido1.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        PersonaCollaInformation personaCollaInformation;

        //Saltra error con la optencion del current user
        //Buscar que le pasa y como implementar de manera correcta la funcion .getCurrentUser();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getActivity(), "El nombre no puede estar en blanco", Toast.LENGTH_SHORT).show();
            //paramos la funcion
            return;

        } else if (TextUtils.isEmpty(apellido1)) {
            Toast.makeText(getActivity(), "El primer apellido no puede estar en blanco", Toast.LENGTH_SHORT).show();
            //paramos la funcion
            return;

        } else if (TextUtils.isEmpty(telefono)) {
            final ProgressDialog ringProgressDialog = ProgressDialog.show(getActivity(), "Espera un moment ", "Guardant Persona ...", true);
            ringProgressDialog.setCancelable(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Here you should write your time consuming task...
                        // Let the progress ring for 10 seconds...
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    ringProgressDialog.dismiss();
                }
            }).start();

            progressDialog.setMessage("Guardant a " + nombre + "....");
            progressDialog.show();
            personaCollaInformation = new PersonaCollaInformation(nombre, apellido1);
            databaseReference.push().setValue(personaCollaInformation);
            Toast.makeText(getActivity(), "Persona guardada", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            etNombre.setText(null);
            etApellido1.setText(null);


        } else {
            final ProgressDialog ringProgressDialog = ProgressDialog.show(getActivity(), "Espera un moment ", "Guardant Persona ...", true);
            ringProgressDialog.setCancelable(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //temmps per fer la tasca
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    ringProgressDialog.dismiss();
                }
            }).start();
            personaCollaInformation = new PersonaCollaInformation(nombre, apellido1, telefono);
            databaseReference.push().setValue(personaCollaInformation);
            Toast.makeText(getActivity(), "Persona guardada", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            etNombre.setText(null);
            etApellido1.setText(null);
            etTelefono.setText(null);
        }

        //si llega aqui toda va dabuti
        //enseñamos un progres dialog


    }

    private void mostrarGente() {
        String nombre = etNombre.getText().toString().trim();
        String apellido1 = etApellido1.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        bundle.putString("COLLA", colla);
        if (TextUtils.isEmpty(nombre) && TextUtils.isEmpty(apellido1) && TextUtils.isEmpty(telefono) && !bundle.getBoolean("DELETE")) {
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("server/saving-data/fireblog/posts");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    Log.d(TAG, "Value is: " + value);
                    bundle.putBoolean("DELETE", false);

                    Fragment fragment = new Mostrar_Colla();
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        } else if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getActivity(), "El nombre no puede estar en blanco", Toast.LENGTH_SHORT).show();
            //paramos la funcion
            return;

        } else if (TextUtils.isEmpty(apellido1)) {
            Toast.makeText(getActivity(), "El primer apellido no puede estar en blanco", Toast.LENGTH_SHORT).show();
            //paramos la funcion
            return;

        } else if (TextUtils.isEmpty(telefono)) {
            final ProgressDialog ringProgressDialog = ProgressDialog.show(getActivity(), "Espera un moment ", "Editant Persona ...", true);
            ringProgressDialog.setCancelable(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Here you should write your time consuming task...
                        // Let the progress ring for 10 seconds...
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    ringProgressDialog.dismiss();
                }
            }).start();
            bundle.putString("NOMBRE", nombre);
            bundle.putString("APELLIDO", apellido1);
            Fragment fragment = new Mostrar_Colla();
            bundle.putBoolean("DELETE", true);
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        } else {
            final ProgressDialog ringProgressDialog = ProgressDialog.show(getActivity(), "Espera un moment ", "Guardant Persona ...", true);
            ringProgressDialog.setCancelable(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //temmps per fer la tasca
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    ringProgressDialog.dismiss();
                }
            }).start();
            bundle.putString("NOMBRE", nombre);
            bundle.putString("APELLIDO", apellido1);
            Fragment fragment = new Mostrar_Colla();
            bundle.putBoolean("DELETE", true);
            fragment.setArguments(bundle);
            replaceFragment(fragment);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:
                guardarPersonaCollaInformation();
                break;
            case R.id.btnConsultar:
                bundle.putBoolean("DELETE", false);
                mostrarGente();
                break;
            case R.id.btnEditar:
                editarpersona();

                break;
            case R.id.btnEliminar:
                bundle.putBoolean("DELETE", true);
                mostrarGente();

                break;

        }
            /*
        Fragment fragment=new gentColla();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentColla, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
*/
    }


    private void editarpersona() {
        String nombre = etNombre.getText().toString().trim();
        String apellido1 = etApellido1.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getActivity(), "El nombre no puede estar en blanco", Toast.LENGTH_SHORT).show();
            //paramos la funcion
            return;

        } else if (TextUtils.isEmpty(apellido1)) {
            Toast.makeText(getActivity(), "El primer apellido no puede estar en blanco", Toast.LENGTH_SHORT).show();
            //paramos la funcion
            return;

        } else if (TextUtils.isEmpty(telefono)) {
            final ProgressDialog ringProgressDialog = ProgressDialog.show(getActivity(), "Espera un moment ", "Comprovant informacio per editar persona ...", true);
            ringProgressDialog.setCancelable(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Here you should write your time consuming task...
                        // Let the progress ring for 10 seconds...
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    ringProgressDialog.dismiss();
                }
            }).start();
            bundle.putString("NOMBRE", nombre);
            bundle.putString("APELLIDO", apellido1);
            for (int i = 0; i < arrayList.size(); i++) {
                nombrePersona = arrayList.get(i).toString();
                if (nombrePersona.equalsIgnoreCase(nombre + apellido1)) {
                    Fragment fragment = new EditarPersona();
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                    estaPersona=true;
                }
            }
            if(!estaPersona){
                Toast.makeText(getActivity(), "Esta persona no esta en la base de datos por lo tanto no lo puedes editar", Toast.LENGTH_LONG).show();
            }

        } else {
            final ProgressDialog ringProgressDialog = ProgressDialog.show(getActivity(), "Espera un moment ", "Guardant Persona ...", true);
            ringProgressDialog.setCancelable(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //temmps per fer la tasca
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    ringProgressDialog.dismiss();
                }
            }).start();

            bundle.putString("NOMBRE", nombre);
            bundle.putString("APELLIDO", apellido1);
            bundle.putString("TELEFONO", telefono);
            Fragment fragment = new EditarPersona();
            fragment.setArguments(bundle);
            replaceFragment(fragment);

        }
    }

    public void onClickCancel(View v) {
        // Finalizar actividad
        getActivity().finish();
    }

    /*
    La clase PostCommentTask permite enviar los datos hacia el servidor
    para guardar el comentario del usuario.
     */


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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
