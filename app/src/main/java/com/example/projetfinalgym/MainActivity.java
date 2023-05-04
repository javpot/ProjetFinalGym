package com.example.projetfinalgym;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userName = auth.getCurrentUser().getDisplayName();
        TextView bonjour = findViewById(R.id.textView7);
        bonjour.setText("Hi " + userName);
    }
    }
