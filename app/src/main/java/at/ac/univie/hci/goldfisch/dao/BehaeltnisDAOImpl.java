package at.ac.univie.hci.goldfisch.dao;

import android.content.Context;
import android.util.Log;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
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
        myfile.delete();
        if(myfile.exists())
            System.out.println("File existiert");
        myfile.createNewFile();
        this.context = context;
        this.filename = filename;
    }


    @Override
    public void saveBehaeltnisse(List<Behaeltnis> neueBehaeltnisse) throws IOException {
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(neueBehaeltnisse);
        out.close();
        outputStream.close();
    }

    @Override
    public void addBehaeltnis(Behaeltnis neuesBehaeltnis) throws IOException {
        List<Behaeltnis> liste = this.getBehaeltnisse();
        for(Behaeltnis b : liste){
            b.setAktiviert(false);
        }
        neuesBehaeltnis.setAktiviert(true);
        liste.add(neuesBehaeltnis);
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(liste);
        out.close();
        outputStream.close();
    }


    @Override
    public void leereListeReingeben() throws IOException{
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(new ArrayList<Behaeltnis>());
        out.close();
        outputStream.close();
    }









    @Override
    public List<Behaeltnis> getBehaeltnisse() throws IOException {
        System.out.println("Behaelterfile: "+myfile);
        inputStream = new FileInputStream(myfile);
        in = new ObjectInputStream(inputStream);

        List<Behaeltnis> liste = null;
        try {
            liste = (List<Behaeltnis>) in.readObject();
        }catch (EOFException e){
            System.err.println("BehaeltnisDAOImpl:getBehaeltnisse:EOFException: "+e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("BehaeltnisDAOImpl:getBehaeltnisse:ClassNotFoundException: "+e.getMessage());
        }finally {
            in.close();
            inputStream.close();
        }
        if(liste == null)
            liste = new ArrayList<Behaeltnis>();
        return liste;
    }















    @Override
    public Behaeltnis getBehaelterByName(String name) throws IOException {
        for(Behaeltnis b : this.getBehaeltnisse())
            if(b.getName().equals(name)) return b;
        return null;
    }
}
