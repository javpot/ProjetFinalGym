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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Workout> getExercices() {
        return exercices;
    }

    public void setExercices(List<Workout> exercices) {
        this.exercices = exercices;
    }
}
