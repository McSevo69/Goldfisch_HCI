package at.ac.univie.hci.goldfisch.management;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import at.ac.univie.hci.goldfisch.dao.BehaeltnisDAO;
import at.ac.univie.hci.goldfisch.dao.BehaeltnisDAOImpl;
import at.ac.univie.hci.goldfisch.dao.GesundheitsDAO;
import at.ac.univie.hci.goldfisch.dao.GesundheitsDAOImpl;
import at.ac.univie.hci.goldfisch.model.Behaeltnis;
import at.ac.univie.hci.goldfisch.model.Gesundheitstipp;

/**
 * Diese Klasse ist fuer die Behaelterverwaltung zustaendig
 */

public class Behaelterverwaltung {

    private static Behaelterverwaltung instance;

    private BehaeltnisDAO dao;

    private Behaelterverwaltung(Context context){
        try {
            this.dao = new BehaeltnisDAOImpl(context, "behaelter.dat");
        }catch(IOException e){
            System.err.println("management:BehaelterVerwaltung:Konstruktor: fehler beim anlegen des DAO Objektes!!!"+e.getMessage());
        }
    }

    public static Behaelterverwaltung getInstance(Context context){
        if(instance == null) instance = new Behaelterverwaltung(context);
        return instance;
    }

    /**
     * Liefert alle Behaeltnisse, welche gespeichert sind
     * @return Liste aller Behaeltnisse
     */
    public List<Behaeltnis> getBehaeltnisse(){
        try{
            return dao.getBehaeltnisse();
        }catch (IOException e){
            System.err.println("management:Behaelterverwaltung:getBehaelter:"+e.getMessage());
        }
        return  null;
    }

    /**
     * Liefert alle Behaeltnisse, welche gespeichert sind
     * @return Liste aller Behaeltnisse
     */
    public Behaeltnis getBehaeltnisByName(String name){
       for(Behaeltnis b : this.getBehaeltnisse())
           if(b.getName().equals(name)) return b;
        return  null;
    }
}
