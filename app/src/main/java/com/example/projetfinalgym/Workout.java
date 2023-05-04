package com.example.projetfinalgym;

import android.media.Image;
import android.widget.CheckBox;

import java.util.HashMap;
import java.util.Map;

public class Workout {
    private Image image;
    private CheckBox categorie;
    private Map<String,Object> infos = new HashMap<>();


    public Workout(Image image, Categories categorie, String titre, String courtedescription,String longueDescription,String musclesSollicite, String execution, String lienYoutube ) {
        infos.put("Image",image);
        infos.put("Categorie",categorie);
        infos.put("Titre",titre);
        infos.put("Muscle sollicite",musclesSollicite);
        infos.put("Execution",execution);
        infos.put("lien_Youtube",lienYoutube);
        infos.put("courteDescription",courtedescription);
        infos.put("LongueDescription",longueDescription);

    }
}
