package at.ac.univie.hci.goldfisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ToggleButton;

import at.ac.univie.hci.goldfisch.management.Behaelterverwaltung;
import at.ac.univie.hci.goldfisch.management.Benutzerverwaltung;
import at.ac.univie.hci.goldfisch.management.Einstellungenverwaltung;
import at.ac.univie.hci.goldfisch.model.AppEinstellungen;
import at.ac.univie.hci.goldfisch.model.Behaeltnis;
import at.ac.univie.hci.goldfisch.model.Benutzer;

import static at.ac.univie.hci.goldfisch.MainActivity.AppBenutzer;

/**
 *
 */

public class SettingsActivity  extends AppCompatActivity implements View.OnClickListener {

    Behaelterverwaltung behver;
    Benutzerverwaltung benver;
    Einstellungenverwaltung einver;
    EditText name;
    EditText gewicht;
    EditText groesse;
    RadioGroup geschlecht;
    RadioButton geschlechtM;
    RadioButton geschlechtW;

    ToggleButton tipps;
    Spinner intervall;

    ImageButton home;
    Button speichern;
    Button ueber;

    Benutzer aktBenutzer;
    AppEinstellungen einstellungen;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einstellungenseite);

        System.out.println("Einstellungen gestartet");

        benver = Benutzerverwaltung.getInstance(getApplicationContext());
        behver = Behaelterverwaltung.getInstance(getApplicationContext());
        einver = Einstellungenverwaltung.getInstance(getApplicationContext());

        name = (EditText) findViewById(R.id.name);
        gewicht = (EditText) findViewById(R.id.gewicht);
        groesse = (EditText) findViewById(R.id.groesse);
        geschlecht = (RadioGroup) findViewById(R.id.geschlecht);
        geschlechtM = (RadioButton) findViewById(R.id.radio_m);
        geschlechtW = (RadioButton) findViewById(R.id.radio_w);
        tipps = (ToggleButton)findViewById(R.id.tipps);
        intervall = (Spinner) findViewById(R.id.tippsIntervall);

        home = (ImageButton) findViewById(R.id.homeButtonSettings);
        speichern = (Button) findViewById(R.id.saveButtonSettings);
        ueber = (Button) findViewById(R.id.ueberButtonSettings);

        aktBenutzer = benver.getBenutzer();
        einstellungen = einver.getEinstellungen();

        //name feld setzen
        name.setText(aktBenutzer.getVorname());
        //gewicht feld setzen
        gewicht.setText(String.valueOf(aktBenutzer.getGewicht()));
        //groesse setzen
        groesse.setText(String.valueOf(aktBenutzer.getGroesse()));
        //geschlecht setzen
        if(aktBenutzer.getGeschlecht()=='m') geschlechtM.setChecked(true);
        else geschlechtW.setChecked(true);
        //tipps ein aus setzen
        tipps.setChecked(einstellungen.isWillTipps());

        //intervall setzen alle werte reingeben
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.intervallArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        intervall.setAdapter(adapter);
        //intervall setzen wie gespeichert
        int intervallSaved = einstellungen.getErinnerungsintervall();
        if(intervallSaved ==0)  intervall.setSelection(3);
        else{
            int spinnerPosition = adapter.getPosition(String.valueOf(intervallSaved));
            System.out.println("Intervall eigentlich: "+intervallSaved + ", Position: "+spinnerPosition);

            intervall.setSelection(spinnerPosition);
        }


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
                Behaeltnis kaffeeKlein = behver.getBehaeltnisByName("250mlKaffee");

                System.out.println("Behaelter: " + kaffeeKlein);

                System.out.println("Heutiger Status: "+benver.getheutigenStatus());
                benver.getraenkTrinken(kaffeeKlein);
                System.out.println("Heutiger Status: "+benver.getheutigenStatus());

                System.out.println("SettingsActivity:onClick:UeberButton bei den Einstellungen gedrueckt!");
        }
    }
}
