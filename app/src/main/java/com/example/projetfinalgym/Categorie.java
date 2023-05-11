package com.example.projetfinalgym;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categorie implements Serializable {
    String titre;
    String image;
    List<Workout> exercices = new ArrayList<>();

    public Categorie(String titre, String image, List<Workout> exercices) {
        this.titre = titre;
        this.image = image;
        this.exercices.addAll(exercices);
    }
}
