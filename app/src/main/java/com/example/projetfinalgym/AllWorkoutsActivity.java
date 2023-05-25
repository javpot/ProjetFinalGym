package com.example.projetfinalgym;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AllWorkoutsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;
    Button add;
    LinearLayout container;
    String nomCategorie;
    List<Map<String, String>> listOfExercises;
    FirebaseFirestore firestore;
    DocumentReference documentRef;
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
        container = findViewById(R.id.Container);

        Intent intent = getIntent();
        String documentPath = intent.getStringExtra("documentPath");

        firestore = FirebaseFirestore.getInstance();
        documentRef = firestore.document(documentPath);

        documentRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    nomCategorie = (String) document.get("titre");
                    if (document.exists()) {
                        listOfExercises = (List<Map<String, String>>) document.get("exercices");
                        for (int i = 0; i < listOfExercises.size(); i++) {
                            Map<String, String> infos = listOfExercises.get(i);
                            addWorkoutView(container, infos, i);

                        }
                    }
                }
            }
        });

        Dialog dialog = new Dialog(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogAdd = dialogReSize(dialog);
                dialogAdd.show();
                addWorkout(dialogAdd);
            }
        });
    }

    public Dialog dialogReSize(Dialog dialog) {
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

    public void addWorkout(Dialog dialog) {
        EditText nomView = dialog.findViewById(R.id.NomExercice);
        EditText CDView = dialog.findViewById(R.id.CourteDescrip);
        EditText LDView = dialog.findViewById(R.id.LongueDescrip);
        EditText MSView = dialog.findViewById(R.id.MusclesSolicites);
        EditText EView = dialog.findViewById(R.id.Execution);
        EditText LView = dialog.findViewById(R.id.LienYT);

        setChecked(dialog,nomCategorie);

        Button terminer = dialog.findViewById(R.id.Terminer);
        terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nomView.getText().toString();
                String cd = CDView.getText().toString();
                String ld = LDView.getText().toString();
                String ms = MSView.getText().toString();
                String e = EView.getText().toString();
                String lyt = LView.getText().toString();

                Workout newExercice = new Workout("image",nom,cd,ld,ms,e,lyt);

                listOfExercises.add(newExercice.getInfos());
                documentRef.update("exercices", listOfExercises);
                addWorkoutView(container, newExercice.getInfos(), listOfExercises.size() - 1);
                dialog.dismiss();
            }
        });
    }

    public void updateWorkout(Dialog dialog, Workout oldWorkout) {
        EditText nomView = dialog.findViewById(R.id.NomExercice);
        EditText CDView = dialog.findViewById(R.id.CourteDescrip);
        EditText LDView = dialog.findViewById(R.id.LongueDescrip);
        EditText MSView = dialog.findViewById(R.id.MusclesSolicites);
        EditText EView = dialog.findViewById(R.id.Execution);
        EditText LView = dialog.findViewById(R.id.LienYT);

        Button terminer = dialog.findViewById(R.id.Terminer);
        terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nomView.getText().toString();
                String cd = CDView.getText().toString();
                String ld = LDView.getText().toString();
                String ms = MSView.getText().toString();
                String e = EView.getText().toString();
                String lyt = LView.getText().toString();

                // update dans la list et la bd
                Workout Newworkout = new Workout("image", nom,cd,ld,ms,e,lyt);
                int index = listOfExercises.indexOf(oldWorkout.getInfos());
                listOfExercises.get(index).clear();
                listOfExercises.get(index).putAll(Newworkout.getInfos());
                documentRef.update("exercices", listOfExercises);

                // update visuellement
               View viewOldWorkout = container.getChildAt(index);
               container.removeView(viewOldWorkout);
               addWorkoutView(container, Newworkout.getInfos(), index);
                dialog.dismiss();
            }
        });
    }
    private void addWorkoutView(LinearLayout layout, Map<String,String> infos, int index) {
        View view = getLayoutInflater().inflate(R.layout.workout_item, null);

        Button update = view.findViewById(R.id.update);
        Button delete = view.findViewById(R.id.delete);
        TextView TitleView = view.findViewById(R.id.WorkoutTitle);
        ImageView imageView = view.findViewById(R.id.WorkoutImage);

        String titre = infos.get("titre");
        TitleView.setText(titre);

        TitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent monInt = new Intent(getApplicationContext(), SingleWorkoutActivity.class);
                monInt.putExtra("infos", (Serializable) infos);
                startActivity(monInt);
            }
        });

        Dialog dialog = new Dialog(this);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogUpdate = dialogReSize(dialog);

                EditText Nom = dialog.findViewById(R.id.NomExercice);
                EditText courteD = dialog.findViewById(R.id.CourteDescrip);
                EditText LongueD = dialog.findViewById(R.id.LongueDescrip);
                EditText Muscles = dialog.findViewById(R.id.MusclesSolicites);
                EditText Execution = dialog.findViewById(R.id.Execution);
                EditText LienYT = dialog.findViewById(R.id.LienYT);

                setChecked(dialog,nomCategorie);

                Nom.setText(titre);
                courteD.setText(infos.get("courtedescription"));
                LongueD.setText(infos.get("longueDescription"));
                Muscles.setText(infos.get("musclesSollicite"));
                Execution.setText(infos.get("execution"));
                LienYT.setText(infos.get("lienYoutube"));

                Workout workout = new Workout("image", titre, infos.get("courtedescription"), infos.get("longueDescription"), infos.get("musclesSollicite"), infos.get("execution"), infos.get("lienYoutube"));
                dialogUpdate.show();
                updateWorkout(dialogUpdate, workout);


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

        layout.addView(view, index);
    }

    public void setChecked(Dialog dialog, String nomCategorie) {
        CheckBox Biceps = dialog.findViewById(R.id.Biceps);
        CheckBox Chest = dialog.findViewById(R.id.Chest);
        CheckBox Triceps = dialog.findViewById(R.id.Triceps);
        CheckBox Epaules = dialog.findViewById(R.id.Epaules);
        CheckBox Cardio = dialog.findViewById(R.id.Cardio);
        CheckBox Etirements = dialog.findViewById(R.id.Etirements);
        CheckBox Jambes = dialog.findViewById(R.id.Jambes);
        CheckBox Dos = dialog.findViewById(R.id.Dos);

        switch (nomCategorie) {
            case "Biceps": {
                Biceps.setChecked(true);
                break;
            }
            case "Chest": {
                Chest.setChecked(true);
                break;
            }
            case "Triceps": {
                Triceps.setChecked(true);
                break;
            }
            case "Epaules": {
                Epaules.setChecked(true);
                break;
            }
            case "Cardio": {
                Cardio.setChecked(true);
                break;
            }
            case "Etirements": {
                Etirements.setChecked(true);
                break;
            }
            case "Jambes": {
                Jambes.setChecked(true);
                break;
            }
            case "Dos": {
                Dos.setChecked(true);
                break;
            }
            default: break;
        }

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
                Intent monInt1 = new Intent(this.getApplicationContext(), account.class);
                startActivity(monInt1);
                return true;

        }
        return false;
    }

}
