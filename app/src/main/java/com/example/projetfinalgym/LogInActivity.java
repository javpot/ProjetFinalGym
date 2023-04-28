package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText emailV, passwordV;
    String email, password;

    boolean accepted = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailV = findViewById(R.id.email);
        passwordV = findViewById(R.id.password);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }
    private void login() {
        if (email.isEmpty()) {
            Toast.makeText(LogInActivity.this, "La case email ne doit pas etre vide",
                    Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(LogInActivity.this, "La case password ne doit pas etre vide",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
               if (task.isSuccessful()) {
                    accepted = true;
               }
               else {
                   Toast.makeText(LogInActivity.this, "Login failed",
                           Toast.LENGTH_SHORT).show();
               }
            });
        }

    }
    public void Login(View view) {
        this.login();
        if (accepted == true) {
            accepted = false;
            Intent monInt = new Intent(this.getApplicationContext(),MainActivity.class);
            startActivity(monInt);
        }
    }

}
