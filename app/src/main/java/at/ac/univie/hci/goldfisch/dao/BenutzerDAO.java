package at.ac.univie.hci.goldfisch.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import at.ac.univie.hci.goldfisch.model.Behaeltnis;
import at.ac.univie.hci.goldfisch.model.Benutzer;
import at.ac.univie.hci.goldfisch.model.Glas;
import at.ac.univie.hci.goldfisch.model.Teich;

/**
 * Mit diesem Interface soll spezifiziert werden, welche Methoden es geben soll,
 * um mit dem Benutzer umzugehen, bzw., dessen Werte zu speichern
 */
public interface BenutzerDAO {
    /**
     * Diese Methode liefert das Benutzerobjekt, welches die wesentlichen Daten des Benutzers beinhaltet
     * @return Das Objekt des Benutzers
     */
    public Benutzer getBenutzer() throws IOException;

    /**
     * Mit dieser Methode kann man den Benutzer aktualisieren und dessen daten aendern
     * ruft intern einfach saveBenutzer auf
     * @param b Das Objekt mit den neuen Benutzerdaten
     */
    public void aktualisiereBenutzer(Benutzer b) throws IOException;

    /**
     * Diese Methode speichert, bzw ueberschreibt einen moeglich vorhandenen Benutzer
     * @param b der neue Benutzer
     */
    public void saveBenutzer(Benutzer b) throws IOException;

    /**
     * Diese Methode liefert das Fischglas des Benutzers zurueck
     * @return Das Fischglas am Startbildschirm des Benutzers
     */
    public Glas getGlas();

    /**
     * Diese Methode liefert den Teich des Benuzters, welches seine Fische beinhaltet
     * @return Der Teich des Benutzers
     */
    public Teich getTeich();
}
