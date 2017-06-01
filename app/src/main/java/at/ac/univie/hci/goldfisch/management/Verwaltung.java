package at.ac.univie.hci.goldfisch.management;

/**
 * Dieses interface soll von allen verwaltungsklassen implentiert werden, damit alle ihre
 * daten ausgeben koennen, sowie deren darunterliegende objekte initialisieren koennen
 */
public interface Verwaltung {

    /**
     * Zum ausgeben aller darunterliegenden Daten, welche verwaltet werden(vor allem fuer interne zwecke)
     */
    public void printAll();

    /**
     * Zum initialisieren der Management-Files
     */
    public void initialisiere();
}
