package at.ac.univie.hci.goldfisch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import at.ac.univie.hci.goldfisch.management.GesundheitstippsVerwaltung;
import at.ac.univie.hci.goldfisch.model.AppEinstellungen;
import at.ac.univie.hci.goldfisch.model.Behaeltnis;
import at.ac.univie.hci.goldfisch.model.Benutzer;

/**
 *
 */

public class SettingsActivity  extends AppCompatActivity implements View.OnClickListener {

    GesundheitstippsVerwaltung gesver;
    Behaelterverwaltung behver;
    Benutzerverwaltung benver;
    Einstellungenverwaltung einver;
    EditText name;
    EditText gewicht;
    EditText groesse;
    RadioGroup geschlecht;
    RadioButton geschlechtM;
    RadioButton geschlechtW;
    RadioButton taetigkeit_sitzend;
    RadioButton taetigkeit_normal;
    RadioButton taetigkeit_aktiv;

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

        benver = Benutzerverwaltung.getInstance(getApplicationContext());
        behver = Behaelterverwaltung.getInstance(getApplicationContext());
        einver = Einstellungenverwaltung.getInstance(getApplicationContext());
        gesver = GesundheitstippsVerwaltung.getInstance(getApplicationContext());

        name = (EditText) findViewById(R.id.name);
        gewicht = (EditText) findViewById(R.id.gewicht);
        groesse = (EditText) findViewById(R.id.groesse);
        geschlecht = (RadioGroup) findViewById(R.id.geschlecht);
        geschlechtM = (RadioButton) findViewById(R.id.radio_m);
        geschlechtW = (RadioButton) findViewById(R.id.radio_w);
        taetigkeit_sitzend = (RadioButton) findViewById(R.id.taetigkeit_sitzend);
        taetigkeit_normal = (RadioButton) findViewById(R.id.taetigkeit_normal);
        taetigkeit_aktiv = (RadioButton) findViewById(R.id.taetigkeit_aktiv);


        //damit die Tastatur bei Enter geschlossen wird
        groesse.setOnKeyListener(new View.OnKeyListener() {
                                     @Override
                                     public boolean onKey(View v, int keyCode, KeyEvent event) {

                                         //-Enter Taste wurde geklickt
                                         if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                                             //Mach irgend etwas wenn enter gedrückt wurde
                                             //-Tastatur ausblenden
                                             InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                             imm.hideSoftInputFromWindow(groesse.getWindowToken(), 0);

                                             return true;
                                         }

                                         return false;

                                     }

                                 });

        gewicht.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                //-Enter Taste wurde geklickt
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    //Mach irgend etwas wenn enter gedrückt wurde
                    //-Tastatur ausblenden
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(gewicht.getWindowToken(), 0);

                    return true;
                }

                return false;

            }

        });


        name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                //-Enter Taste wurde geklickt
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    //Mach irgend etwas wenn enter gedrückt wurde
                    //-Tastatur ausblenden
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(name.getWindowToken(), 0);

                    return true;
                }

                return false;

            }

        });

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
        gewicht.setText(String.valueOf((int)(aktBenutzer.getGewicht())));
        //groesse setzen
        groesse.setText(String.valueOf((int)(aktBenutzer.getGroesse())));
        //geschlecht setzen
        if(aktBenutzer.getGeschlecht()=='m') geschlechtM.setChecked(true);
        else geschlechtW.setChecked(true);
        //aktivitaet setzen
        if(aktBenutzer.getAktivitaet() == 'n') taetigkeit_normal.setChecked(true);
        if(aktBenutzer.getAktivitaet() == 's') taetigkeit_sitzend.setChecked(true);
        if(aktBenutzer.getAktivitaet() == 'a') taetigkeit_aktiv.setChecked(true);
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

                double gewichtDouble;
                double groesseDouble;
                int intervallInt;
                char geschlechtChar;
                char taetigkeitChar = 'n';

                try{
                    gewichtDouble = Double.parseDouble(gewicht.getText().toString());
                    if(gewichtDouble<0||gewichtDouble>200) {gewicht.setText("ungültiges Gewicht!!!");break;}
                }catch (NumberFormatException e){
                    gewicht.setText("ZAHL!!!");
                    break;
                }
                try{
                    groesseDouble = Double.parseDouble(groesse.getText().toString());
                    if(groesseDouble<0||groesseDouble>300) {groesse.setText("ungültige Grösse!!!");break;}
                }catch (NumberFormatException e){
                    groesse.setText("ZAHL!!!");
                    break;
                }
                try{
                    intervallInt = Integer.parseInt(intervall.getSelectedItem().toString());
                }catch (NumberFormatException e){
                    intervallInt = 0; //falls die Antwort 'gar keine' gewaehlt wurde
                }

                if(geschlechtM.isChecked()) geschlechtChar='m';
                else geschlechtChar = 'w';

                if(taetigkeit_sitzend.isChecked()) taetigkeitChar = 's';
                if(taetigkeit_normal.isChecked()) taetigkeitChar = 'n';
                if(taetigkeit_aktiv.isChecked()) taetigkeitChar = 'a';

                aktBenutzer.setAktivitaet(taetigkeitChar);
                aktBenutzer.setGroesse(groesseDouble);
                aktBenutzer.setGewicht(gewichtDouble);
                aktBenutzer.setVorname(name.getText().toString());
                aktBenutzer.setGeschlecht(geschlechtChar);
                einstellungen.setWillTipps(tipps.isChecked());
                einstellungen.setErinnerungsintervall(intervallInt);

                benver.aktualisiereBenutzer(aktBenutzer);
                einver.saveEinstellungen(einstellungen);

                //Beim ersten Mal Settings werden Änderungen sofort wirksam, sonst erst morgen
                boolean isFirstSettings = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstSettings", true);

                if (!isFirstSettings) {

                AlertDialog alertDialog = new AlertDialog.Builder(SettingsActivity.this).create();
                alertDialog.setMessage("Änderungen werden morgen wirksam.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                //Intent zur Übertragung der Daten an den Endscreen
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });
                alertDialog.show();

                } else {

                    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                            .edit()
                            .putBoolean("isFirstSettings", false)
                            .apply();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

                break;

            case R.id.ueberButtonSettings:
                String TrinkMessage = "Feedback ist uns wichtig, rufe uns an und teile uns deine Leiden mit. \nMfG, dein Goldfisch ;-)";
                AlertDialog alertDialog2 = new AlertDialog.Builder(SettingsActivity.this).create();
                alertDialog2.setTitle("Wir sind nichts ohne dich!");
                alertDialog2.setMessage(TrinkMessage);
                alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog2.show();
        }
    }
}
