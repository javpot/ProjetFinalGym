package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class SingleWorkoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_workout);

        Intent intent = getIntent();
        Map<String, String> receivedDataMap = (Map<String, String>) intent.getSerializableExtra("infos");

        if (receivedDataMap != null) {
            String nom = receivedDataMap.get("titre");
            String courteDescription = receivedDataMap.get("courtedescription");
            String longueDescription = receivedDataMap.get("longueDescription");
            String musclesS = receivedDataMap.get("musclesSollicite");
            String execution = receivedDataMap.get("execution");
            String lienYT = receivedDataMap.get("lienYoutube");
        }

    }
}
