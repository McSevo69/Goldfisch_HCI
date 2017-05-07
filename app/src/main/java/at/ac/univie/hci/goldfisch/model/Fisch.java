package at.ac.univie.hci.goldfisch.model;

import android.media.Image;

import java.io.Serializable;
import java.util.UUID;

/**
 *Diese Klasse umfasst einen Fisch, welchen der User erwerben kann
 */
public class Fisch  implements Serializable {

    private UUID id;
    private String name;
    private Image bild;
    private boolean imGlas;
    private String kategorie;


    public Fisch(String name, Image bild, boolean imGlas){
        this.id = UUID.randomUUID();
        this.name = name;
        this.bild = bild;
        this.imGlas = imGlas;
    }


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Image getBild() {
        return bild;
    }
    public void setBild(Image bild) {
        this.bild = bild;
    }

    public boolean isImGlas() {
        return imGlas;
    }
    public void setImGlas(boolean imGlas) {
        this.imGlas = imGlas;
    }

    public String getKategorie() {
        return kategorie;
    }
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

}
