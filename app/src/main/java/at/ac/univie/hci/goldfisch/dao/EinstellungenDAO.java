package at.ac.univie.hci.goldfisch.dao;

import java.io.IOException;

import at.ac.univie.hci.goldfisch.model.AppEinstellungen;

/**
 * Dieses DAO ist zum Abrufen der globalen Einstellungen
 */
public interface EinstellungenDAO {
    /**
     * Damit kann man die globalen aktuellen Einstellungen holen
     * @return die aktuellen AppEinstellungen
     */
    public AppEinstellungen getEinstellungen() throws IOException;

    /**
     * Damit werden die globalen Einstellungen ueberschrieben
     * @param ae die neuen Einstellungen
     */
    public void aktualisiereAppEinstellungen(AppEinstellungen ae) throws IOException;

    /**
     * Zum initialisieren des FIles
     * @throws IOException
     */
    public void fileInitialisieren() throws IOException;
}
