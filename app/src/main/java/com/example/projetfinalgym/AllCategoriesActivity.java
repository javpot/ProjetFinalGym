package com.example.projetfinalgym;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Console;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.util.ArrayList;

public class AllCategoriesActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;
    LinearLayout leftContainer;
    LinearLayout rightContainer;

    FirebaseAuth mAuth ;
    private ArrayList<Categorie> categories = new ArrayList<>();
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

        leftContainer = findViewById(R.id.leftContainer);
        rightContainer = findViewById(R.id.rightContainer);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
       CollectionReference collectionReference = db.collection("Users").document(user.getUid()).collection("Categories");
       collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {
               for (int i = 0; i < task.getResult().size(); i++) {
                   if(i < task.getResult().size() / 2)
                        addCategorieView(leftContainer, task.getResult().getDocuments().get(i));
                   else
                       addCategorieView(rightContainer,task.getResult().getDocuments().get(i));
               }
           }
       });
    }

    private void addCategorieView(LinearLayout layout ,DocumentSnapshot doc) {
            View view = getLayoutInflater().inflate(R.layout.categorie_item, null);

            TextView TitleView = view.findViewById(R.id.CategorieTitle);
        ImageView imageView = view.findViewById(R.id.CategorieImage);

        TitleView.setText(doc.getString("titre"));

        String imageName = doc.getString("image");
        int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        imageView.setImageResource(resId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event(doc);
            }
        });

        layout.addView(view);
    }

    public void event(DocumentSnapshot documentSnapshot) {
        Intent intent = new Intent(this, AllWorkoutsActivity.class);
        String documentId = documentSnapshot.getId();
        String documentPath = documentSnapshot.getReference().getPath();

        intent.putExtra("documentId", documentId);
        intent.putExtra("documentPath", documentPath);
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
