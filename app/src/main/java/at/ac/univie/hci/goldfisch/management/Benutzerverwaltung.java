package at.ac.univie.hci.goldfisch.management;

import android.content.Context;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import at.ac.univie.hci.goldfisch.dao.BenutzerDAO;
import at.ac.univie.hci.goldfisch.dao.BenutzerDAOImpl;
import at.ac.univie.hci.goldfisch.model.Behaeltnis;
import at.ac.univie.hci.goldfisch.model.Benutzer;
import at.ac.univie.hci.goldfisch.model.Fisch;
import at.ac.univie.hci.goldfisch.model.Glas;
import at.ac.univie.hci.goldfisch.model.Status;

/**
 * Diese Klasse stellt alle wichtigen Methoden zur Verfuegung, um Benutzerspezifische Aktionen
 * durchfuehren zu koennen
 */

public class Benutzerverwaltung implements Verwaltung {

    private static Benutzerverwaltung instance;
    private BenutzerDAO dao;

    private Benutzerverwaltung(Context context){
        try {
            this.dao = new BenutzerDAOImpl(context, "benutzer.dat");
        }catch(IOException e){
            System.err.println("management:Benutzerverwaltung:Konstruktor: fehler beim anlegen des DAO Objektes!!!");
        }
    }

    public static Benutzerverwaltung getInstance(Context context){
        if(instance == null)
            instance = new Benutzerverwaltung(context);
        return instance;
    }

    public Benutzer getBenutzer(){
        try {
            return dao.getBenutzer();
        }catch (IOException e) {
            System.err.println("Benutzerverwaltung.getBenutzer: " + e.getMessage());
            return null;
        }
    }

    /**
     * Diese Methode aktualisiert das Benutzerobjekt, welches im Benutzer.dat file gespeichert ist
     * @param b das neue Objekt
     */
    public void aktualisiereBenutzer(Benutzer b){
        try {
            this.dao.saveBenutzer(b);
        } catch (IOException e) {
            System.err.println("Benutzerverwaltung:aktualisiereBenutzer:fehler beim speichern des Benutzers:"+e.getMessage());
        }
    }


    /**
     * Methode zum Aendern der Groesse
     * @param neueGroesse Groesse in cm
     */
    public void groesseAendern(double neueGroesse){
        Benutzer b = this.getBenutzer();
        if(b == null){ System.err.println("Benutzerverwaltung:groesseAendern:ACHTUNG: KEIN BENUTZER!!"); return; }
        if(neueGroesse<=0 || neueGroesse>300) return;
        b.setGroesse(neueGroesse);
        this.aktualisiereBenutzer(b);
    }

    /**
     * Methode zum Aendern des Gewichts
     * @param neuesGewicht Gewicht in kg
     */
    public void gewichtAendern(double neuesGewicht){
        Benutzer b = this.getBenutzer();
        if(b == null){ System.err.println("Benutzerverwaltung:gewichtAendern:ACHTUNG: KEIN BENUTZER!!"); return; }
        if(neuesGewicht<=0 || neuesGewicht>300) return;
        b.setGewicht(neuesGewicht);
        this.aktualisiereBenutzer(b);
    }


    /**
     * Diese Methode fuegt ein neues Glas hinzu und setzt dieses automatisch als aktuell
     * @param neuesGlas das neue glas
     */
    public void glasHinzufuegen(Glas neuesGlas){
        Benutzer b = this.getBenutzer();
        if(b == null){ System.err.println("Benutzerverwaltung:GlasHinzufuegen:ACHTUNG: KEIN BENUTZER!!"); return; }
        List<Glas> alleGlaeser = b.getGlaeser();
        for(Glas g : alleGlaeser) g.setAktiviert(false); //alle vorhandenen als nicht aktiviert setzen
        neuesGlas.setAktiviert(true);
        alleGlaeser.add(neuesGlas);
        b.setGlaeser(alleGlaeser);
        this.aktualisiereBenutzer(b);
    }

    /**
     * Diese Methode fuegt einen neuen Fisch hinzu und setzt dieses automatisch ins Glas
     * @param neuerFisch der neue Fisch
     */
    public void fischHinzufuegen(Fisch neuerFisch){
        Benutzer b = getBenutzer();
        if(b == null){ System.err.println("Benutzerverwaltung:FischHinzufuegen:ACHTUNG: KEIN BENUTZER!!"); return; }
        List<Fisch> alleFische = b.getFische();
        for(Fisch f:alleFische) f.setImGlas(false);
        neuerFisch.setImGlas(true);
        alleFische.add(neuerFisch);
        b.setFische(alleFische);
        this.aktualisiereBenutzer(b);
    }


    /**
     * Aendert die StandardAktivitaet des Benutzers
     * a..aktiv
     */
    public void aktivitaetAendernAktiv(){
        aktivitaetAendern('a');
    }
    /**
     * Aendert die StandardAktivitaet des Benutzers
     * n..normal
     */
    public void aktivitaetAendernNormal(){
        this.aktivitaetAendern('n');
    }
    /**
     * Aendert die StandardAktivitaet des Benutzers
     * s..sitzend
     */
    public void aktivitaetAendernSitzen(){
        this.aktivitaetAendern('s');
    }

    /**
     * Diese Methode speichert die aenderung
     * @param aktivitaet
     */
    private void aktivitaetAendern(char aktivitaet){
        Benutzer b = this.getBenutzer();
        if(b == null){ System.err.println("Benutzerverwaltung:groesseAendern:ACHTUNG: KEIN BENUTZER!!"); return; }
        b.setAktivitaet(aktivitaet);
        this.aktualisiereBenutzer(b);
    }


    private void neuenStatusAnlegen(double tagessollmenge){
        Benutzer b = this.getBenutzer();
        if(b == null){ System.err.println("Benutzerverwaltung:groesseAendern:ACHTUNG: KEIN BENUTZER!!"); return; }
        List<Status> alleStati = b.getStati();
        alleStati.add(new Status(tagessollmenge));
        b.setStati(alleStati);
        this.aktualisiereBenutzer(b);
    }

    private Status getStatus(int tag, int monat, int jahr){
        Benutzer b = this.getBenutzer();
        Calendar c;
        monat -=1; //weil Calendar beim Monat bei 0 anfaengt
        if(b == null){ System.err.println("Benutzerverwaltung:groesseAendern:ACHTUNG: KEIN BENUTZER!!"); return null; }
        List<Status> alleStati = b.getStati();
        for(Status s : alleStati){
            c=s.getDatum();
            int m = c.get(Calendar.MONTH);
            if(c.get(Calendar.DAY_OF_MONTH)==tag && m==monat && c.get(Calendar.YEAR)==jahr)  //
                return s;
        }
        return null;
    }

    private Status getHeutigenStatus(){
        Calendar c = new GregorianCalendar();
        return this.getStatus(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
    }

    private void tagesmengeErhoehen(double wert){
        Benutzer b = this.getBenutzer();
        if(b == null){ System.err.println("Benutzerverwaltung:groesseAendern:ACHTUNG: KEIN BENUTZER!!"); return; }
        List<Status> alleStati = b.getStati();
    }


    public Status getheutigenStatus(){
        Benutzer b = this.getBenutzer();
        System.out.println("Benutzerverwaltung: getheutigenStatus: gestartet"+b);
        List<Status> alleStati = this.getBenutzer().getStati();
        for(Status s : alleStati)
            if(this.testIfSameDay(s.getDatum(),new GregorianCalendar()))
                return s;
        return null;
    }

    public void getraenkTrinken(Behaeltnis b){
        boolean statusHeuteVorhanden = false;
        Benutzer benutzer = this.getBenutzer();
        List<Status> alleStati = benutzer.getStati();
        Calendar heute = new GregorianCalendar();

        for(Status s : alleStati){
            if(testIfSameDay(s.getDatum(),heute)){
                double tagesist = s.getTagesIstMenge();
                s.setTagesIstMenge(tagesist+b.getEffektiveTrinkmenge());
                statusHeuteVorhanden = true;
            }
        }
        if(!statusHeuteVorhanden){ //falls aktueller status noch nicht vorhanden war
            double kiloFaktor = (benutzer.getAktivitaet()=='n') ? 0.04031 : (benutzer.getAktivitaet()=='a') ? 0.04535 : 0.03359;
            double tagessollmenge = benutzer.getGewicht()*kiloFaktor;
            alleStati.add(new Status(tagessollmenge, b.getEffektiveTrinkmenge()));
        }

        benutzer.setStati(alleStati);
        this.aktualisiereBenutzer(benutzer);

    }

    /**
     * Prüft ob 2 Daten gleich sind(aber nur bezogen auf den Tag)
     * @param datum1
     * @param datum2
     * @return
     */
    private boolean testIfSameDay(Calendar datum1, Calendar datum2){
        int jahr1 = datum1.get(Calendar.YEAR);
        int jahr2 = datum2.get(Calendar.YEAR);;

        int monat1 = datum1.get(Calendar.MONTH);
        int monat2 = datum2.get(Calendar.MONTH);;

        int tag1 = datum1.get(Calendar.DAY_OF_MONTH);
        int tag2 = datum2.get(Calendar.DAY_OF_MONTH);

        if(jahr1==jahr2 && monat1==monat2 && tag1==tag2) return true;
        return false;

    }


    @Override
    public void printAll(){
        System.out.println("Benutzer: \n "+this.getBenutzer());
    }

    @Override
    public void initialisiere(){

        try{
            dao.fileInitialisieren();
        }catch (IOException e){
            System.out.println("Benutzerverwaltung:initialisiere:Error");
            e.printStackTrace();
        }
    }

}
