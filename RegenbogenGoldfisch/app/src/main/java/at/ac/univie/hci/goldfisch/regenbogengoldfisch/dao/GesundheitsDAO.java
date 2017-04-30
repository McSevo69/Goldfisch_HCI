package at.ac.univie.hci.goldfisch.regenbogengoldfisch.dao;

import java.util.List;
import java.util.UUID;

import at.ac.univie.hci.goldfisch.regenbogengoldfisch.model.Gesundheitstipp;

/**
 * Mit diesem Interface soll spezifiziert werden, welche Methoden es geben soll,
 * um mit Gesundheitstipps umzugehen
 * Created by Gerhard on 27.04.2017.
 */
public interface GesundheitsDAO {

    /**
     * Speichern eines Gesundheitstipps
     * @param tipp Der abzuspeichernde speichernde Tipp
     * @throws Exception Falls zB ein fehler beim Lesen eines files passiert,...
     */
    public void saveTipp(Gesundheitstipp tipp) throws Exception;

    /**
     * Diese Methode soll eine Liste aller gespeicherten Gesundheitstipps zurueckliefern
     * @return Liste aller gespeicherten Gesundheitstipps, oder eine leere Liste, falls nix drinnen ist, nicht null
     * @throws Exception Falls zB ein fehler beim Lesen eines files passiert,...
     */
    public List<Gesundheitstipp> getTipps() throws Exception;

    /**
     * Diese Methode ist fuer den Selektiven Zugriff auf einen bestimmten Gesundheitstipp, anhand der Ueberschrift
     * @param heading Die Ueberschrift des zu suchenden Tipps
     * @return Der gefundene Tipp, oder null
     * @throws Exception Falls zB ein fehler beim Lesen eines files passiert,...
     */
    public List<Gesundheitstipp> getTippByHeading(String heading) throws Exception;

    /**
     * Diese Methode ist fuer den Selektiven Zugriff auf einen bestimmten Gesundheitstipp, anhand der ID
     * @param id Die ID des zu suchenden Tipps
     * @return Der gefundene Tipp, oder null
     * @throws Exception Falls zB ein fehler beim Lesen eines files passiert,...
     */
    public Gesundheitstipp getTippByID(UUID id) throws  Exception;

    /**
     * Diese Methode loescht alle enthaltenen Tipps
     * @throws Exception
     */
    public void loescheAlleTipps() throws  Exception;


    /**
     * Mit dieser Methode kann man einen Tipp loeschen per ID
     * @throws Exception
     */
    public void loescheTippByID(UUID id) throws Exception;
}
