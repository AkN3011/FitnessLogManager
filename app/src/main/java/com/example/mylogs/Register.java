package com.example.mylogs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent main_Page = new Intent(Register.this, MainPage.class);
            startActivity(main_Page);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View v){
        TextView Email_getText = findViewById(R.id.editTextTextEmailAddress);
        TextView password_getText = findViewById(R.id.editTextTextPassword2);

        String email = String.valueOf(Email_getText.getText());
        String password = String.valueOf(password_getText.getText());

        if(email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this, "Email or password is empty", Toast.LENGTH_SHORT).show();
            return;

        } else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // Sign in success, update UI with the signed-in user's information

                                Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();

                                Intent login = new Intent(Register.this, MainActivity.class);
                                startActivity(login);




                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(Register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


        }

    }



}