package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText nameV, emailV, passwordV;
    String name, email, password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        nameV = findViewById(R.id.name);
        emailV = findViewById(R.id.email);
        passwordV = findViewById(R.id.password);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }
    private void CreateUser() {
        name = nameV.getText().toString();
        email = emailV.getText().toString();
        password = passwordV.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "La case Nom ne doit pas etre vide",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            // create user
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success
                            // set le nom du user
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdateRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();
                            user.updateProfile(profileUpdateRequest);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }
    public void SignUp(View view) {

        this.CreateUser();
        Intent monInt = new Intent(this.getApplicationContext(),MainActivity.class);
        startActivity(monInt);
    }


}
