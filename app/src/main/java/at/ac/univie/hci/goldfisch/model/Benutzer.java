package at.ac.univie.hci.goldfisch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

/**
 * Diese Klasse soll alle wichtigen Informationen des Users haben.
 * neu
 */
public class Benutzer implements Serializable{
    private UUID id;
    private String vorname;
    private double groesse;
    private double gewicht;
    private char geschlecht;
    private Calendar nutzungsbeginn;
    private char aktivitaet;


    private List<Fisch> fische;
    private List<Glas> glaeser;
    private List<Status> stati;
    private Teich teich;

    public List<Fisch> getFische() {
        return fische;
    }

    public void setFische(List<Fisch> fische) {
        this.fische = fische;
    }

    public List<Glas> getGlaeser() {
        return glaeser;
    }

    public void setGlaeser(List<Glas> glaeser) {
        this.glaeser = glaeser;
    }

    public List<Status> getStati() {
        return stati;
    }

    public void setStati(List<Status> stati) {
        this.stati = stati;
    }

    public Teich getTeich() {
        return teich;
    }

    public void setTeich(Teich teich) {
        this.teich = teich;
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", groesse=" + groesse +
                ", gewicht=" + gewicht +
                ", geschlecht=" + geschlecht +
                ", nutzungsbeginn=" + nutzungsbeginn +
                ", aktivitaet=" + aktivitaet +
                ", fische=" + fische +
                ", glaeser=" + glaeser +
                ", stati=" + stati +
                ", teich=" + teich +
                '}';
    }

    public Benutzer(String vorname, double groesse, double gewicht, char geschlecht, char aktivitaet) {
        this.id = UUID.randomUUID();
        this.nutzungsbeginn = new GregorianCalendar();
        this.vorname = vorname;
        this.groesse = groesse;
        this.gewicht = gewicht;
        this.geschlecht = geschlecht;
        this.aktivitaet = aktivitaet;

        this.fische = new ArrayList<Fisch>();
        this.glaeser = new ArrayList<Glas>();
        this.stati = new ArrayList<Status>();
        this.teich = new Teich();
    }


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public double getGroesse() {
        return groesse;
    }
    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }

    public double getGewicht() {
        return gewicht;
    }
    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public char getGeschlecht() {
        return geschlecht;
    }
    public void setGeschlecht(char geschlecht) {
        this.geschlecht = geschlecht;
    }

    public Calendar getNutzungsbeginn() {
        return nutzungsbeginn;
    }
    public void setNutzungsbeginn(Calendar nutzungsbeginn) {
        this.nutzungsbeginn = nutzungsbeginn;
    }

    public char getAktivitaet() {
        return aktivitaet;
    }
    public void setAktivitaet(char aktivitaet) {
        this.aktivitaet = aktivitaet;
    }
}