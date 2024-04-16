package com.example.armadillo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText ETemail;
    private EditText ETpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){

                }
                else {

                }
            }
        };

        ETemail = (EditText) findViewById(R.id.et_email);
        ETpassword = (EditText) findViewById(R.id.et_password);

        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_registration).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_sign_in) {
            signing(ETemail.getText().toString(), ETpassword.getText().toString());
        }
        else if (view.getId() == R.id.btn_registration){
            registration(ETemail.getText().toString(), ETpassword.getText().toString());
        }
    }

    public void signing(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Auth complete", Toast.LENGTH_SHORT).show();

                    Intent mainPage = new Intent(MainActivity.this, MainPage.class);
                    startActivity(mainPage);
                }
                else{
                    Toast.makeText(MainActivity.this, "Auth failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void registration (String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Успешно зарегистрирован!", Toast.LENGTH_SHORT).show();
                    Intent mainPage = new Intent(MainActivity.this, MainPage.class);
                    startActivity(mainPage);
                }
                else{
                    Toast.makeText(MainActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void continue_as_guest(View v){
        Intent mainPage = new Intent(MainActivity.this, MainPage.class);
        startActivity(mainPage);
    }
}