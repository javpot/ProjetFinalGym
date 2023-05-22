package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView bonjour;
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
         menuItem1 = bottomNavigationView.getMenu().findItem(R.id.home);
         menuItem2 = bottomNavigationView.getMenu().findItem(R.id.sport);
         menuItem3 = bottomNavigationView.getMenu().findItem(R.id.account);
         menuItem1.setChecked(true);


        //Message bonjour personalise
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userName = user.getDisplayName();
        bonjour = findViewById(R.id.textView7);
        bonjour.setText("Bonjour " + userName + " \uD83D\uDC4B");

    }
    public void ViewWorkouts(View view) {
        Intent monInt = new Intent(this.getApplicationContext(), AllCategoriesActivity.class);
        startActivity(monInt);
    }

    public void ViewLegWorkouts(View view) {
        String nom = "Jambes";
        event(nom);

    }
    public void ViewChestWorkouts(View view) {
        String nom = "Chest";
        event(nom);

    }
    public void ViewBicepWorkouts(View view) {
        String nom = "Biceps";
        event(nom);
    }

    public void event (String name) {
        Intent intent = new Intent(this, AllWorkoutsActivity.class);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("Users").document(user.getUid()).collection("Categories");
        DocumentReference documentReference = collectionReference.document(name);
        String documentPath = documentReference.getPath();

        intent.putExtra("documentPath", documentPath);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent monInt = new Intent(this.getApplicationContext(), MainActivity.class);
                startActivity(monInt);
                return true;
            case R.id.sport:
                Intent monInt1 = new Intent(this.getApplicationContext(), AllCategoriesActivity.class);
                startActivity(monInt1);
                return true;
            case R.id.account:

                return true;

        }
        return false;
    }
    }

