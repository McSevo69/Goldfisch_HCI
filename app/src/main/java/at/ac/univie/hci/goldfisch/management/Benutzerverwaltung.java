package at.ac.univie.hci.goldfisch.management;

/**
 * Created by Gerhard on 02.05.2017.
 */

public class Benutzerverwaltung {

    private static Benutzerverwaltung instance;

    private Benutzerverwaltung(){

    }

    public Benutzerverwaltung getInstance(){
        if(instance == null)
            instance = new Benutzerverwaltung();
        return instance;
    }



}
