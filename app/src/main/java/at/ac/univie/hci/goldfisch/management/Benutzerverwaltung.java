package at.ac.univie.hci.goldfisch.management;

import android.content.Context;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import at.ac.univie.hci.goldfisch.dao.BenutzerDAO;
import at.ac.univie.hci.goldfisch.dao.BenutzerDAOImpl;
import at.ac.univie.hci.goldfisch.model.Benutzer;
import at.ac.univie.hci.goldfisch.model.Fisch;
import at.ac.univie.hci.goldfisch.model.Glas;
import at.ac.univie.hci.goldfisch.model.Status;

/**
 * Created by Gerhard on 02.05.2017.
 */

public class Benutzerverwaltung {

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
        }catch (IOException e){
            System.err.println("Benutzerverwaltung.getBenutzer: "+e.getMessage());
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
     * Methode zum Aendern des Geburtsdatums
     * @param tag
     * @param monat
     * @param jahr
     */
    public void geburtsdatumAendern(int tag, int monat, int jahr){
        Benutzer b = this.getBenutzer();
        if(b == null){ System.err.println("Benutzerverwaltung:geburtsdatumAendern:ACHTUNG: KEIN BENUTZER!!"); return; }
        b.setGebDatum(new GregorianCalendar(jahr,monat-1,tag)); //-1, weil monat bei 0 anfaengt zu zaehlen(Jaenner = 0)
        this.aktualisiereBenutzer(b);
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

    public void neuenBenutzerTestAnlegen() throws IOException {
        dao.saveBenutzer(new Benutzer("Gerhard", "Schmidt", "gerhard@schmidt.com", 185, 90, null, 'm','n'));
    }

}
