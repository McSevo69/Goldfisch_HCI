package at.ac.univie.hci.goldfisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.List;
import at.ac.univie.hci.goldfisch.management.GesundheitstippsVerwaltung;
import at.ac.univie.hci.goldfisch.model.Gesundheitstipp;


/**
 * Created by Mirz'n on 12.05.2017.
 */

public class Tipps extends AppCompatActivity implements View.OnClickListener{

    public final static String ueberschriftTipps = "ueberschriftTipps";
    public final static String textTipps = "textTipps";

    Button feld0,feld1,feld2,feld3,feld4;
    ImageButton home;
    GesundheitstippsVerwaltung gesver;
    Gesundheitstipp tipp0,tipp1,tipp2,tipp3,tipp4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tippsanzeige);

        feld0 = (Button)findViewById(R.id.gesundheitstippFeld0);
        feld1 = (Button)findViewById(R.id.gesundheitstippFeld1);
        feld2 = (Button)findViewById(R.id.gesundheitstippFeld2);
        feld3 = (Button)findViewById(R.id.gesundheitstippFeld3);
        feld4 = (Button)findViewById(R.id.gesundheitstippFeld4);
        home = (ImageButton)findViewById(R.id.homeButtonTippsAnzeige);
        home.setOnClickListener(this);
        feld0.setOnClickListener(this);
        feld1.setOnClickListener(this);
        feld2.setOnClickListener(this);
        feld3.setOnClickListener(this);
        feld4.setOnClickListener(this);
        gesver = GesundheitstippsVerwaltung.getInstance(getApplicationContext());
        gesver.initialisiere();

        tipp0 = gesver.getRandomTipp();
        tipp1 = gesver.getRandomTipp();
        tipp2 = gesver.getRandomTipp();
        tipp3 = gesver.getRandomTipp();
        tipp4 = gesver.getRandomTipp();

        feld0.setText(tipp0.getUeberschrift());
        feld1.setText(tipp1.getUeberschrift());
        feld2.setText(tipp2.getUeberschrift());
        feld3.setText(tipp3.getUeberschrift());
        feld4.setText(tipp4.getUeberschrift());



    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.homeButtonSettings:
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
                setContentView(R.layout.hauptseite);
                break;
            case R.id.gesundheitstippFeld0:
                Intent intent0 = new Intent(v.getContext(), Tippslesen.class);
                intent0.putExtra(ueberschriftTipps, tipp0.getUeberschrift());
                intent0.putExtra(textTipps, tipp0.getTipp());
                setContentView(R.layout.tippslesen);
                System.out.println(tipp0.getUeberschrift());
                System.out.println(tipp0.getTipp());
                startActivity(intent0);
                break;
            case R.id.gesundheitstippFeld1:
                Intent intent1 = new Intent(v.getContext(), Tippslesen.class);
                intent1.putExtra(ueberschriftTipps, tipp1.getUeberschrift());
                intent1.putExtra(textTipps, tipp1.getTipp());
                setContentView(R.layout.tippslesen);
                startActivity(intent1);
                break;
            case R.id.gesundheitstippFeld2:
                Intent intent2 = new Intent(v.getContext(), Tippslesen.class);
                intent2.putExtra(ueberschriftTipps, tipp2.getUeberschrift());
                intent2.putExtra(textTipps, tipp2.getTipp());
                startActivity(intent2);
                break;
            case R.id.gesundheitstippFeld3:
                Intent intent3 = new Intent(v.getContext(), Tippslesen.class);
                intent3.putExtra(ueberschriftTipps, tipp3.getUeberschrift());
                intent3.putExtra(textTipps, tipp3.getTipp());
                startActivity(intent3);
                break;
            case R.id.gesundheitstippFeld4:
                Intent intent4 = new Intent(v.getContext(), Tippslesen.class);
                intent4.putExtra(ueberschriftTipps, tipp4.getUeberschrift());
                intent4.putExtra(textTipps, tipp4.getTipp());
                startActivity(intent4);
                break;
        }
    }

}
