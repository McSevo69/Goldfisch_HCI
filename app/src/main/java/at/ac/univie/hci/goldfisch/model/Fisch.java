package at.ac.univie.hci.goldfisch.model;

import android.media.Image;

import java.util.UUID;



/**
 * Created by Gerhard on 30.04.2017.
 */

public class Fisch {

    UUID id;
    String name;
    Image bild;
    boolean imGlas;

    public Fisch(String name, Image bild, boolean imGlas){
        this.id = UUID.randomUUID();
        this.name = name;
        this.bild = bild;
        this.imGlas = imGlas;
    }


}
