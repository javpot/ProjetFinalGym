package com.example.projetfinalgym;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AllWorkoutsActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    Categories categorie = (Categories) getIntent().getSerializableExtra("categorie");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_workouts);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        db.collection("Users").document(user.getUid()).collection("Workout")
                .whereEqualTo("categorie", categorie)
                .get();

    }
}
