package at.ac.univie.hci.goldfisch.dao;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import at.ac.univie.hci.goldfisch.model.Gesundheitstipp;

/**
 * Diese Klasse impementiert das GesundheitsDAO und benutzt dabei eine
 * Filesspeicherung(interne App-Speicherung)
 */
public class GesundheitsDAOImpl implements GesundheitsDAO{
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
    public GesundheitsDAOImpl(Context context, String filename) throws IOException {
        this.folder =  new File(context.getFilesDir().toString());
        this.myfile =  new File(folder.getAbsolutePath() + "/"+filename);
        this.myfile.createNewFile();
        this.context = context;
        this.filename = filename;
    }


    @Override
    public void saveTipp(Gesundheitstipp tipp) throws IOException {
        List<Gesundheitstipp> liste = this.getTipps();
        liste.add(tipp);
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(liste);
        out.close();
        outputStream.close();
    }

    @Override
    public List<Gesundheitstipp> getTipps() throws IOException {
        inputStream = new FileInputStream(myfile);
        in = new ObjectInputStream(inputStream);
        List<Gesundheitstipp> liste = null;
        try {
            liste = (List<Gesundheitstipp>) in.readObject();
        }catch (Exception e){

        }
        if(liste == null)
            liste = new ArrayList<Gesundheitstipp>();
        in.close();
        inputStream.close();
        return liste;
    }

    @Override
    public List<Gesundheitstipp> getTippByUeberschrift(String ueberschrift) throws IOException {
        List<Gesundheitstipp> gefundene = new ArrayList<Gesundheitstipp>();
        List<Gesundheitstipp> liste = this.getTipps();
        for(Gesundheitstipp t : liste){
            if(t.getUeberschrift().equals(ueberschrift))
                gefundene.add(t);
        }
        return gefundene;
    }

    @Override
    public Gesundheitstipp getTippByID(UUID id) throws IOException {
        List<Gesundheitstipp> liste = this.getTipps();
        for(Gesundheitstipp t : liste){
            if(t.getId().equals(id))
                return t;
        }
        return null;
    }

    @Override
    public void loescheAlleTipps() throws IOException {
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(new ArrayList<Gesundheitstipp>());
        out.close();
        outputStream.close();
    }

    @Override
    public void loescheTippByID(UUID id) throws IOException {
        List<Gesundheitstipp> liste = this.getTipps();
        for(Gesundheitstipp t : liste){
            if(t.getId().equals(id))
                liste.remove(t);
        }
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(liste);
        out.close();
        outputStream.close();
    }
}
