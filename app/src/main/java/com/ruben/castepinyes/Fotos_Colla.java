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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fotos_Colla extends Fragment {

    ListView fotos;
    public ArrayList imagen = new ArrayList();
    Adapter_foto_listView adapter_foto_listView;
    private DatabaseReference databaseReference;
    private Bundle bundleImage =new Bundle();

    public Fotos_Colla() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fotos__colla, container, false);

        fotos=(ListView) view.findViewById(R.id.fotos_listView);

        adapter_foto_listView=new Adapter_foto_listView(imagen,getActivity());
        fotos.setAdapter(adapter_foto_listView);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("data");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String string = String.valueOf(dataSnapshot.getValue());
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference hola = storage.getReference().child(getArguments().getString("usuario")).child(string);
                hola.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imagen.add(uri);
                        adapter_foto_listView.notifyDataSetChanged();
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
        fotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Uri = (String) ((TextView) view).getText();

                bundleImage.putString("Uri",Uri);
                Fragment login = new MostrarFotoPinyaFragment();
                login.setArguments(bundleImage);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.Fotos_colla, login);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });






        return view;
    }

}
