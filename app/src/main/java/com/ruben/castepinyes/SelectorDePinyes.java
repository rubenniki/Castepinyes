package com.ruben.castepinyes;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Objects;

public class SelectorDePinyes extends Fragment implements View.OnClickListener {

    private ListView listview;
    private DatabaseReference databaseReference;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private ArrayList<Uri> imagen=new ArrayList<Uri>();

    public SelectorDePinyes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_selector_de_pinyes, container, false);


        // Inflate the layout for this fragmentView


        listview = (ListView) view.findViewById(R.id.collas);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(arrayAdapter);

        //pasarle bundle de colla usuario
        final String nombreColla = getArguments().getString("usuario");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                String colla = String.valueOf(dataSnapshot.getRef());
                String[] separated = colla.split("/");
                int ultimo = separated.length - 1;

                if (separated[ultimo].contains("Colla")) {
                    if (nombreColla.equalsIgnoreCase(separated[ultimo])) {
                        arrayList.add(separated[ultimo]);
                        arrayAdapter.notifyDataSetChanged();
                        if(arrayList.size()==1){
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("data");
                            databaseReference.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                    String string = String.valueOf(dataSnapshot.getValue());
                                    FirebaseStorage storage = FirebaseStorage.getInstance();
                                    StorageReference hola = storage.getReference().child(Objects.requireNonNull(arrayList.get(0))).child(string);
                                    hola.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imagen.add(uri);
                                        }
                                    });


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
                    }

                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String colla = String.valueOf(dataSnapshot.getRef());
                String[] separated = colla.split("/");
                int ultimo = separated.length - 1;

                if (separated[ultimo].contains("colla")) {
                    arrayList.add(separated[ultimo]);
                    arrayAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String colla = String.valueOf(dataSnapshot.getRef());
                String[] separated = colla.split("/");
                int ultimo = separated.length - 1;


                if (separated[ultimo].contains("colla")) {
                    arrayList.add(separated[ultimo]);
                    arrayAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                String colla = String.valueOf(dataSnapshot.getRef());
                String[] separated = colla.split("/");
                int ultimo = separated.length - 1;

                if ("colla".contains(separated[ultimo])) {
                    arrayList.add(separated[ultimo]);
                    arrayAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String nombre = listview.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), "Has seleccionadao la colla de " + nombre, Toast.LENGTH_LONG).show();


                Fragment login = new Fotos_Colla();


                bundle.putParcelableArrayList("fotos",imagen);
                login.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.selector_de_pinyes, login);
                transaction.addToBackStack(null);
                transaction.commit();

            }


        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
