package com.example.projetfinalgym;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddWorkoutActivity extends AppCompatActivity {
    EditText Nom, CourteDesc, LongueDesc, Muscles, ExecutionExercice, LienYT;
    CheckBox Biceps,Chest,Triceps,Epaules,Cardio,Etirements,Jambes,Dos;

    Button Done;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Nom = findViewById(R.id.edit_text1);
        CourteDesc = findViewById(R.id.edit_text2);
        LongueDesc = findViewById(R.id.edit_text3);
        Muscles = findViewById(R.id.edit_text4);
        ExecutionExercice = findViewById(R.id.edit_text5);
        LienYT = findViewById(R.id.edit_text6);

        Biceps = findViewById(R.id.checkbox1);
        Chest = findViewById(R.id.checkbox3);
        Triceps = findViewById(R.id.checkbox2);
        Epaules = findViewById(R.id.checkbox4);
        Cardio = findViewById(R.id.checkbox6);
        Etirements = findViewById(R.id.checkbox5);
        Jambes = findViewById(R.id.checkbox7);
        Dos = findViewById(R.id.checkbox8);

        Done = findViewById(R.id.buttonDone);

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
