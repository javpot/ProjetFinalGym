package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class SingleWorkoutActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_workout);

        Intent intent = getIntent();
        Map<String, String> infos = (Map<String, String>) intent.getSerializableExtra("infos");

            String nom = infos.get("titre");
            String courteDescription = infos.get("courtedescription");
            String longueDescription = infos.get("longueDescription");
            String musclesS = infos.get("musclesSollicite");
            String execution = infos.get("execution");
            String lienYT = infos.get("lienYoutube");

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

        webView = findViewById(R.id.WebView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(lienYT);
    }
}
//AIzaSyBOsK0T2M2WwF5j5z6_giVHVYhEAIch4BU -- YT API key