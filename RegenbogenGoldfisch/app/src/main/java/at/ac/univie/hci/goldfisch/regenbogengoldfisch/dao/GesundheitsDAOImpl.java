package at.ac.univie.hci.goldfisch.regenbogengoldfisch.dao;

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

import at.ac.univie.hci.goldfisch.regenbogengoldfisch.model.Gesundheitstipp;

/**
 * Created by Gerhard on 27.04.2017.
 */

public class GesundheitsDAOImpl implements GesundheitsDAO{
    File folder;
    File myfile;
    FileOutputStream outputStream;
    ObjectOutputStream out;
    FileInputStream inputStream;
    ObjectInputStream in;
    Context context;
    String filename;

    public GesundheitsDAOImpl(Context context, String filename) throws IOException {
        this.folder =  new File(context.getFilesDir().toString());
        this.myfile =  new File(folder.getAbsolutePath() + "/"+filename);
        this.myfile.createNewFile();
        this.context = context;
        this.filename = filename;
    }


    @Override
    public void saveTipp(Gesundheitstipp tipp) throws Exception {
        List<Gesundheitstipp> liste = this.getTipps();
        liste.add(tipp);
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(liste);
        out.close();
        outputStream.close();
    }

    @Override
    public List<Gesundheitstipp> getTipps() throws Exception {
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
    public List<Gesundheitstipp> getTippByHeading(String heading) throws Exception {
        List<Gesundheitstipp> gefundene = new ArrayList<Gesundheitstipp>();
        List<Gesundheitstipp> liste = this.getTipps();
        for(Gesundheitstipp t : liste){
            if(t.getHeading().equals(heading))
                gefundene.add(t);
        }
        return gefundene;
    }

    @Override
    public Gesundheitstipp getTippByID(UUID id) throws Exception {
        List<Gesundheitstipp> liste = this.getTipps();
        for(Gesundheitstipp t : liste){
            if(t.getId().equals(id))
                return t;
        }
        return null;
    }

    @Override
    public void loescheAlleTipps() throws Exception {
        outputStream = new FileOutputStream(myfile);
        out = new ObjectOutputStream(outputStream);
        out.writeObject(new ArrayList<Gesundheitstipp>());
        out.close();
        outputStream.close();
    }

    @Override
    public void loescheTippByID(UUID id) throws Exception {
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
