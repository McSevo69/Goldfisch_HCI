package at.ac.univie.hci.goldfisch.model;

import android.media.Image;

import java.io.Serializable;
import java.util.UUID;

/**
 * Diese Klasse stellt die Behältnisse dar, zwischen die der Nutzer wechseln kann, nur 1 ist immer aktiviert
 * Es handelt sich um die Behaeltnisse, die man trinken kann(etwa eine 500ml Tasse)
 * Diese Klasse stellt nicht den Teich oder das Fischglas dar, wo fertige Fische aufbewahrt werden dar.
 */
public class Behaeltnis implements Serializable{
    private UUID id;
    private Image bild;
    boolean aktiviert;
    private double fuellmenge;


    public Behaeltnis(Image bild, boolean aktiviert, double fuellmenge){
        this.id = UUID.randomUUID();
        this.bild = bild;
        this.aktiviert = aktiviert;
        this.fuellmenge = fuellmenge;

    }


    public double getFuellmenge() {
        return fuellmenge;
    }
    public void setFuellmenge(double fuellmenge) {
        this.fuellmenge = fuellmenge;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public Image getBild() {
        return bild;
    }
    public void setBild(Image bild) {
        this.bild = bild;
    }

    public boolean isAktiviert() {
        return aktiviert;
    }
    public void setAktiviert(boolean aktiviert) {
        this.aktiviert = aktiviert;
    }
}
