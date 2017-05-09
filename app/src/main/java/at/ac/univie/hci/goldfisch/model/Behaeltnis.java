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
    private String inhalt; //zb Wasser, Kaffee,...
    private double faktor; //zB Alkohol hat faktor von 0.8, liefert also nicht so viel Wasser wie reines Wasser

    public Behaeltnis(String name,boolean aktiviert, double fuellmenge, String inhalt, double faktor){
        this.id = UUID.randomUUID();
        this.name = name;
        this.aktiviert = aktiviert;
        this.fuellmenge = fuellmenge;
        this.inhalt = inhalt;
        this.faktor = faktor;
    }

    /**
     * gibt die eigentlich anzurechnende Menge an
     * @return das was eigentlich effektiv vom Koerper als Wasser aufgenommen wird
     */
    public double getEffektiveTrinkmenge(){return (int)(fuellmenge*faktor);}//int, damit gerundet auf ganzzahl


    public double getFuellmenge() {
        return fuellmenge;
    }
    public void setFuellmenge(double fuellmenge) {
        this.fuellmenge = fuellmenge;
    }

    public String getInhalt() {
        return inhalt;
    }
    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public double getFaktor() {
        return faktor;
    }
    public void setFaktor(double faktor) {
        this.faktor = faktor;
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
                ", inhalt=" + inhalt+
                ", faktor=" + faktor+
                ", effektiv=" + getEffektiveTrinkmenge() +
                '}';
    }
}
