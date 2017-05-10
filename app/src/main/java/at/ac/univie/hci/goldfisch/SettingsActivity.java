package at.ac.univie.hci.goldfisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ToggleButton;

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
    Button saveButton, homeButton, ueberButton;



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

        saveButton.setOnClickListener(SettingsActivity.this);
        homeButton.setOnClickListener(SettingsActivity.this);
        ueberButton.setOnClickListener(SettingsActivity.this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeButtonSettings:
                System.out.println("SettingsActivity:onClick:Homebutton bei den Einstellungen gedrueckt!");
                break;

            case R.id.saveButtonSettings:
                System.out.println("SettingsActivity:onClick:SaveButton bei den Einstellungen gedrueckt!");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;

            case R.id.ueberButtonSettings:
                System.out.println("SettingsActivity:onClick:UeberButton bei den Einstellungen gedrueckt!");
        }
    }
}
