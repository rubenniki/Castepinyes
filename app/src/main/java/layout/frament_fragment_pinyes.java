package layout;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ruben.castepinyes.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frament_fragment_pinyes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frament_fragment_pinyes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frament_fragment_pinyes extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String colla;
    TextView hola, button, button2;
    private DatabaseReference databaseReference;
    private ListView listview;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private String nombre="",nombrePersona;

    private Button Agulla_izquierda,Baix_izquierda,Agulla_derecha,Baix_derecha,Crosa_dreta_arriba,Crosa_izquierda_arriba,Crosa_izquierda_abajo,Vent_abajo,Vent_arriba,Crosa_dreta_abajo;


    public frament_fragment_pinyes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frament_fragment_pinyes.
     */
    // TODO: Rename and change types and number of parameters
    public static frament_fragment_pinyes newInstance(String param1, String param2) {
        frament_fragment_pinyes fragment = new frament_fragment_pinyes();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragmentView
        View view = inflater.inflate(R.layout.fragment_frament__pinyes_dosdecinc, container, false);
        FloatingActionButton fab = view.findViewById(R.id.floatGuardar);
        Agulla_izquierda=view.findViewById(R.id.Agulla_izquierda);
        Baix_izquierda=view.findViewById(R.id.Baix_izquierda);
        Agulla_derecha=view.findViewById(R.id.Agulla_derecha);
        Baix_derecha=view.findViewById(R.id.Baix_derecha);
        Crosa_dreta_arriba=view.findViewById(R.id.Crosa_dreta_arriba);
        Crosa_izquierda_arriba=view.findViewById(R.id.Crosa_izquierda_arriba);
        Crosa_izquierda_abajo=view.findViewById(R.id.Crosa_izquierda_abajo);
        Vent_abajo=view.findViewById(R.id.Vent_abajo);
        Vent_arriba=view.findViewById(R.id.Vent_arriba);
        Crosa_dreta_abajo=view.findViewById(R.id.Crosa_dreta_abajo);

        Agulla_derecha.setOnClickListener(this);
        Agulla_izquierda.setOnClickListener(this);
        Baix_izquierda.setOnClickListener(this);
        Baix_derecha.setOnClickListener(this);
        Crosa_dreta_abajo.setOnClickListener(this);
        Crosa_dreta_arriba.setOnClickListener(this);
        Crosa_izquierda_abajo.setOnClickListener(this);
        Crosa_izquierda_arriba.setOnClickListener(this);
        Vent_abajo.setOnClickListener(this);
        Vent_arriba.setOnClickListener(this);



        listview = (ListView) view.findViewById(R.id.listview);
        nombrePersona=getArguments().getString("usuario");

        databaseReference = FirebaseDatabase.getInstance().getReference().child(nombrePersona).child("Personas Colla");
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String string = String.valueOf(dataSnapshot.child("nombre").getValue());

                arrayList.add(string);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String string = String.valueOf(dataSnapshot.child("nombre").getValue());

                arrayList.add(string);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String string = String.valueOf(dataSnapshot.child("nombre").getValue());

                arrayList.add(string);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                String string = String.valueOf(dataSnapshot.child("nombre").getValue());

                arrayList.add(string);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                nombre = (String) ((TextView) view).getText();
                Log.d("hola", nombre);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Agulla_derecha:
                if (!nombre.equalsIgnoreCase("")) {
                    Agulla_derecha.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Agulla_izquierda:
                if (!nombre.equalsIgnoreCase("")) {
                    Agulla_izquierda.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Baix_izquierda:
                if (!nombre.equalsIgnoreCase("")) {
                    Baix_izquierda.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Baix_derecha:
                if (!nombre.equalsIgnoreCase("")) {
                    Baix_derecha.setText(nombre);
                    nombre="";
                }
                break;
            case R.id.Crosa_dreta_abajo:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_dreta_abajo.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_dreta_arriba:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_dreta_arriba.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_izquierda_abajo:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_izquierda_abajo.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.Crosa_izquierda_arriba:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_izquierda_arriba.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Vent_abajo:
                if (!nombre.equalsIgnoreCase("")) {
                    Vent_abajo.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Vent_arriba:
                if (!nombre.equalsIgnoreCase("")) {
                    Vent_arriba.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
        }
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
