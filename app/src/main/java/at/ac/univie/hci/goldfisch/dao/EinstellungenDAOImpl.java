package at.ac.univie.hci.goldfisch.dao;

import android.content.Context;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import at.ac.univie.hci.goldfisch.model.AppEinstellungen;
import at.ac.univie.hci.goldfisch.model.Benutzer;

/**
 * Diese Klasse impementiert das EinstellungenDAO und benutzt dabei eine
 * Filesspeicherung(interne App-Speicherung)
 */
public class EinstellungenDAOImpl implements EinstellungenDAO {
    private File folder;
    private File myfile;
    private FileOutputStream outputStream;
    private ObjectOutputStream out;
    private FileInputStream inputStream;
    private ObjectInputStream in;
    private Context context;
    private String filename;

    /**
     * Dieser Konstruktor baut die Verbindung mit dem Filesystem am Handy auf
     * @param context Der aktuelle Kontext, um auf das interne App-Filesystem zuzugreifen
     * @param filename Der Name des Files, das die Gesundheitstipps beinhaltet
     * @throws IOException Falls die Verbindung zum Filesystem fehlschlaegt
     */
    public EinstellungenDAOImpl(Context context, String filename) throws IOException {
        this.folder =  new File(context.getFilesDir().toString());
        this.myfile =  new File(folder.getAbsolutePath() + "/"+filename);
        this.myfile.createNewFile();
        this.context = context;
        this.filename = filename;
    }


    @Override
    public AppEinstellungen getEinstellungen() throws IOException {
        inputStream = new FileInputStream(myfile);
        in = new ObjectInputStream(inputStream);
        AppEinstellungen einstellungen= null;
        try {
            einstellungen = (AppEinstellungen) in.readObject();
        }catch (Exception e){
            System.err.println("EinstellungenDAOImpl:getAppEinstellungen:fehler bei in.readObject!");
        }
        in.close();
        inputStream.close();
        if(einstellungen == null)
            System.out.println("Das File ist null, da getAppEinstellungen liefert null!!");
        return einstellungen;
    }

    @Override
    public void aktualisiereAppEinstellungen(AppEinstellungen ae) throws IOException {
        this.saveEinstellungen(ae);
    }

    /**
     * Speichert ein AppEinstellungen Objekt in einem File
     * @param ae das zu speichernde Objekt
     * @throws IOException Falls ein Fehler beim Fileschreiben passiert
     */
    private void saveEinstellungen(AppEinstellungen ae) throws IOException {
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(ae);
        out.close();
        outputStream.close();
    }




    @Override
    public void fileInitialisieren() throws IOException{ //nur falls nix drin ist, wird ne EOF Exception geworfen, dann eine leereListe reingeben
        try{
            inputStream = new FileInputStream(myfile);
            in = new ObjectInputStream(inputStream);
            inputStream.close();
            in.close();
        }catch (EOFException e) {

            this.saveEinstellungen(new AppEinstellungen());
        }
    }


}
