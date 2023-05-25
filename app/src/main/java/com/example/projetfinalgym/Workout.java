package com.example.projetfinalgym;

import android.media.Image;
import android.widget.CheckBox;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Workout implements Serializable {
    private Map<String,String> infos = new HashMap<>();
    private String image;
    private String titre;
    private String courtedescription;
    private String longueDescription;
    private String musclesSollicite;
    private String execution;
    private String lienYoutube;

public Workout() {}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCourtedescription() {
        return courtedescription;
    }

    public void setCourtedescription(String courtedescription) {
        this.courtedescription = courtedescription;
    }

    public String getLongueDescription() {
        return longueDescription;
    }

    public void setLongueDescription(String longueDescription) {
        this.longueDescription = longueDescription;
    }

    public String getMusclesSollicite() {
        return musclesSollicite;
    }

    public void setMusclesSollicite(String musclesSollicite) {
        this.musclesSollicite = musclesSollicite;
    }

    public String getExecution() {
        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public String getLienYoutube() {
        return lienYoutube;
    }

    public void setLienYoutube(String lienYoutube) {
        this.lienYoutube = lienYoutube;
    }

    public Workout(String image, String titre, String courtedescription, String longueDescription, String musclesSollicite, String execution, String lienYoutube ) {
        this.image = image;
        this.titre = titre;
        this.courtedescription = courtedescription;
        this.longueDescription = longueDescription;
        this.musclesSollicite = musclesSollicite;
        this.execution = execution;
        this.lienYoutube = lienYoutube;

        infos.put("image",this.image);
        infos.put("titre",this.titre);
        infos.put("courtedescription",this.courtedescription);
        infos.put("longueDescription",this.longueDescription);
        infos.put("musclesSollicite",this.musclesSollicite);
        infos.put("execution",this.execution);
        infos.put("lienYoutube",this.lienYoutube);

    }

    public Map<String, String> getInfos() {
        return infos;
    }

    public void setInfos(Map<String, String> infos) {
        this.infos = infos;
    }
}
