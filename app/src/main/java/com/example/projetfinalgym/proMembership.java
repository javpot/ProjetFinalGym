package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class proMembership extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promembership);

    }

    public void back(View view) {
        Intent monInt = new Intent(this.getApplicationContext(),account.class);
        startActivity(monInt);
    }
}
