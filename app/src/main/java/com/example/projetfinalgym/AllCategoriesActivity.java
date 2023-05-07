package com.example.projetfinalgym;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AllCategoriesActivity extends AppCompatActivity {

    Workout workoutTriceps1 = new Workout();
    Workout workoutChest1 = new Workout();
    Workout workoutDos1 = new Workout();
    Workout workoutEtirements1 = new Workout();
    Workout workoutCardio1 = new Workout();
    Workout workoutJambes1 = new Workout();
    Workout workoutEpaules1 = new Workout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);



    }

}
