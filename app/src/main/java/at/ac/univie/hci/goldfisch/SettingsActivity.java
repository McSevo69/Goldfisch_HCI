package at.ac.univie.hci.goldfisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.UUID;

import at.ac.univie.hci.goldfisch.model.Benutzer;

import static at.ac.univie.hci.goldfisch.MainActivity.AppBenutzer;

/**
 *
 */

public class SettingsActivity  extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText gewicht;
    EditText groesse;
    RadioGroup geschlecht;
    ToggleButton tipps;
    Spinner intervall;

    ImageButton home;
    Button speichern;
    Button ueber;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einstellungenseite);

        System.out.println("Einstellungen gestartet");

        name = (EditText) findViewById(R.id.name);
        gewicht = (EditText) findViewById(R.id.gewicht);
        groesse = (EditText) findViewById(R.id.groesse);
        geschlecht = (RadioGroup) findViewById(R.id.geschlecht);
        tipps = (ToggleButton)findViewById(R.id.tipps);
        intervall = (Spinner) findViewById(R.id.tippsIntervall);

        home = (ImageButton) findViewById(R.id.homeButtonSettings);
        speichern = (Button) findViewById(R.id.saveButtonSettings);
        ueber = (Button) findViewById(R.id.ueberButtonSettings);

        home.setOnClickListener(this);
        speichern.setOnClickListener(this);
        ueber.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeButtonSettings:
                System.out.println("SettingsActivity:onClick:Homebutton bei den Einstellungen gedrueckt!");
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
                setContentView(R.layout.hauptseite);
                break;

            case R.id.saveButtonSettings:

                String vorname = name.getText().toString();
                //String geschlecht = geschlecht.getSe
                double gewichtKilo = Double.parseDouble(gewicht.getText().toString());
                double groesseCM = Double.parseDouble(groesse.getText().toString());

                char geschlechtTest = 'm';

                AppBenutzer.setVorname(vorname);
                AppBenutzer.setGewicht(gewichtKilo);
                AppBenutzer.setGroesse(groesseCM);
                AppBenutzer.setGeschlecht(geschlechtTest);
                AppBenutzer.setAktivitaet('n');

                double kiloFaktor = (AppBenutzer.getAktivitaet()=='n') ? 0.04031 : (AppBenutzer.getAktivitaet()=='a') ? 0.04535 : 0.03359;

                MainActivity.AppStatus.setTagesSollMenge(gewichtKilo*kiloFaktor);

                System.out.println("SettingsActivity:onClick:SaveButton bei den Einstellungen gedrueckt!");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                setContentView(R.layout.hauptseite);
                break;

            case R.id.ueberButtonSettings:
                System.out.println("SettingsActivity:onClick:UeberButton bei den Einstellungen gedrueckt!");
        }
    }
}
