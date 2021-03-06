package at.ac.univie.hci.goldfisch.management;

import android.content.Context;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import at.ac.univie.hci.goldfisch.dao.GesundheitsDAO;
import at.ac.univie.hci.goldfisch.dao.GesundheitsDAOImpl;
import at.ac.univie.hci.goldfisch.model.Gesundheitstipp;

/**
 *
 */

public class GesundheitstippsVerwaltung implements Verwaltung{

    private static GesundheitstippsVerwaltung instance;

    private GesundheitsDAO dao;

    /**
     * Singleton
     * @param context braucht man zum aufbauen der fileverbindung
     */
    private GesundheitstippsVerwaltung(Context context){
        try {
            this.dao = new GesundheitsDAOImpl(context, "gesundheitstipp.dat");
        }catch(IOException e){
            System.err.println("management:GesundheitstippsVerwaltung:Konstruktor: fehler beim anlegen des DAO Objektes!!!");
        }
    }

    /**
     * Singleton
     * @param context braucht man zum aufbauen der fileverbindung
     */
    public static GesundheitstippsVerwaltung getInstance(Context context){
        if(instance == null) instance = new GesundheitstippsVerwaltung(context);
        return instance;
    }

    /**
     * Liefert einen RandomTipp
     * @return irgendein Tipp der gespeichert ist
     */
    public Gesundheitstipp getRandomTipp() {
        int random;

        try {
            List<Gesundheitstipp> alleTipps = dao.getTipps();
            random = new Random().nextInt(alleTipps.size());
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
            System.err.println("management:GesundheitstippsVerwaltung:getGesundheitstipps: "+e.getMessage());
            e.printStackTrace();
        }
        return  null;
    }

    public void neuenTippAnlegen(String ueberschrift, String tipp){
        try{
            dao.saveTipp(new Gesundheitstipp(ueberschrift,tipp));
        }catch (IOException e){
            System.err.println("management:GesundheitstippsVerwaltung:neuenTippAnlegen: "+e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void printAll(){
        System.out.println("Alle Gesundheitstipps: \n");
        for(Gesundheitstipp b : this.getGesundheitstipps())
            System.out.println(" "+b);
    }

    @Override
    public void initialisiere(){
        try{
            dao.fileInitialisieren();
        }catch (IOException e){
            System.out.println("Behaelterverwaltung:initialisieren:Error");
            e.printStackTrace();
        }
    }
}
