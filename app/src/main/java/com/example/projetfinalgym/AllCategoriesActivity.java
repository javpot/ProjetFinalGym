package com.example.projetfinalgym;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AllCategoriesActivity extends AppCompatActivity {
    Workout workoutBiceps1 = new Workout(null,Categories.Biceps,"Curl à la barre","Avec le curl debout avec la barre, le biceps brachial et le brachial antérieur sont assistés par le long supinateur et le rond pronateur.","Le biceps est constitué de deux faisceaux (longue portion et courte portion). Le brachial antérieur est situé sous le biceps, près du coude. Il intervient fortement dans la flexion du coude, quel que soit le type de prise adoptée. Logé sur la face supéro-externe de l’avant-bras, du côté du pouce, le long supinateur crée le galbe de l’avant-bras, depuis le coude jusqu’au pouce. Le rond pronateur n’intervient que lorsque la résistance est suffisamment importante. Recouvert en partie par le long supinateur, il est logé en oblique en travers du coude.","Biceps","Prenez une barre avec une prise en supination et de la largeur des épaules. Tenez-vous debout et tenez la barre au niveau des cuisses.\n" +
            "Positionnez vos pieds écartés de la largeur des hanches, les genoux légèrement fléchis. Tirez vos épaules vers l’arrière et sortez votre poitrine.\n" +
            "\n" +
            "Fléchissez les coudes pour faire monter la charge. Déplacez légèrement les coudes vers l’avant, mais pas plus que de quelques centimètres.\n" +
            "Soulevez la barre jusqu’à ce que vos avant-bras soient perpendiculaires au sol. Faites une courte pause pour contracter vos biceps.\n" +
            "Dépliez les coudes pour faire redescendre la barre. Ramenez les coudes dans leur position initiale.\n" +
            "Abaissez la barre jusqu’à ce que vous reveniez à la position de départ.\n" +
            "Répétez le mouvement jusqu’à effectuer le nombre de répétitions souhaitées.","https://www.youtube.com/watch?v=ZXYkt-pkcAQ" );

    Workout workoutTriceps1;

    Map<String, Object> Biceps = new HashMap<>();
    Map<String, Object> Triceps = new HashMap<>();
    Map<String, Object> Chest = new HashMap<>();
    Map<String, Object> Dos = new HashMap<>();
    Map<String, Object> Etirements = new HashMap<>();
    Map<String, Object> Cardio = new HashMap<>();
    Map<String, Object> Jambes = new HashMap<>();
    Map<String, Object> Epaules = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("biceps")
                .add(workoutBiceps1);
        db.collection("triceps");
        db.collection("chest");
        db.collection("dos");
        db.collection("etirements");
        db.collection("cardio");
        db.collection("jambes");
        db.collection("epaules");

    }


}
