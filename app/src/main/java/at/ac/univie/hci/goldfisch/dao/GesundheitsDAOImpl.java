package at.ac.univie.hci.goldfisch.dao;

import android.content.Context;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
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
        inputStream = context.openFileInput(filename);
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


    @Override
    public void fileInitialisieren() throws IOException{
        try {
            inputStream = context.openFileInput(filename);
            in = new ObjectInputStream(inputStream);
            in.close();
            inputStream.close();
            System.out.println(filename + " war schon initialisiert!");
        }catch(EOFException e) {
            System.out.println("Starte mit initialisierung des "+filename+"!");
            outputStream = new FileOutputStream(myfile);
            out = new ObjectOutputStream(outputStream);
            List<Gesundheitstipp> alleWerte = new ArrayList<Gesundheitstipp>();
            alleWerte.add(new Gesundheitstipp("Wasser ist gut!","Jedes Wasser außer dem Wasser des Helenentals ist gut!"));
            alleWerte.add(new Gesundheitstipp("Wasser ist billig!","Ein Schluck Wasser in Österreich ist nicht teuer, gönn dir mal einen!"));
            alleWerte.add(new Gesundheitstipp("Wasser ist durch rein garnichts zu ersetzen","Reines sauberes Wasser wirkt wie ein Reinigungsmittel im Körper. Sobald dieses mit Schadstoffen oder ähnlichem verunreinigt ist, verringert sich der Reinigungseffekt."));
            alleWerte.add(new Gesundheitstipp("Nach dem Aufstehen trinken","Am besten sind 2 Gläser Wasser nach dem Aufstehen. Denn in der Nacht verliert der Körper viel Flüssigkeit. Um gut in den Tag zu starten und den Kreislauf anzukurbeln empfiehlt es sich daher dem eigenen Körper gleich wieder 'aufzufüllen'."));
            alleWerte.add(new Gesundheitstipp("Trinken während des Essens","Immer auf den eigenen Körper hören. Wenn du Durst verspürst, trink was! Im besten Fall sogar dafür sorgen, dass dein Körper garkeine Durst-Signale aussenden muss. Dann stimmt alles."));

            alleWerte.add(new Gesundheitstipp("Verdurste nicht sonst stirbst du!","Wer nichts trinkt stirbt leider."));
            alleWerte.add(new Gesundheitstipp("Wer nicht trinkt ist doof!","Isso!"));
            alleWerte.add(new Gesundheitstipp("Wer nicht trinkt ist selbst schuld!","Isso!"));

            out.writeObject(alleWerte);
            out.close();
            outputStream.close();
        }
    }
}
