package com.ruben.castepinyes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private DatabaseReference databaseReference;
    private ListView listview;
    private String colla,nombrePersona,apellidoPersona;
    private ArrayList<String> arrayList =new ArrayList();
    private ArrayAdapter<String> arrayAdapter;

    Bundle bundle=new Bundle();
    final String nombre=bundle.getString("NOMBRE");

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
        final Bundle bundle=this.getArguments();
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_editar_persona, container, false);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();


        if (user.getEmail().equals("rubenniki@gmail.com")) {

            colla = "Collaviladecans";
            databaseReference = FirebaseDatabase.getInstance().getReference(colla).child("Personas Colla");
        } else {
            colla = "Personas Colla";
            databaseReference = FirebaseDatabase.getInstance().getReference(colla).child("Mal");
        }
        // Inflate the layout for this fragmentView
        FloatingActionButton fab = view.findViewById(R.id.genteColla);


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
                String key = dataSnapshot.getKey();

                if (nombrePersona.equalsIgnoreCase(bundle.getString("NOMBRE")) && apellidoPersona.equalsIgnoreCase(bundle.getString("APELLIDO"))) {

                        databaseReference.child(key);
                        EditText etnombre=view.findViewById(R.id.editNombre);
                        EditText etApellido=view.findViewById(R.id.editApellido1);
                        etApellido.setText(apellidoPersona);
                        etnombre.setText(nombre);

                }
                arrayList.add(nombrePersona);
                arrayAdapter.notifyDataSetChanged();

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
        return view;
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
