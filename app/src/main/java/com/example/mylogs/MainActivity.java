package com.example.mylogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent main_Page = new Intent(MainActivity.this, MainPage.class);
            startActivity(main_Page);
            finish();


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }


    public void Login(View v){


        TextView gmail_text = findViewById(R.id.editTextTextEmailAddress2);
        TextView password_text = findViewById(R.id.editTextTextPassword);
        String email = gmail_text.getText().toString();
        String password = password_text.getText().toString();
        if(email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this, "Email or password is empty", Toast.LENGTH_SHORT).show();
            return;

        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information


                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                               Intent main_Page = new Intent(MainActivity.this, MainPage.class);



                                startActivity(main_Page);
                                finish();



                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });




        }










    }
    public void RegisterPage(View v){
        Intent register =  new Intent(this, Register.class);
        startActivity(register);

    }

}