package layout;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ruben.castepinyes.R;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_pinye_de_6 extends Fragment implements View.OnClickListener {

    private ArrayList<String> arrayList =new ArrayList();
    private ArrayAdapter<String> arrayAdapter;
    private DatabaseReference databaseReference;
    private ListView listview;
    private String nombre="",nombrePersona;
    String string;

    private Button Baix_arriba,Baix_abajo,Baix_izquierda,Baix_arriba_2,Baix_derecha,Baix_izquierda_2,Agulla_arriba_2,Agulla_arriba,Agulla_derecha,Agulla_derecha_2,Agulla_abajo,Agulla_izquierda,Crosa_arriba_izquierda,Crosa_arriba_derecha,Crosa_derecha_abajo,Crosa_derecha_arriba_2,Crosa_dreta_arriba,Crosa_izquierda_arriba,Crosa_izquierda_abajo,Crosa_dreta_abajo,Crosa_dreta_arriba_2,Crosa_derecha_abajo_2,Crosa_abajo_izquierda,Crosa_abajo_derecha;


    public Fragment_pinye_de_6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pinye_de_6, container, false);

        Baix_abajo = (Button) view.findViewById(R.id.Baix_abajo);
        Baix_arriba = (Button) view.findViewById(R.id.Baix_arriba);
        Baix_derecha = (Button) view.findViewById(R.id.Baix_derecha);
        Baix_izquierda = (Button) view.findViewById(R.id.Baix_izquierda);
        Baix_izquierda_2 = (Button) view.findViewById(R.id.Baix_izquierda_2);
        Baix_arriba_2 = (Button) view.findViewById(R.id.Baix_arriba_2);
        Agulla_arriba_2=(Button) view.findViewById(R.id.Agulla_arriba_2);
        Agulla_arriba = (Button) view.findViewById(R.id.Agulla_arriba);
        Agulla_derecha = (Button) view.findViewById(R.id.Agulla_derecha);
        Agulla_derecha_2= (Button) view.findViewById(R.id.Agulla_derecha_2);
        Agulla_izquierda = (Button) view.findViewById(R.id.Agulla_izquierda);
        Agulla_abajo = (Button) view.findViewById(R.id.Agulla_abajo);
        Crosa_arriba_izquierda = (Button) view.findViewById(R.id.Crosa_arriba_izquierda);
        Crosa_arriba_derecha = (Button) view.findViewById(R.id.Crosa_arriba_derecha);
        Crosa_derecha_abajo = (Button) view.findViewById(R.id.Crosa_derecha_abajo);
        Crosa_derecha_arriba_2 = (Button) view.findViewById(R.id.Crosa_derecha_arriba_2);
        Crosa_dreta_arriba = (Button) view.findViewById(R.id.Crosa_dreta_arriba);
        Crosa_dreta_abajo = (Button) view.findViewById(R.id.Crosa_dreta_abajo);
        Crosa_dreta_arriba_2 = (Button) view.findViewById(R.id.Crosa_dreta_arriba_2);
        Crosa_derecha_abajo_2 = (Button) view.findViewById(R.id.Crosa_derecha_abajo_2);
        Crosa_abajo_izquierda  = (Button) view.findViewById(R.id.Crosa_abajo_izquierda);
        Crosa_abajo_derecha = (Button) view.findViewById(R.id.Crosa_abajo_derecha);
        Crosa_izquierda_arriba = (Button) view.findViewById(R.id.Crosa_izquierda_arriba);
        Crosa_izquierda_abajo = (Button) view.findViewById(R.id.Crosa_izquierda_abajo);

        Baix_izquierda.setOnClickListener(this);
        Baix_abajo.setOnClickListener(this);
        Baix_arriba.setOnClickListener(this);
        Baix_derecha.setOnClickListener(this);
        Baix_izquierda_2.setOnClickListener(this);
        Baix_arriba_2.setOnClickListener(this);
        Agulla_arriba_2.setOnClickListener(this);
        Agulla_arriba.setOnClickListener(this);
        Agulla_derecha.setOnClickListener(this);
        Agulla_derecha_2.setOnClickListener(this);
        Agulla_abajo.setOnClickListener(this);
        Agulla_izquierda.setOnClickListener(this);
        Crosa_arriba_izquierda.setOnClickListener(this);
        Crosa_arriba_derecha.setOnClickListener(this);
        Crosa_derecha_abajo.setOnClickListener(this);
        Crosa_derecha_arriba_2.setOnClickListener(this);
        Crosa_dreta_arriba.setOnClickListener(this);
        Crosa_dreta_abajo.setOnClickListener(this);
        Crosa_dreta_arriba_2.setOnClickListener(this);
        Crosa_derecha_abajo_2.setOnClickListener(this);
        Crosa_abajo_izquierda.setOnClickListener(this);
        Crosa_abajo_derecha.setOnClickListener(this);
        Crosa_izquierda_arriba.setOnClickListener(this);
        Crosa_izquierda_abajo.setOnClickListener(this);

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
        FloatingActionButton fab = view.findViewById(R.id.floatGuardar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("data");


                Bitmap foto=takeScreenshot();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();
                FirebaseStorage storage = FirebaseStorage.getInstance();

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat(";yyyy-MM-dd;HH:mm:ss");
                String formattedDate = df.format(c);

                databaseReference2.push().setValue("pinya"+formattedDate);

                StorageReference fotoRef = storage.getReference().child(nombrePersona.concat("/")).child("pinya"+formattedDate);
                fotoRef.getDownloadUrl();
                UploadTask uploadTask = fotoRef.putBytes(data);
uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        Toast.makeText(getContext(),"S'ha guardat la pinya",Toast.LENGTH_LONG).show();
    }
});
               /* Bundle bundle=new Bundle();
                bundle.putParcelable("foto",foto);
                Fragment fragment= new MostrarFotoPinyaFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);*/
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
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public Bitmap takeScreenshot() {
        Bitmap bitmap = null;
        try {
            // crear un bitmap con la captura de pantalla
            View v1 = getActivity().getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Baix_abajo:
                if (!nombre.equalsIgnoreCase("")) {
                    Baix_abajo.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Baix_arriba:
                if (!nombre.equalsIgnoreCase("")) {
                    Baix_arriba.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Baix_derecha:
                if (!nombre.equalsIgnoreCase("")) {
                    Baix_derecha.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Baix_izquierda_2:
                if (!nombre.equalsIgnoreCase("")) {
                    Baix_izquierda_2.setText(nombre);
                    nombre="";
                }
                break;
            case R.id.Baix_arriba_2:
                if (!nombre.equalsIgnoreCase("")) {
                    Baix_arriba_2.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Agulla_arriba_2:
                if (!nombre.equalsIgnoreCase("")) {
                    Agulla_arriba_2.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Agulla_arriba:
                if (!nombre.equalsIgnoreCase("")) {
                    Agulla_arriba.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.Agulla_derecha:
                if (!nombre.equalsIgnoreCase("")) {
                    Agulla_derecha.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Agulla_derecha_2:
                if (!nombre.equalsIgnoreCase("")) {
                    Agulla_derecha_2.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Agulla_abajo:
                if (!nombre.equalsIgnoreCase("")) {
                    Agulla_abajo.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_arriba_izquierda:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_arriba_izquierda.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_arriba_derecha:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_arriba_derecha.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_derecha_abajo:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_derecha_abajo.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_derecha_arriba_2:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_derecha_arriba_2.setText(nombre);
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
            case R.id.Crosa_dreta_abajo:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_dreta_abajo.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_dreta_arriba_2:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_dreta_arriba_2.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_derecha_abajo_2:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_derecha_abajo_2.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_abajo_izquierda:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_abajo_izquierda.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.Crosa_abajo_derecha:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_abajo_derecha.setText(nombre);
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
            case R.id.Agulla_izquierda:
                if (!nombre.equalsIgnoreCase("")) {
                    Agulla_izquierda.setText(nombre);
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
            case R.id.Crosa_izquierda_abajo:
                if (!nombre.equalsIgnoreCase("")) {
                    Crosa_izquierda_abajo.setText(nombre);
                    nombre="";
                }else{
                    Toast.makeText(getActivity(),"Selecciona un nombre de la lista",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
