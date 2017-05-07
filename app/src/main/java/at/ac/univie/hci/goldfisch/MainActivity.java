package at.ac.univie.hci.goldfisch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import at.ac.univie.hci.goldfisch.management.Benutzerverwaltung;
import at.ac.univie.hci.goldfisch.management.Einstellungenverwaltung;
import at.ac.univie.hci.goldfisch.model.AppEinstellungen;
import at.ac.univie.hci.goldfisch.model.Behaeltnis;


/**
 * Created by Mirz'n on 03.05.2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton einkauf;
    ImageButton einstellung;
    ImageButton trophae;
    Button trinken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hauptseite);


        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        einkauf = (ImageButton)findViewById(R.id.shoppingButton);
        einstellung = (ImageButton)findViewById(R.id.settingsButton);
        trophae = (ImageButton)findViewById(R.id.trophaeButton);
        trinken = (Button) findViewById(R.id.trinken);
        einkauf.setOnClickListener(this);
        einstellung.setOnClickListener(this);
        trophae.setOnClickListener(this);
        trinken.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                arg0.startAnimation(animRotate);
            }});

        try {
            System.out.println("Benutzer anlegen");
            Benutzerverwaltung.getInstance(getApplicationContext()).neuenBenutzerTestAnlegen();
            Einstellungenverwaltung.getInstance(getApplicationContext()).speichereEinstellungen(new AppEinstellungen(new Behaeltnis(null,true,150)));
            System.out.println("Benutzer drinnen: \n "+Benutzerverwaltung.getInstance(getApplicationContext()).getBenutzer());
            Einstellungenverwaltung.getInstance(getApplicationContext()).printEinstellungen();
            System.out.println("Benutzer und Einstellungen gespeichert");
        }catch (Exception e){
            System.err.println("haahhah");
        }
    }

    @Override
    public void onClick(View v) {



    }
}
