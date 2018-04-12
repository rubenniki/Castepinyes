package com.ruben.castepinyes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SelectorDePinyes extends AppCompatActivity implements View.OnClickListener {

    private ListView listview;
    private DatabaseReference databaseReference;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_de_pinyes);

        Button back= (Button) findViewById(R.id.back_menu);
        back.setOnClickListener(this);

    // Inflate the layout for this fragmentView


        listview = (ListView) findViewById(R.id.collas);
    databaseReference = FirebaseDatabase.getInstance().getReference();

    arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            String colla = String.valueOf(dataSnapshot.getRef());
            String[] separated = colla.split("/");
            int ultimo=separated.length-1;


               arrayList.add(separated[ultimo]);
                arrayAdapter.notifyDataSetChanged();



        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String colla = String.valueOf(dataSnapshot.getRef());
            String[] separated = colla.split("/");
            int ultimo=separated.length-1;


            arrayList.add(separated[ultimo]);
            arrayAdapter.notifyDataSetChanged();

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

            String colla = String.valueOf(dataSnapshot.getRef());
            String[] separated = colla.split("/");
            int ultimo=separated.length-1;


            arrayList.add(separated[ultimo]);
            arrayAdapter.notifyDataSetChanged();

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            String colla = String.valueOf(dataSnapshot.getRef());
            String[] separated = colla.split("/");
            int ultimo=separated.length-1;


            arrayList.add(separated[ultimo]);
            arrayAdapter.notifyDataSetChanged();

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre = listview.getItemAtPosition(position).toString();
                Toast.makeText(getApplication(), "Has seleccionadao la colla de "+nombre, Toast.LENGTH_LONG).show();

                Fragment login = new Fotos_Colla();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.selector_de_pinyes, login);
                transaction.addToBackStack(null);
                transaction.commit();

            }


        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back_menu:
                Intent SingActivity = new Intent(getApplicationContext(), Sing.class);
                startActivity(SingActivity);
                break;
        }
    }
}
