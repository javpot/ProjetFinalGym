package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AllCategoriesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
    }

    private void TransferCategorieParameter(Categories categories) {
        Intent intent = new Intent(this, AllWorkoutsActivity.class);
        intent.putExtra("categorie", categories);
        startActivity(intent);

    }
}
