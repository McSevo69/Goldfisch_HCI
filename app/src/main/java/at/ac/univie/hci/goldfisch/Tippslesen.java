package at.ac.univie.hci.goldfisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Mirz'n on 12.05.2017.
 */

public class Tippslesen extends AppCompatActivity {


    TextView ueberschrift;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tippslesen);
        ueberschrift =(TextView)findViewById(R.id.UeberschriftTippslesen);
        text = (TextView)findViewById(R.id.textTippslesen);
        Intent intent0 = getIntent();
        String ueberschriftTipps = intent0.getStringExtra(Tipps.ueberschriftTipps);
        String textTipps = intent0.getStringExtra(Tipps.textTipps);



        ueberschrift.setText(ueberschriftTipps);
        text.setText(textTipps);


    }
}