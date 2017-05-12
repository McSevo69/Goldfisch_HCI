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
import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.goldfisch.model.Benutzer;
import at.ac.univie.hci.goldfisch.model.Gesundheitstipp;
import at.ac.univie.hci.goldfisch.model.Glas;
import at.ac.univie.hci.goldfisch.model.Teich;

/**
 * Diese Klasse impementiert das BenutzerDAO und benutzt dabei eine
 * Filesspeicherung(interne App-Speicherung)
 */
public class BenutzerDAOImpl implements BenutzerDAO {
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
    public BenutzerDAOImpl(Context context, String filename) throws IOException {
        this.folder =  new File(context.getFilesDir().toString());
        this.myfile =  new File(folder.getAbsolutePath() + "/"+filename);
        this.myfile.createNewFile();
        this.context = context;
        this.filename = filename;
    }

    @Override
    public Benutzer getBenutzer() throws IOException {
        inputStream = context.openFileInput(filename);
        in = new ObjectInputStream(inputStream);
        Benutzer benutzerImFile = null;
        try {
            benutzerImFile = (Benutzer) in.readObject();
        }catch (Exception e){
            System.err.println("BenutzerDAOImpl:getBenutzer:fehler bei in.readObject!");
            e.printStackTrace();
        }
        in.close();
        inputStream.close();
        if(benutzerImFile == null)
            System.out.println("Das File ist null, da getBenutzer liefert null!!");
        return benutzerImFile;
    }

    @Override
    public void saveBenutzer(Benutzer b) throws IOException {
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(b);
        out.close();
        outputStream.close();
    }

    @Override
    public Glas getGlas() throws IOException {
        Benutzer b = this.getBenutzer();
        for(Glas g : b.getGlaeser()){
            if(g.isAktiviert()) return g;
        }
        return null;
    }

    @Override
    public Teich getTeich() throws IOException {
        Benutzer b = this.getBenutzer();
        return b.getTeich();
    }



    @Override
    public void fileInitialisieren() throws IOException {
        try {

            if (this.getBenutzer() == null) {
                System.out.println("Starte mit initialisierung des " + filename + "!");
                outputStream = new FileOutputStream(myfile);
                out = new ObjectOutputStream(outputStream);
                Benutzer b = new Benutzer("keinName", 1, 1, 'm', 'n');
                out.writeObject(b);
                out.close();
                outputStream.close();
            } else
                System.out.println(filename + " war schon initialisiert!");
        } catch (EOFException e) {
            System.out.println("Starte mit initialisierung des " + filename + "!");
            outputStream = new FileOutputStream(myfile);
            out = new ObjectOutputStream(outputStream);
            Benutzer b = new Benutzer("keinName", 185, 75, 'm', 'n');
            out.writeObject(b);
            out.close();
            outputStream.close();
        }
    }
}
