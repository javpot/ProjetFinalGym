package com.example.projetfinalgym;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Map;

public class SingleWorkoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_workout);

        Intent intent = getIntent();
        Map<String, String> receivedDataMap = (Map<String, String>) intent.getSerializableExtra("infos");

            String nom = receivedDataMap.get("titre");
            String courteDescription = receivedDataMap.get("courtedescription");
            String longueDescription = receivedDataMap.get("longueDescription");
            String musclesS = receivedDataMap.get("musclesSollicite");
            String execution = receivedDataMap.get("execution");
            String lienYT = receivedDataMap.get("lienYoutube");

        VideoView videoView = findViewById(R.id.videoView);
        TextView NomView = findViewById(R.id.NomExercice);
        TextView courteDescriptionView = findViewById(R.id.CourteDescrip);
        TextView longueDescriptionView = findViewById(R.id.LongueDescrip);
        TextView musclesSView = findViewById(R.id.MusclesSolicites);
        TextView executionView = findViewById(R.id.Execution);

        NomView.setText(nom);
        courteDescriptionView.setText("Courte description: \n" + courteDescription);
        longueDescriptionView.setText("Longue description: \n" + longueDescription);
        musclesSView.setText("Muscles Solicites: \n" + musclesS);
        executionView.setText("Execution: \n" + execution);

        Uri uri = Uri.parse(lienYT);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
    }
}
