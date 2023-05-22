package com.example.projetfinalgym;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AllWorkoutsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;
    Button add;
    AlertDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_workouts);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        menuItem1 = bottomNavigationView.getMenu().findItem(R.id.home);
        menuItem2 = bottomNavigationView.getMenu().findItem(R.id.sport);
        menuItem3 = bottomNavigationView.getMenu().findItem(R.id.account);
        menuItem2.setChecked(true);


        LinearLayout container = findViewById(R.id.Container);

        Intent intent = getIntent();
        String documentPath = intent.getStringExtra("documentPath");

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentRef = firestore.document(documentPath);
        documentRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                List listOfExercices = Arrays.asList(task.getResult().get("exercices"));
                for (int i = 0; i < listOfExercices.size(); i++) {
                    addWorkoutView(container, listOfExercices);
                }
            }
        });

        buildDialog();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.activity_form, null);


        builder.setView(view);
        builder.setTitle("Ajouter un nouveau Workout")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                  //      addWorkoutView();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        dialog = builder.create();
    }
    public void addWorkoutView(LinearLayout layout, List list) {
        View view = getLayoutInflater().inflate(R.layout.workout_item, null);

        TextView TitleView = view.findViewById(R.id.WorkoutTitle);
        ImageView imageView = view.findViewById(R.id.WorkoutImage);

                 //   String imageName = value;
                    //       int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
                    //       imageView.setImageResource(resId);

                 //   TitleView.setText(value);

        layout.addView(view);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent monInt = new Intent(this.getApplicationContext(), MainActivity.class);
                startActivity(monInt);
                return true;
            case R.id.sport:

                return true;
            case R.id.account:

                return true;

        }
        return false;
    }

}
