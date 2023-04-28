package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TransferActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
    }

    public void Transfer(View view) {
        Intent monInt = new Intent(this.getApplicationContext(),MainActivity.class);
        startActivity(monInt);
    }

}
