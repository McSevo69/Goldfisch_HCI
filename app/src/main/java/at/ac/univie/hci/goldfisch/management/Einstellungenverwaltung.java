package at.ac.univie.hci.goldfisch.management;

import android.content.Context;

import java.io.IOException;

import at.ac.univie.hci.goldfisch.dao.EinstellungenDAO;
import at.ac.univie.hci.goldfisch.dao.EinstellungenDAOImpl;
import at.ac.univie.hci.goldfisch.model.AppEinstellungen;

/**
 * Diese Klasse stellt alle wichtigen Einstellungen zur Bearbeitung und Ausgeben der
 * globalen Einstellungen
 */

public class Einstellungenverwaltung implements Verwaltung{

    private static Einstellungenverwaltung instance;
    private EinstellungenDAO dao;

    /**
     * Singleton
     * @param context braucht man zum aufbauen der fileverbindung
     */
    private Einstellungenverwaltung(Context context){
        try {
            this.dao = new EinstellungenDAOImpl(context, "einstellungen.dat");
        }catch(IOException e){
            System.err.println("management:Einstellungenverwaltung:Konstruktor: fehler beim anlegen des DAO Objektes!!!");
        }
    }

    /**
     * Singleton
     * @param context braucht man zum aufbauen der fileverbindung
     */
    public static Einstellungenverwaltung getInstance(Context context){
        if(instance == null)
            instance = new Einstellungenverwaltung(context);
        return instance;
    }

    @Override
    public void printAll(){
        System.out.println("Einstellungen: \n " + this.getEinstellungen());
    }

    @Override
    public void initialisiere(){
        try{
            dao.fileInitialisieren();
        }catch (IOException e){
            System.out.println("Einstellungenverwaltung:initialisieren:Error");
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode holt die globalen Einstellungen der App
     * @return Die derzeit gespeicherten Einstellungen
     */
    public AppEinstellungen getEinstellungen(){
        try{
            return dao.getEinstellungen();
        }catch (IOException e){
            System.err.println("Einstellungenverwaltung:getEinstellungen:Fehler beim dao.getEinstellungen holen: "+e.getMessage());
            return null;
        }
    }

    /**
     * Diese Methode speichert die uebergebenen Einstellungen
     * @param ae die neuen zu speichernden Einstellungen
     */
    public void saveEinstellungen(AppEinstellungen ae){
        try {
            dao.aktualisiereAppEinstellungen(ae);
        }catch(IOException e){
            System.err.println("Einstellungenverwaltung.saveEinstellungen:Fehler: "+e.getMessage());
        }
    }

    /**
     * Diese Methode aktiviert Tipps beim Systemstart
     */
    public void aktiviereTipps(){
        AppEinstellungen ae = this.getEinstellungen();
        ae.setWillTipps(true);
        saveEinstellungen(ae);
    }

    /**
     * Diese Methode deaktiviert Tipps beim Systemstart
     */
    public void deaktiviereTipps(){
        AppEinstellungen ae = this.getEinstellungen();
        ae.setWillTipps(false);
        saveEinstellungen(ae);
    }

    /**
     * Diese Methode aktiviert Erinnerungen
     */
    public void aktiviereErinnerungen(){
        AppEinstellungen ae = this.getEinstellungen();
        ae.setWillErinnerungen(true);
        saveEinstellungen(ae);
    }

    /**
     * Diese Methode deaktiviert Erinnerungen
     */
    public void deaktiviereErinnerungen(){
        AppEinstellungen ae = this.getEinstellungen();
        ae.setWillErinnerungen(false);
        saveEinstellungen(ae);
    }

    /**
     * Damit kann man das erinnerungsintervall aendern
     * @param hours das in stunden angegebene intervall
     */
    public void erinnerungsintervallAendern(int hours){
        AppEinstellungen ae = this.getEinstellungen();
        ae.setErinnerungsintervall(hours);
        saveEinstellungen(ae);
    }


}
