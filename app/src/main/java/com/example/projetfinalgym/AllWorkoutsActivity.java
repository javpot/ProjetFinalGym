package com.example.projetfinalgym;

import android.accessibilityservice.GestureDescription;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

public class AllWorkoutsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;

    Button add;
    Dialog dialog;
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

        add = findViewById(R.id.addWorkout);
        LinearLayout container = findViewById(R.id.Container);

        Intent intent = getIntent();
        String documentPath = intent.getStringExtra("documentPath");

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentRef = firestore.document(documentPath);

        documentRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        List<Map<String, Object>> listOfExercises = (List<Map<String, Object>>) document.get("exercices");
                        for (int i = 0; i < listOfExercises.size(); i++) {
                            Map<String, Object> exercise = listOfExercises.get(i);
                            String workoutTitle = (String) exercise.get("titre");

                            System.out.println("Workout Title: " + workoutTitle);
                            addWorkoutView(container, workoutTitle);
                        }
                    } else {
                        // Document doesn't exist
                        System.out.println("Workout document not found.");
                    }
                } else {
                    // Error occurred while retrieving the document
                    System.out.println("Failed to retrieve workout document: " + task.getException());
                }
            }
        });

        Dialog dialog = new Dialog(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.activity_form);
                Window window = dialog.getWindow();
                if (window != null) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(window.getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
                    window.setAttributes(layoutParams);
                }
                dialog.setTitle("Ajouter un nouveau Workout");
                dialog.show();

            }
        });
    }

    private void addWorkoutView(LinearLayout layout,    String w) {
        View view = getLayoutInflater().inflate(R.layout.workout_item, null);

        TextView TitleView = view.findViewById(R.id.WorkoutTitle);
        ImageView imageView = view.findViewById(R.id.WorkoutImage);
        TitleView.setText(w);

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
