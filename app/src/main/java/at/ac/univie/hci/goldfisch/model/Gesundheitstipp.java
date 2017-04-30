package at.ac.univie.hci.goldfisch.model;

import java.util.UUID;

/**
 * Created by Gerhard on 27.04.2017.
 */

public class Gesundheitstipp implements java.io.Serializable{

    private UUID id;
    private String tipp;
    private String heading;


    @Override
    public String toString() {
        return "Gesundheitstipp{" +
                "id=" + id +
                ", tipp='" + tipp + '\'' +
                ", heading='" + heading + '\'' +
                '}';
    }

    public Gesundheitstipp(String heading, String tipp){
        this.tipp = tipp;
        this.heading = heading;
        this.id = UUID.randomUUID();

    }


    public void setTipp(String tipp) {
        this.tipp = tipp;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTipp() {

        return tipp;
    }

    public String getHeading() {
        return heading;
    }

    public UUID getId() {
        return id;
    }

}
