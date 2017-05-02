package at.ac.univie.hci.goldfisch.management;

import android.content.Context;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import at.ac.univie.hci.goldfisch.dao.GesundheitsDAO;
import at.ac.univie.hci.goldfisch.dao.GesundheitsDAOImpl;
import at.ac.univie.hci.goldfisch.model.Gesundheitstipp;

/**
 * Created by Gerhard on 02.05.2017.
 */

public class GesundheitstippsVerwaltung {

    private static GesundheitstippsVerwaltung instance;

    private GesundheitsDAO dao;

    private GesundheitstippsVerwaltung(Context context){
        try {
            this.dao = new GesundheitsDAOImpl(context, "gesundheitstipps.dat");
        }catch(IOException e){
            System.err.println("management:GesundheitstippsVerwaltung:Konstruktor: fehler beim anlegen des DAO Objektes!!!");
        }
    }

    public GesundheitstippsVerwaltung getInstance(Context context){
        if(instance == null) instance = new GesundheitstippsVerwaltung(context);
        return instance;
    }

    /**
     * Liefert einen RandomTipp
     * @return irgendein Tipp der gespeichert ist
     */
    public Gesundheitstipp getRandomTipp() {
        int lowerBoundInclusive=1;
        int upperBoundInclusive;
        int random;

        try {
            List<Gesundheitstipp> alleTipps = dao.getTipps();
            upperBoundInclusive = alleTipps.size()-1;
            random = new Random().nextInt(upperBoundInclusive)+lowerBoundInclusive;
            return alleTipps.get(random);
        }catch (IOException e){
            System.err.println("management:GesundheitstippsVerwaltung:getRandomTipp");
        }
        return null;
    }

    /**
     * Liefert alle Gesundheitstipps, welche gespeichert sind
     * @return Liste aller Gesundheitstipps
     */
    public List<Gesundheitstipp> getGesundheitstipps(){
        try{
            return dao.getTipps();
        }catch (IOException e){
            System.err.println("management:GesundheitstippsVerwaltung:getGesundheitstipps");
        }
        return  null;
    }
}
