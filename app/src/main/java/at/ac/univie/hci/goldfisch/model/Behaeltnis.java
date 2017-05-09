package at.ac.univie.hci.goldfisch.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Diese Klasse stellt die Behältnisse dar, zwischen die der Nutzer wechseln kann, nur 1 ist immer aktiviert
 * Es handelt sich um die Behaeltnisse, die man trinken kann(etwa eine 500ml Tasse)
 * Diese Klasse stellt nicht den Teich oder das Fischglas dar, wo fertige Fische aufbewahrt werden dar.
 */
public class Behaeltnis implements Serializable{
    private UUID id;
    private String name;
    boolean aktiviert;
    private double fuellmenge;


    public Behaeltnis(String name,boolean aktiviert, double fuellmenge){
        this.id = UUID.randomUUID();
        this.name = name;
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


    public boolean isAktiviert() {
        return aktiviert;
    }
    public void setAktiviert(boolean aktiviert) {
        this.aktiviert = aktiviert;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Behaeltnis{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aktiviert=" + aktiviert +
                ", fuellmenge=" + fuellmenge +
                '}';
    }
}
