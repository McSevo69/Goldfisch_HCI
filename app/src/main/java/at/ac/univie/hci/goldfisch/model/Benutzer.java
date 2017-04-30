package at.ac.univie.hci.goldfisch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

/**
 * Diese Klasse soll alle wichtigen Informationen des Users haben.
 *
 */
public class Benutzer implements Serializable{
    private UUID id;
    private String vorname;
    private String nachname;
    private String email;
    private double groesse;
    private double gewicht;
    private Calendar gebDatum;
    private char geschlecht;
    private Calendar nutzungsbeginn;

    private List<Fisch> fische;
    private List<Glas> glaeser;
    private List<Status> stati;
    private Teich teich;

    public Benutzer(String vorname, String nachname, String email, double groesse, double gewicht, Calendar gebDatum, char geschlecht) {
        this.id = UUID.randomUUID();
        this.nutzungsbeginn = new GregorianCalendar();
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.groesse = groesse;
        this.gewicht = gewicht;
        this.gebDatum = gebDatum;
        this.geschlecht = geschlecht;

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

    public String getNachname() {
        return nachname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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

    public Calendar getGebDatum() {
        return gebDatum;
    }
    public void setGebDatum(Calendar gebDatum) {
        this.gebDatum = gebDatum;
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
}