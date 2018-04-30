package com.ruben.castepinyes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditarPersona.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditarPersona#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarPersona extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Bundle bundle = new Bundle();
    final String nombre = bundle.getString("NOMBRE");
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean buscar = false;
    private OnFragmentInteractionListener mListener;
    private DatabaseReference databaseReference;
    private ListView listview;
    private String colla, nombrePersona, apellidoPersona,key;
    private ArrayList<String> arrayList = new ArrayList();
    private ArrayAdapter<String> arrayAdapter;

    EditText etnombre,etApellido;

    private AdView mAdView;

    public EditarPersona() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarPersona.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarPersona newInstance(String param1, String param2) {
        EditarPersona fragment = new EditarPersona();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.fragment_editar_persona, container, false);
        etnombre = view.findViewById(R.id.editNombre);
        etApellido = view.findViewById(R.id.editApellido1);

        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");


        mAdView = view.findViewById(R.id.publi);
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
        // Inflate the layout for this fragmentView
        FloatingActionButton fab = view.findViewById(R.id.floatGuardar);


        listview = (ListView) view.findViewById(R.id.listview
        );
        databaseReference = FirebaseDatabase.getInstance().getReference().child(colla).child("Personas Colla");

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                nombrePersona = String.valueOf(dataSnapshot.child("nombre").getValue());
                apellidoPersona = String.valueOf(dataSnapshot.child("apellido1").getValue());


                if (nombrePersona.equalsIgnoreCase(bundle.getString("NOMBRE")) && apellidoPersona.equalsIgnoreCase(bundle.getString("APELLIDO"))) {
                    key = dataSnapshot.getKey();
                    databaseReference.child(key);
                    etnombre.setText(nombrePersona);
                    etApellido.setText(apellidoPersona);

                }else{
                    arrayList.add(nombrePersona);
                    arrayAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                nombrePersona = String.valueOf(dataSnapshot.child("nombre").getValue());

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
        Button editar=view.findViewById(R.id.editEditar);

        //edit implementacion
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apellidoPersona= String.valueOf(etApellido.getText());
                nombrePersona=String.valueOf(etnombre.getText());
                Toast.makeText(getActivity(),nombrePersona,Toast.LENGTH_LONG).show();
                PersonaCollaInformation persona = new PersonaCollaInformation(nombrePersona,apellidoPersona);
                databaseReference.child(key).setValue(persona);
            }
        });
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
