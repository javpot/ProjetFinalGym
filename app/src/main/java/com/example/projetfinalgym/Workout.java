package com.example.projetfinalgym;

import android.media.Image;
import android.widget.CheckBox;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Workout implements Serializable {
    private Map<String,String> infos = new HashMap<>();

public Workout() {}
    public Workout(String image, String titre, String courtedescription,String longueDescription,String musclesSollicite, String execution, String lienYoutube ) {
        infos.put("Image",image);
        infos.put("Titre",titre);
        infos.put("Muscle sollicite",musclesSollicite);
        infos.put("Execution",execution);
        infos.put("Lien_Youtube",lienYoutube);
        infos.put("CourteDescription",courtedescription);
        infos.put("LongueDescription",longueDescription);

    }

    public Map<String, String> getInfos() {
        return infos;
    }

    public void setInfos(Map<String, String> infos) {
        this.infos = infos;
    }
}
