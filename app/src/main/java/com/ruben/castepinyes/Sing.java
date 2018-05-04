package com.ruben.castepinyes;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class Sing extends AppCompatActivity implements View.OnClickListener {
    private Button butonIn, butonReset;
    private EditText textUser, textPass;
    private String user, pass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private ArrayList<String> arrayList = new ArrayList();
    private ArrayAdapter<String> arrayAdapter;
    private DatabaseReference databaseReference;
    private String string = "";
    private Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sing);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        textUser = (EditText) findViewById(R.id.textUser);
        textPass = (EditText) findViewById(R.id.textPass);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        butonIn = (Button) findViewById(R.id.buttonEntrar);
        butonIn.setOnClickListener(this);

        butonReset = (Button) findViewById(R.id.Sing_button_resset);
        butonReset.setOnClickListener(this);

    }

    private void loginUser() {
        user = textUser.getText().toString().trim();
        pass = textPass.getText().toString().trim();
        user = "rubenniki@gmail.com";
        pass = "adminpassword";
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "El user no puede estar en blanco", Toast.LENGTH_SHORT).show();
            //paramos la funcion
            return;

        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "El pass no puede estar en blanco", Toast.LENGTH_SHORT).show();
            //paramos la funcion
            return;
        }
        //tofo fine

        progressDialog.setMessage("Iniciant sessió....");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //user esta registrado
                            Toast.makeText(Sing.this, "Has iniciat sessió", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Sing.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(Sing.this, "Error:No Has iniciat sessió", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    private void ressetPass() {

        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_SUBJECT, "Reset Password");
        Email.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"Castepinyes@gmail.com"});  //developer 's email
        Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer Name," + "\n");  //Email 's Greeting text
        startActivity(Intent.createChooser(Email, "Obre el mailper demanar una nova contraseña"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonEntrar:
                loginUser();
                break;
            case R.id.Sing_button_resset:
                ressetPass();
                break;
        }


    }


    private boolean isValidLogin(String textUser, String textPass) {
        if (textUser.equals("Ruben") && textPass.equals("123456")) {
            return true;
        }
        return false;
    }
}



