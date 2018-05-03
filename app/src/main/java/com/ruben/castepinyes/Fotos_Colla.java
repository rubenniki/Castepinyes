package com.ruben.castepinyes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fotos_Colla extends Fragment {

    ListView fotos;
    public ArrayList imagen;
    Adapter_foto_listView adapter_foto_listView;
    public Fotos_Colla() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fotos__colla, container, false);

        fotos=view.findViewById(R.id.Foto_pinya);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference hola = storage.getReference().child("CollaViladecans").child("ac_a1721.jpg");
        Toast.makeText(getActivity(), (CharSequence) hola.getName(), Toast.LENGTH_SHORT).show();
        adapter_foto_listView=new Adapter_foto_listView(imagen,getActivity());


        return view;
    }

}
