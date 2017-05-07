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

public class Einstellungenverwaltung {

    private static Einstellungenverwaltung instance;
    private EinstellungenDAO dao;

    private Einstellungenverwaltung(Context context){
        try {
            this.dao = new EinstellungenDAOImpl(context, "einstellungen.dat");
        }catch(IOException e){
            System.err.println("management:Einstellungenverwaltung:Konstruktor: fehler beim anlegen des DAO Objektes!!!");
        }
    }

    public static Einstellungenverwaltung getInstance(Context context){
        if(instance == null)
            instance = new Einstellungenverwaltung(context);
        return instance;
    }

    /**
     * rein zum ausgeben der aktuellen Einstellungen
     */
    public void printEinstellungen(){
        System.out.println("Einstellungenverwaltung:printEinstellungen:Einstellungen drinnen: \n" + this.getEinstellungen());
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

    public void erinnerungsintervallAendern(int hours){
        AppEinstellungen ae = this.getEinstellungen();
        ae.setErinnerungsintervall(hours);
        saveEinstellungen(ae);
    }

}
