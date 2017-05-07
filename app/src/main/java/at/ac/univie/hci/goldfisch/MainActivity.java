package at.ac.univie.hci.goldfisch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import at.ac.univie.hci.goldfisch.management.Benutzerverwaltung;
import at.ac.univie.hci.goldfisch.management.Einstellungenverwaltung;
import at.ac.univie.hci.goldfisch.model.AppEinstellungen;
import at.ac.univie.hci.goldfisch.model.Behaeltnis;


/**
 * Created by Mirz'n on 03.05.2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Buttons auf der Hauptseite
    ImageButton shopping;//Einkaufswagen Button auf der Hauptseite
    ImageButton setting; //Einstellungen Button auf der Hauptseite
    ImageButton trophae;//Trophaen Button auf der Hauptseite
    Button trinkKreis; // Kreis der sich dreht beim Trinken

    //Variable der Prozentanzeige
    int percentStatus = 0; //Variable der Prozentanzeige
    private Handler handler = new Handler(); // dient zum Aufrufen der Einstellungen des Kreises
    TextView trinkStatus; // Zeigt die Prozentzahl der Kreises im Kreis an.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hauptseite);


        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate); // Lässt den Kreis beim Trinken rotieren
        shopping = (ImageButton) findViewById(R.id.shoppingButton); // initialisierung des shopping Buttons
        setting = (ImageButton) findViewById(R.id.settingsButton);  // initialisierung des settings Buttons
        trophae = (ImageButton) findViewById(R.id.trophaeButton);  // initialisierung des Stern Buttons
        trinkKreis = (Button) findViewById(R.id.trinkenKreis);  // initialisierung des Trinktreises der sich beim lange drücken dreht
        shopping.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        setting.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        trophae.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        trinkKreis.setOnLongClickListener(new View.OnLongClickListener() { // Listener der sofort die Rotationsbewegung ausführt wenn er lange gedrückt wird
            @Override
            public boolean onLongClick(View arg0) {
                arg0.startAnimation(animRotate);
                return false;
            }
        });
		
		        //Kreis mit Prozentanzeige
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar); // initialisierung des Kreises
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);
		
		
		        //Dieser Teil betrifft nur die Prozentanzeige recht Oben und lässt sie mittels Schleife bis auf 100% auffüllen

         /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/
        trinkStatus = (TextView) findViewById(R.id.trinkStatus);
        new Thread(new Runnable() { // ausführung der Rotation des Kreises dieser Thread füllt den Kreis mit jeweils einem Punkt und erhöht in bis 100%
                                    // Diese Schleife dient nur als vorführung und sollte unterbunden werden sodass nur die prozent angezeigt werden
                                    // die auch getrunken wurden.
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (percentStatus < 100) {
                    percentStatus += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(percentStatus);
                            trinkStatus.setText(percentStatus + "%");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(8); //thread will take approx 1.5 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


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
