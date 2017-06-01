package at.ac.univie.hci.goldfisch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created on 13.05.2017.
 */

public class Erfolge extends AppCompatActivity implements View.OnClickListener{

    ImageView sternBronze;
    ImageView sternSilber;
    ImageView sternGold;
    ImageView sternTitan;
    ImageView sternTitan2;
    ImageView sternTitan22;
    ImageButton homeButtonErfolge;

    TextView sternBronzeText;
    TextView sternSilberText;
    TextView sternGoldText;
    TextView sternTitanText;
    TextView sternTitan2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erfolgeseite);

        homeButtonErfolge = (ImageButton)findViewById(R.id.homeButtonErfolgeSeite);
        homeButtonErfolge.setOnClickListener(this);

        sternBronze = (ImageView)findViewById(R.id.SternBronzeErfolgeSeite);
        sternSilber = (ImageView)findViewById(R.id.SternSilverErfolgeSeite);
        sternGold = (ImageView)findViewById(R.id.SternGoldErfolgeSeite);
        sternTitan = (ImageView)findViewById(R.id.SternTitanErfolgeSeite1);
        sternTitan2 = (ImageView)findViewById(R.id.SternTitanzweiErfolgeSeite1);
        sternTitan22 = (ImageView)findViewById(R.id.SternTitanzwei2ErfolgeSeite1);

        sternBronzeText = (TextView)findViewById(R.id.TextSternBronzeErfolgeSeite);
        sternSilberText = (TextView)findViewById(R.id.TextSternSilverErfolgeSeite);
        sternGoldText = (TextView)findViewById(R.id.TextSternGoldErfolgeSeite);
        sternTitanText = (TextView)findViewById(R.id.TextSternTitanErfolgeSeite);
        sternTitan2Text = (TextView)findViewById(R.id.TextSternTitan2ErfolgeSeite);


    }

    @Override
    public void onClick(View v) {

        Intent backHomeIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(backHomeIntent);

    }
}