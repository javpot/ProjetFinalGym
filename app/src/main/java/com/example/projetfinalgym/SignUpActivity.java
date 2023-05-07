package com.example.projetfinalgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText nameV, emailV, passwordV;
    String name, email, password;

    boolean accepted = false;
    Workout workoutBiceps1 = new Workout(null,Categories.Biceps,"Curl à la barre","Avec le curl debout avec la barre, le biceps brachial et le brachial antérieur sont assistés par le long supinateur et le rond pronateur.","Le biceps est constitué de deux faisceaux (longue portion et courte portion). Le brachial antérieur est situé sous le biceps, près du coude. Il intervient fortement dans la flexion du coude, quel que soit le type de prise adoptée. Logé sur la face supéro-externe de l’avant-bras, du côté du pouce, le long supinateur crée le galbe de l’avant-bras, depuis le coude jusqu’au pouce. Le rond pronateur n’intervient que lorsque la résistance est suffisamment importante. Recouvert en partie par le long supinateur, il est logé en oblique en travers du coude.","Biceps","Prenez une barre avec une prise en supination et de la largeur des épaules. Tenez-vous debout et tenez la barre au niveau des cuisses.\n" +
            "Positionnez vos pieds écartés de la largeur des hanches, les genoux légèrement fléchis. Tirez vos épaules vers l’arrière et sortez votre poitrine.\n" +
            "\n" +
            "Fléchissez les coudes pour faire monter la charge. Déplacez légèrement les coudes vers l’avant, mais pas plus que de quelques centimètres.\n" +
            "Soulevez la barre jusqu’à ce que vos avant-bras soient perpendiculaires au sol. Faites une courte pause pour contracter vos biceps.\n" +
            "Dépliez les coudes pour faire redescendre la barre. Ramenez les coudes dans leur position initiale.\n" +
            "Abaissez la barre jusqu’à ce que vous reveniez à la position de départ.\n" +
            "Répétez le mouvement jusqu’à effectuer le nombre de répétitions souhaitées.","https://www.youtube.com/watch?v=ZXYkt-pkcAQ" );

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        nameV = findViewById(R.id.name);
        emailV = findViewById(R.id.email);
        passwordV = findViewById(R.id.password);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }
    private void CreateUser() {
        name = nameV.getText().toString();
        email = emailV.getText().toString();
        password = passwordV.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "La case Nom ne doit pas etre vide",
                    Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(SignUpActivity.this, "Le mot de passe doit etre au moins 6 caracteres",
                    Toast.LENGTH_SHORT).show();
        } else {
            // create user
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign up success
                            // set le nom du user
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdateRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();
                            user.updateProfile(profileUpdateRequest);
                            accepted = true;
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("name", name);
                            userData.put("email", email);

                            db.collection("Users").document(user.getUid()).set(userData)
                                    .addOnSuccessListener(aVoid -> {
                                        DocumentReference userRef = db.collection("Users").document(user.getUid());
                                        CollectionReference newCollectionRef = userRef.collection("Workout");
                                        newCollectionRef.add(workoutBiceps1)
                                                .addOnSuccessListener(documentReference -> {
                                                    System.out.println("good");
                                                })
                                                .addOnFailureListener(e -> {
                                                    System.out.println("mauvaias");
                                                });


                                        // passer a l'activity Transfer
                                        Intent monInt = new Intent(this.getApplicationContext(), TransferActivity.class);
                                        startActivity(monInt);
                                    });
                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "email invalide",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }

    public void SignUp(View view) {
        this.CreateUser();
        if (accepted == true) {
            accepted = false;
        }
    }


}
