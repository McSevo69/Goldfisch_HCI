package at.ac.univie.hci.goldfisch.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Diese Klasse soll die Tipps darstellen, welche dem User regelmaessig angezeigt werden
 * Man speichert nicht nur Strings, sondern die Tipps und auch die dazugehoerigen Ueberschriften
 */
public class Gesundheitstipp implements Serializable {

    private UUID id;
    private String tipp;
    private String ueberschrift;


    @Override
    public String toString() {
        return "Gesundheitstipp{" +
                "id=" + id +
                ", tipp='" + tipp + '\'' +
                ", ueberschrift='" + ueberschrift + '\'' +
                '}';
    }

    public Gesundheitstipp(String heading, String tipp){
        this.tipp = tipp;
        this.ueberschrift = heading;
        this.id = UUID.randomUUID();

    }


    public void setTipp(String tipp) {
        this.tipp = tipp;
    }

    public void setUeberschrift(String heading) {
        this.ueberschrift = heading;
    }

    public String getTipp() {

        return tipp;
    }

    public String getUeberschrift() {
        return ueberschrift;
    }

    public UUID getId() {
        return id;
    }

}
