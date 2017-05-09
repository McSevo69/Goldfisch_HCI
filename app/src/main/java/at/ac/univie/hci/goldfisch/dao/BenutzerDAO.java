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
     * Diese Methode speichert, bzw ueberschreibt einen moeglich vorhandenen Benutzer
     * @param b der neue Benutzer
     */
    public void saveBenutzer(Benutzer b) throws IOException;

    /**
     * Diese Methode liefert das Fischglas des Benutzers zurueck
     * @return Das Fischglas am Startbildschirm des Benutzers
     */
    public Glas getGlas() throws IOException;

    /**
     * Diese Methode liefert den Teich des Benuzters, welches seine Fische beinhaltet
     * @return Der Teich des Benutzers
     */
    public Teich getTeich() throws IOException;

    /**
     * Zum initialisieren des FIles
     * @throws IOException
     */
    public void fileInitialisieren() throws IOException;
}
