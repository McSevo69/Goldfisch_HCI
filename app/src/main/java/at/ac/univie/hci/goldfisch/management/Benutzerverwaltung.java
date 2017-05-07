package at.ac.univie.hci.goldfisch.management;

import android.content.Context;

import java.io.IOException;

import at.ac.univie.hci.goldfisch.dao.BenutzerDAO;
import at.ac.univie.hci.goldfisch.dao.BenutzerDAOImpl;
import at.ac.univie.hci.goldfisch.dao.GesundheitsDAOImpl;
import at.ac.univie.hci.goldfisch.model.Benutzer;

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

    public void neuenBenutzerTestAnlegen() throws IOException {
        dao.saveBenutzer(new Benutzer("Gerhard", "Schmidt", "gerhard@schmidt.com", 185, 90, null, 'm'));
    }

}
