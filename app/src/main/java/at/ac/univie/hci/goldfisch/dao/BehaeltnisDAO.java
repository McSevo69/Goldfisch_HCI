package at.ac.univie.hci.goldfisch.dao;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import at.ac.univie.hci.goldfisch.model.Behaeltnis;
import at.ac.univie.hci.goldfisch.model.Gesundheitstipp;

/**
 * Diese Klasse ist fuer den Zugriff auf die Behaeltnisse zum trinken verantwortlich
 */

public interface BehaeltnisDAO {

    /**
     * Speichern eines Behaeltnisses
     * @param neuesBehaeltnis Der abzuspeichernde speichernde Behaelter
     * @throws Exception Falls zB ein fehler beim Lesen eines files passiert,...
     */
    public void saveBehaeltnisse(Behaeltnis neuesBehaeltnis) throws IOException;

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

}
