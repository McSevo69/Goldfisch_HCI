package at.ac.univie.hci.goldfisch.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Die einzelnen Werte, wie sich der User verhaelt werden in einer Status Klasse zusammengefasst
 * Fuer jeden einzelnen Tag wird ein Statusobjekt dann instantiiert.
 */
public class Status implements Serializable {
    private UUID id;
    double tagesSollMenge;
    double tagesIstMenge;
    Calendar datum;

    public Status(double tagesSollMenge){
        this.tagesSollMenge = tagesSollMenge;
        this.tagesIstMenge = 0;
        this.datum = new GregorianCalendar(); //heutiges Datum einstellen
    }

    public Status(double tagesSollMenge, double tagesIstMenge){
        this.tagesSollMenge = tagesSollMenge;
        this.tagesIstMenge = tagesIstMenge;
        this.datum = new GregorianCalendar(); //heutiges Datum einstellen
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public double getTagesSollMenge() {
        return tagesSollMenge;
    }
    public void setTagesSollMenge(double tagesSollMenge) {
        this.tagesSollMenge = tagesSollMenge;
    }

    public double getTagesIstMenge() {
        return tagesIstMenge;
    }
    public void setTagesIstMenge(double tagesIstMenge) {
        this.tagesIstMenge = tagesIstMenge;
    }

    public Calendar getDatum() {
        return datum;
    }
    public void setDatum(Calendar datum) {
        this.datum = datum;
    }
}
