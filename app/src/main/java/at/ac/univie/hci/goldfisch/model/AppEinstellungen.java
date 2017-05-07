package at.ac.univie.hci.goldfisch.model;

import java.io.Serializable;

/**
 * Hier werden alle wichtigen globalen Einstellungen des Users gespeichert
 */

public class AppEinstellungen implements Serializable{
    private boolean willTipps;
    private boolean willErinnerungen;
    private int erinnerungsintervall;
    private Behaeltnis ausgewBehaeltnis;
    private Glas ausgewGlas;

    public AppEinstellungen(Behaeltnis ausgewBehaeltnis){
        this.willTipps = true;
        this.willErinnerungen = true;
        this.erinnerungsintervall = 6; // alle 6 Stunden erinnern
        this.ausgewBehaeltnis = ausgewBehaeltnis;
    }

    public boolean isWillTipps() {
        return willTipps;
    }
    public void setWillTipps(boolean willTipps) {
        this.willTipps = willTipps;
    }

    public boolean isWillErinnerungen() {
        return willErinnerungen;
    }
    public void setWillErinnerungen(boolean willErinnerungen) {
        this.willErinnerungen = willErinnerungen;
    }

    public int getErinnerungsintervall() {
        return erinnerungsintervall;
    }
    public void setErinnerungsintervall(int erinnerungsintervall) {
        this.erinnerungsintervall = erinnerungsintervall;
    }

    public Behaeltnis getAusgewBehaeltnis() {
        return ausgewBehaeltnis;
    }
    public void setAusgewBehaeltnis(Behaeltnis ausgewBehaeltnis) {
        this.ausgewBehaeltnis = ausgewBehaeltnis;
    }

    @Override
    public String toString() {
        return "AppEinstellungen{" +
                "willTipps=" + willTipps +
                ", willErinnerungen=" + willErinnerungen +
                ", erinnerungsintervall=" + erinnerungsintervall +
                ", ausgewBehaeltnis=" + ausgewBehaeltnis +
                ", ausgewGlas=" + ausgewGlas +
                '}';
    }


}
