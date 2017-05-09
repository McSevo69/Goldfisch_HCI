package at.ac.univie.hci.goldfisch.management;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import at.ac.univie.hci.goldfisch.dao.BehaeltnisDAO;
import at.ac.univie.hci.goldfisch.dao.BehaeltnisDAOImpl;
import at.ac.univie.hci.goldfisch.model.Behaeltnis;

/**
 * Diese Klasse ist fuer die Behaelterverwaltung zustaendig
 */

public class Behaelterverwaltung implements Verwaltung{

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
            e.printStackTrace();
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


    public void neuesBehaeltnisAnlegen(String name, double fuellmenge, String inhalt, double faktor){
        List<Behaeltnis> alleBehaeltnisse =  this.getBehaeltnisse();
        for(Behaeltnis b : alleBehaeltnisse)
            b.setAktiviert(false);
        Behaeltnis b = new Behaeltnis(name, true, fuellmenge, inhalt, faktor);
        alleBehaeltnisse.add(b);
        try {
            dao.saveBehaeltnisse(alleBehaeltnisse);
        }catch (IOException e){
            System.err.println("Behaelterverwaltung:neuesBehaeltnisAnlegen:fehler beim saven der neuen Liste:"+e.getMessage());
        }
    }

    public void setBehaeltnisAktiv(String name){
        for(Behaeltnis b : this.getBehaeltnisse()){
            if(b.getName().equals(name))
                b.setAktiviert(true);
            else
                b.setAktiviert(false);
        }
    }

    @Override
    public void printAll(){
        System.out.println("Alle Behaelter: \n");
        for(Behaeltnis b : this.getBehaeltnisse())
            System.out.println(" "+b);
    }

    @Override
    public void initialisiere(){
        try{
            dao.fileInitialisieren();
        }catch (IOException e){
            System.out.println("Behaelterverwaltung:initialisiere:Error");
            e.printStackTrace();
        }
    }

}
