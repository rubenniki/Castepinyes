package com.ruben.castepinyes;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fotos_Colla extends Fragment {


    public Fotos_Colla() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fotos__colla, container, false);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference hola = storage.getReference().child("CollaViladecans");
        Toast.makeText(getActivity(), hola.getName() , Toast.LENGTH_SHORT).show();



        return view;
    }

}
