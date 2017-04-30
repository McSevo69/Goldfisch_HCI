package at.ac.univie.hci.goldfisch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Der Teich stellt eine Sammlung von bereits fertigen Fischen dar
 */
public class Teich implements Serializable {
    private List<Fisch> beinhalteteFische;

    public Teich(){
        this.beinhalteteFische = new ArrayList<Fisch>();
    }


    public List<Fisch> getBeinhalteteFische() {
        return beinhalteteFische;
    }
    public void setBeinhalteteFische(List<Fisch> beinhalteteFische) {
        this.beinhalteteFische = beinhalteteFische;
    }
}
