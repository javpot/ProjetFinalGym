package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView bonjour;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Message bonjour personalise
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userName = auth.getCurrentUser().getDisplayName();
        bonjour = findViewById(R.id.textView7);
        bonjour.setText("Hi " + userName + " \uD83D\uDC4B");

    }
    public void ViewWorkouts(View view) {
        Intent monInt = new Intent(this.getApplicationContext(), AllCategoriesActivity.class);
        startActivity(monInt);
    }

    public void ViewLegWorkouts(View view) {
        Intent monInt = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(monInt);
    }
    public void ViewChestWorkouts(View view) {
        Intent monInt = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(monInt);
    }
    public void ViewBicepWorkouts(View view) {
        Intent monInt = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(monInt);
    }
    }
