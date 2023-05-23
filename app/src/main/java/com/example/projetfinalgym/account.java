package com.example.projetfinalgym;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class account extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Context context;
    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        menuItem1 = bottomNavigationView.getMenu().findItem(R.id.home);
        menuItem2 = bottomNavigationView.getMenu().findItem(R.id.sport);
        menuItem3 = bottomNavigationView.getMenu().findItem(R.id.account);
        menuItem3.setChecked(true);

        context = this.getApplicationContext();
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        nameEditText.setText(currentUser.getDisplayName());
        emailEditText.setText(currentUser.getEmail());


    }
    public void SignOut(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Deconnexion");
        alertDialogBuilder.setMessage("Etes vous sur de vouloir vous deconnecter?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);            }
        });

        alertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialogBuilder.show();
    }

    public void proMembershipView(View view) {
        Intent monInt = new Intent(this.getApplicationContext(),proMembership.class);
        startActivity(monInt);
    }

    public void ModifierNom(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modification du username");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newDisplayName = input.getText().toString();


                if (currentUser != null) {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(newDisplayName)
                            .build();

                    currentUser.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        nameEditText.setText(newDisplayName);
                                        Toast.makeText(account.this, "nom change avec succes",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(account.this, "nom invalid",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void modifierEmail(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modification du courriel");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newEmail = input.getText().toString();

                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = auth.getCurrentUser();

                if (currentUser != null) {
                    currentUser.updateEmail(newEmail)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    emailEditText.setText(newEmail);
                                    Toast.makeText(account.this, "email change avec succes",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(account.this, "email invalid",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void modifierPassword(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modification du mot de passe");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newPassword = input.getText().toString();

                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = auth.getCurrentUser();

                if (currentUser != null) {
                    currentUser.updatePassword(newPassword)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    passwordEditText.setText(newPassword);
                                    Toast.makeText(account.this, "mot de passe change avec succes",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(account.this, "mot de passe invalid",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent monInt = new Intent(this.getApplicationContext(), MainActivity.class);
                startActivity(monInt);
                return true;
            case R.id.sport:
                Intent monInt1 = new Intent(this.getApplicationContext(), AllCategoriesActivity.class);
                startActivity(monInt1);
                return true;
            case R.id.account:

                return true;

        }
        return false;
    }
}
