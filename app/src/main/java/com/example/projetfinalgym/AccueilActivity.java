package com.example.projetfinalgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccueilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

    }

    public void ViewSignUp(View view) {
        Intent monInt = new Intent(this.getApplicationContext(),SignUpActivity.class);
        startActivity(monInt);
    }

    public void ViewLogIn(View view) {
        Intent monInt = new Intent(this.getApplicationContext(),LogInActivity.class);
        startActivity(monInt);
    }
}