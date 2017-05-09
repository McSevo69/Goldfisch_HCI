package at.ac.univie.hci.goldfisch.dao;

import java.io.IOException;
import java.util.List;

import at.ac.univie.hci.goldfisch.model.Behaeltnis;

/**
 * Diese Klasse ist fuer den Zugriff auf die Behaeltnisse zum trinken verantwortlich
 */

public interface BehaeltnisDAO {

    /**
     * Speichern der neuen Behaeltnisliste
     * @param neueBehaeltnisse Der abzuspeichernde speichernde Behaelter
     * @throws Exception Falls zB ein fehler beim Lesen eines files passiert,...
     */
    public void saveBehaeltnisse(List<Behaeltnis> neueBehaeltnisse) throws IOException;

    /**
     * Diese Methode fuegt ein neues Behaeltnis hinzu
     * @param neuesBehaeltnis Das neue Behaeltnis
     * @throws IOException Falls zB ein fehler beim Lesen eines files passiert,...
     */
    public void addBehaeltnis(Behaeltnis neuesBehaeltnis) throws IOException;

    /**
     * Diese Methode soll eine Liste aller gespeicherten Behaeltnisse zurueckliefern
     * @return Liste aller gespeicherten Behaeltnisse, oder eine leere Liste, falls nix drinnen ist, nicht null
     * @throws Exception Falls zB ein fehler beim Lesen eines files passiert,...
     */
    public List<Behaeltnis> getBehaeltnisse() throws IOException;

    /**
     * Diese Methodel liefert den Behaelter mit den angegebenen namen
     * @param name der Name des zu suchenden Behaelters
     * @return der gesuchte Behaelter
     * @throws IOException Falls zB ein fehler beim Lesen eines files passiert,...
     */
    public Behaeltnis getBehaelterByName(String name) throws IOException;


    /**
     * Da sonst ein error entsteht, wenn man einen inputsream auf ein leeres file setzt
     * Es wird gleich auch eine liste mit den wichtigsten getraenken reingegeben
     * @throws IOException
     */
    public void fileInitialisieren() throws  IOException;
}
