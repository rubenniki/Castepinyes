package com.ruben.castepinyes;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class Fotos_Colla extends Fragment {

    public ArrayList<Uri> imagen = new ArrayList<Uri>();
    ListView fotos;
    Adapter_foto_listView adapter_foto_listView;
    private DatabaseReference databaseReference;
    private Bundle bundleImage = new Bundle();

    public Fotos_Colla() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fotos__colla, container, false);

        fotos = (ListView) view.findViewById(R.id.fotos_listView);

        imagen=getArguments().getParcelableArrayList("fotos");

        adapter_foto_listView = new Adapter_foto_listView(imagen, getActivity());
        fotos.setAdapter(adapter_foto_listView);

        fotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView imageView = (ImageView) view.findViewById(R.id.Foto_pinya);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap yourBitmap = bitmapDrawable.getBitmap();
                bundleImage.putParcelable("Uri", yourBitmap);
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
