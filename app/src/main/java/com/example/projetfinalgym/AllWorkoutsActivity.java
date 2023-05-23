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
import android.widget.EditText;
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
                            Map<String, Object> infos = listOfExercises.get(i);
                            addWorkoutView(container, infos);
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
                Dialog dialogAdd = dialog(dialog);
                dialogAdd.setTitle("Ajouter un nouveau Workout");
                dialogAdd.show();
            }
        });
    }

    public Dialog dialog(Dialog dialog) {
        dialog.setContentView(R.layout.activity_form);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
            return dialog;
        }
        return null;
    }
    private void addWorkoutView(LinearLayout layout, Map<String,Object> infos) {
        View view = getLayoutInflater().inflate(R.layout.workout_item, null);

        Button update = view.findViewById(R.id.update);
        Button delete = view.findViewById(R.id.delete);
        TextView TitleView = view.findViewById(R.id.WorkoutTitle);
        ImageView imageView = view.findViewById(R.id.WorkoutImage);

        String titre = (String) infos.get("titre");
        TitleView.setText(titre);

        TitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //envoyer vers singleWorkout
            }
        });

        Dialog dialog = new Dialog(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogUpdate = dialog(dialog);
                EditText Nom = dialog.findViewById(R.id.NomExercice);
                EditText courteD = dialog.findViewById(R.id.CourteDescrip);
                EditText LongueD = dialog.findViewById(R.id.LongueDescrip);
                EditText Muscles = dialog.findViewById(R.id.MusclesSolicites);
                EditText Execution = dialog.findViewById(R.id.Execution);
                EditText LienYT = dialog.findViewById(R.id.LienYT);

                Nom.setText(titre);
                courteD.setText((String) infos.get("courtedescription"));
                LongueD.setText((String) infos.get("longueDescription"));
                System.out.print("HELLO " + (String) infos.get("longueDescription"));
                Muscles.setText((String) infos.get("musclesSollicite"));
                Execution.setText((String) infos.get("execution"));
                LienYT.setText((String) infos.get("lienYoutube"));
                dialogUpdate.setTitle("Modifier un Workout");
                dialogUpdate.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
/*
        String imageName = (String) infos.get("image");
        int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        imageView.setImageResource(resId);
*/

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
