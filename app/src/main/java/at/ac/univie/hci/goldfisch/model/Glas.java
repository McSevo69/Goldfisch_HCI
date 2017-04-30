package at.ac.univie.hci.goldfisch.model;

import android.media.Image;

import java.util.UUID;

/**
 * Diese Klasse stellt das eigentliche Fischglas dar, welches am Hauptbildschirm verfuegbar ist
 */

public class Glas {
    private UUID id;
    private Image bild;
    private boolean aktiviert;

    public Glas(Image bild, boolean aktiviert, double fuellmenge){
        this.id = UUID.randomUUID();
        this.bild = bild;
        this.aktiviert = aktiviert;
    }
}
