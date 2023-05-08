package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AllCategoriesActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        menuItem1 = bottomNavigationView.getMenu().findItem(R.id.home);
        menuItem2 = bottomNavigationView.getMenu().findItem(R.id.sport);
        menuItem3 = bottomNavigationView.getMenu().findItem(R.id.account);
        menuItem2.setChecked(true);
    }

    private void TransferCategorieParameter(Categories categories) {
        Intent intent = new Intent(this, AllWorkoutsActivity.class);
        intent.putExtra("categorie", categories);
        startActivity(intent);

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
