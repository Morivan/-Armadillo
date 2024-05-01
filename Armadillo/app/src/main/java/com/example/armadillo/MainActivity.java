package com.example.armadillo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText ETemail;
    private EditText ETpassword;

    private DatabaseReference mDataBase;
    private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Intent mainPageIntent = new Intent(MainActivity.this, MainPage.class);
            startActivity(mainPageIntent);
            finish();
        }
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

        init();
    }

    private void init(){
        ETemail = (EditText) findViewById(R.id.et_email);
        ETpassword = (EditText) findViewById(R.id.et_password);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
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
        if (TextUtils.isEmpty(email)){
            Toast.makeText(MainActivity.this, "Введите электронную почту!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Введите пароль!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.setLanguageCode("ru");
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    String id = myRef.push().getKey();
                    if (id != null){
                        DatabaseReference userRef = myRef.child(id);
                        userRef.child("email").setValue(email);
                        userRef.child("password").setValue(password);
                        Toast.makeText(MainActivity.this, "Пользователь авторизован", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    // Toast.makeText(MainActivity.this, "Auth complete", Toast.LENGTH_SHORT).show();
                    Intent mainPage = new Intent(MainActivity.this, MainPage.class);
                    startActivity(mainPage);
                }
                else{
                    Toast.makeText(MainActivity.this, "Ошибка авторизации", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void registration (String email, String password){
        if (TextUtils.isEmpty(email)){
            Toast.makeText(MainActivity.this, "Введите электронную почту!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Введите пароль!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.setLanguageCode("ru");
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    String id = myRef.push().getKey();
                    if (id != null){
                        DatabaseReference userRef = myRef.child(id);
                        userRef.child("email").setValue(email);
                        userRef.child("password").setValue(password);
                        Toast.makeText(MainActivity.this, "Пользователь зарегистрирован", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    Intent mainPage = new Intent(MainActivity.this, MainPage.class);
                    startActivity(mainPage);
                }
                else{
                    Toast.makeText(MainActivity.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void continue_as_guest(View v){
        Intent mainPage = new Intent(MainActivity.this, MainPage.class);
        startActivity(mainPage);
        finish();
    }
}