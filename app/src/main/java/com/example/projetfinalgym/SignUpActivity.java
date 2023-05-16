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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText nameV, emailV, passwordV;
    String name, email, password;
    boolean accepted = false;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Workout workoutBiceps1 = new Workout(null,"Curl à la barre","Avec le curl debout avec la barre, le biceps brachial et le brachial antérieur sont assistés par le long supinateur et le rond pronateur.","Le biceps est constitué de deux faisceaux (longue portion et courte portion). Le brachial antérieur est situé sous le biceps, près du coude. Il intervient fortement dans la flexion du coude, quel que soit le type de prise adoptée. Logé sur la face supéro-externe de l’avant-bras, du côté du pouce, le long supinateur crée le galbe de l’avant-bras, depuis le coude jusqu’au pouce. Le rond pronateur n’intervient que lorsque la résistance est suffisamment importante. Recouvert en partie par le long supinateur, il est logé en oblique en travers du coude.","Biceps","Prenez une barre avec une prise en supination et de la largeur des épaules. Tenez-vous debout et tenez la barre au niveau des cuisses.\n" +
            "Positionnez vos pieds écartés de la largeur des hanches, les genoux légèrement fléchis. Tirez vos épaules vers l’arrière et sortez votre poitrine.\n" +
            "\n" +
            "Fléchissez les coudes pour faire monter la charge. Déplacez légèrement les coudes vers l’avant, mais pas plus que de quelques centimètres.\n" +
            "Soulevez la barre jusqu’à ce que vos avant-bras soient perpendiculaires au sol. Faites une courte pause pour contracter vos biceps.\n" +
            "Dépliez les coudes pour faire redescendre la barre. Ramenez les coudes dans leur position initiale.\n" +
            "Abaissez la barre jusqu’à ce que vous reveniez à la position de départ.\n" +
            "Répétez le mouvement jusqu’à effectuer le nombre de répétitions souhaitées.","https://www.youtube.com/watch?v=ZXYkt-pkcAQ" );

    Workout workoutTriceps1 = new Workout(null,"Barre front", "un exercice d’extension des triceps effectué couché avec une barre.", "Il s’agit d’un exercice populaire dont le principal avantage est d’obtenir un étirement très important du muscle, ce qui permet d’activer davantage de fibres pour l’hypertrophie musculaire.", "triceps", "Le barre front est l’un des meilleurs exercices de musculation des triceps qui soit. Asseyez-vous à l’extrémité d’un banc plat avec une barre d’haltères sur vos cuisses.\n" +
            "Saisissez la barre avec une prise en main en pronation (paumes vers le bas), les mains étant écartées de la largeur des épaules.\n" +
            "Allongez-vous sur le dos et amenez la barre sur votre poitrine. Tendez les bras au-dessus de cette dernière.\n" +
            "En gardant les coudes immobiles et non pointés vers l’extérieur, abaissez lentement la barre jusqu’à ce qu’elle soit à environ 2 cm de votre front.\n" +
            "Faites une pause, puis tendez lentement les bras pour revenir à la position de départ.\n" +
            "Ne verrouillez pas vos coudes, puis répétez l’exercice jusqu’à ce que vous ayez atteint le nombre de répétitions souhaité.", "https://www.youtube.com/watch?v=H3IYYdZYiwo");
    Workout workoutChest1 = new Workout(null, "Développé couché", "développé couché est l’un des premiers exercices que la plupart des pratiquants apprennent lorsqu’ils commencent la musculation. C’est de loin l’exercice le plus populaire effectué dans la plupart des salles de sport. Êtes-vous déjà allé un lundi dans une salle de muscu pour essayer de faire cet exercice sur banc ? Ce n’est tout simplement pas possible.", "Le développé couché est pratiqué comme un exercice pour le haut du corps, ciblant principalement les muscles pectoraux, et secondairement les triceps et épaules. Mais lorsqu’il est effectué correctement, il permet de travailler tout le corps, avec une sollicitation des jambes, un gainage de la sangle abdominale, et une stabilisation de la ceinture scapulaire.\n" +
            "\n" +
            "Cependant, lorsqu’il est mal exécuté, il peut causer de graves déséquilibres musculaires qui entraînent des douleurs chroniques à l’épaule. Inutile donc de vous dire que cela va compliquer la plupart de vos activités quotidiennes.", "les muscles pectoraux, triceps, epaules", "Tirez vos omoplates en arrière (et vers le bas si vous avez tendance à hausser les épaules).\n" +
            "Faites littéralement comme si vous essayiez d’étirer la barre en longueur. Cela activera davantage les dorsaux et les autres muscles du dos.\n" +
            "Bloquez vos muscles abdominaux, fessiers et quadriceps en les contractant bien fort. Rappelez-vous qu’il s’agit d’un exercice sollicitant tout le corps !", "https://www.youtube.com/watch?v=rT7DgCr-3pg");
    Workout workoutDos1 = new Workout(null, "traction", "La traction (en anglais pull up) est un exercice classique qui permet de cibler les muscles du haut du dos, en particulier le muscle grand dorsal.", "Les mouvements de traction verticale, tels que la traction classique (mains en pronation), sont des mouvements de base indispensables à vos séances d’entraînement. Après avoir trouvé une variante avec laquelle vous vous sentez à l’aise, pratiquez-la régulièrement pour gagner en force et avoir une plus belle silhouette.\n" +
            "\n" +
            "Les tractions peuvent être intégrées dans des entraînements pour le dos, des entraînements pour le haut du corps ou des entraînements de tout le corps. Personnellement, c’est mon exercice au poids du corps préféré que je fais pour garder la forme lorsque je suis en vacance ou en déplacement, et même à la maison lorsque la salle est fermée.", "grand dorsal", "Placez-vous sous une barre de traction. Sautez et saisissez la barre (mains en pronation) à une largeur légèrement supérieure à celle des épaules.\n" +
            "Vos épaules, le haut du dos et les hanches doivent être correctement alignés. Les bras sont parfaitement tendus et votre corps se trouve en suspension. C’est la position de départ.\n" +
            "Prenez une grande inspiration puis expirez. Hissez-vous ensuite vers le haut en contractant les muscles de votre dos.\n" +
            "Amenez le menton au-dessus de la barre jusqu’à ce que les dorsaux soient bien contractés, puis redescendez lentement à la position de départ.\n" +
            "Répétez le mouvement jusqu’au nombre de répétitions prévu.", "https://www.youtube.com/watch?v=eGo4IYlbE5g");
        Workout workoutEtirements1 = new Workout(null, "", "", "", "", "", "");
        Workout workoutCardio1 = new Workout(null, "", "", "", "", "", "");
        Workout workoutJambes1 = new Workout(null, "", "", "", "", "", "");

    Workout workoutEpaules1 = new Workout(null, "Le face pull", "Le face pull est un excellent moyen de renforcer les muscles de la coiffe des rotateurs comme le sous-épineux. Ceci permet de renforcer la stabilité et la force des omoplates et de rendre vos épaules plus résistantes aux blessures.", "Le face pull est un excellent moyen de renforcer les muscles de la coiffe des rotateurs comme le sous-épineux. Ceci permet de renforcer la stabilité et la force des omoplates et de rendre vos épaules plus résistantes aux blessures.\n" +
            "\n" +
            "De nombreux pratiquants privilégient les muscles pectoraux au détriment des muscles du dos, ce qui entraîne des déséquilibres musculaires et une mauvaise posture. Cet exercice peut s’avérer efficace pour corriger ces déséquilibres.", "épaules,dos,deltoïdes,rhomboïdes", "Attachez une corde sur une poulie placée à peu près au niveau de la poitrine.\n" +
            "Saisissez les deux extrémités de la corde avec une prise en pronation.\n" +
            "Reculez de manière à maintenir la charge avec les bras complètement tendus et mettez-vous debout avec les pieds écartés. Pliez légèrement les genoux pour assurer une certaine stabilité.\n" +
            "Rétractez les omoplates et tirez la corde vers le visage tout en écartant les coudes sur les côtés. Pensez à écarter les extrémités de la corde et pas seulement à la tirer vers l’arrière.\n" +
            "Maintenez la position haute pendant une seconde et descendez lentement.\n" +
            "Répétez ce mouvement jusqu’à ce que vous ayez atteint le nombre de répétitions souhaité.", "https://www.youtube.com/watch?v=rep-qVOkqgk");

    List<Workout> Bicepworkouts = new ArrayList<>();
    List<Workout> Tricepworkouts = new ArrayList<>();
    List<Workout> Chestworkouts = new ArrayList<>();
    List<Workout> Dosworkouts = new ArrayList<>();
    List<Workout> Etiremnetsworkouts = new ArrayList<>();
    List<Workout> Cardioworkouts = new ArrayList<>();
    List<Workout> Jambesworkouts = new ArrayList<>();
    List<Workout> Epaulesworkouts = new ArrayList<>();

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

                            //ajouter les donnees d'un user dans la base
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("name", name);
                            userData.put("email", email);

                            //creer les categories
                            Bicepworkouts.add(workoutBiceps1);
                            Tricepworkouts.add(workoutTriceps1);
                            Chestworkouts.add(workoutChest1);
                            Dosworkouts.add(workoutDos1);
                            Etiremnetsworkouts.add(workoutEtirements1);
                            Cardioworkouts.add(workoutCardio1);
                            Jambesworkouts.add(workoutJambes1);
                            Epaulesworkouts.add(workoutEpaules1);

                            Categorie Biceps = new Categorie("Biceps", "image", Bicepworkouts);
                            Categorie Triceps = new Categorie("Triceps", "image", Tricepworkouts);
                            Categorie Chest = new Categorie("Chest", "image", Chestworkouts);
                            Categorie Dos = new Categorie("Dos", "image", Dosworkouts);
                            Categorie Etirements = new Categorie("Etirements", "image", Etiremnetsworkouts);
                            Categorie Jambes = new Categorie("Jambes", "image", Cardioworkouts);
                            Categorie Cardio = new Categorie("Cardio", "image", Jambesworkouts);
                            Categorie Epaules = new Categorie("Epaules", "image", Epaulesworkouts);

                            //ajouter les categories dans la base
                            db.collection("Users").document(user.getUid()).set(userData)
                                    .addOnSuccessListener(Void -> {
                                        DocumentReference userRef = db.collection("Users").document(user.getUid());
                                        CollectionReference Categories = userRef.collection("Categories");
                                        Categories.document("Biceps").set(Biceps);
                                        Categories.document("Triceps").set(Triceps);
                                        Categories.document("Dos").set(Dos);
                                        Categories.document("Chest").set(Chest);
                                        Categories.document("Epaules").set(Epaules);
                                        Categories.document("Etirements").set(Etirements);
                                        Categories.document("Cardio").set(Cardio);
                                        Categories.document("Jambes").set(Jambes)
                                                .addOnSuccessListener(documentReference -> {
                                                    System.out.println("good");
                                                })
                                                .addOnFailureListener(e -> {
                                                    System.out.println("mauvais");
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
