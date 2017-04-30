package at.ac.univie.hci.goldfisch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Hierbei handelt es sich um eine Einteilung der Fische, hier werden Fische in einer Liste
 * abgespeichert, welche zusammengehoeren
 */
public class Fischkategorie  implements Serializable {
    private UUID id;
    private String name;
    private List<Fisch> fische;

    public Fischkategorie(String name){
        this.id = UUID.randomUUID();
        this.name = name;
        this.fische = new ArrayList<Fisch>();
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setFische(List<Fisch> fische) {
        this.fische = fische;
    }
    public List<Fisch> getFische() {
        return fische;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
}
