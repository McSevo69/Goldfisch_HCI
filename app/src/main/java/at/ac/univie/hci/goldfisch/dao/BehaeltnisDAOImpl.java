package at.ac.univie.hci.goldfisch.dao;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.goldfisch.model.Behaeltnis;

/**
 * Diese Klasse impementiert das GesundheitsDAO und benutzt dabei eine
 * Filesspeicherung(interne App-Speicherung)
 */

public class BehaeltnisDAOImpl implements  BehaeltnisDAO {
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
    public BehaeltnisDAOImpl(Context context, String filename) throws IOException {
        this.folder =  new File(context.getFilesDir().toString());
        this.myfile =  new File(folder.getAbsolutePath() + "/"+filename);
        this.myfile.createNewFile();
        this.context = context;
        this.filename = filename;
    }


    @Override
    public void saveBehaeltnisse(Behaeltnis neuesBehaeltnis) throws IOException {
        List<Behaeltnis> liste = this.getBehaeltnisse();
        liste.add(neuesBehaeltnis);
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(liste);
        out.close();
        outputStream.close();
    }

    @Override
    public List<Behaeltnis> getBehaeltnisse() throws IOException {
        inputStream = new FileInputStream(myfile);
        in = new ObjectInputStream(inputStream);
        List<Behaeltnis> liste = null;
        try {
            liste = (List<Behaeltnis>) in.readObject();
        }catch (Exception e){

        }
        if(liste == null)
            liste = new ArrayList<Behaeltnis>();
        in.close();
        inputStream.close();
        return liste;
    }

    @Override
    public Behaeltnis getBehaelterByName(String name) throws IOException {
        for(Behaeltnis b : this.getBehaeltnisse())
            if(b.getName().equals(name)) return b;
        return null;
    }
}
