package at.ac.univie.hci.goldfisch;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Snezana on 11/05/2017.
 */

public class Getraenkeauswahl extends AppCompatActivity implements View.OnClickListener {

    //final keys for Intents
    public final static String TRINKEN_MESSAGE = "TRINKEN";

    //Buttons auf der Getraenkeseite
    ImageButton firstGlass_250ml;
    ImageButton secondGlass_330ml;
    ImageButton thirdGlass_500ml;

    LinearLayout sliderDot;
    private int dotscount;
    private ImageView [] dots;

    //Da gehts um die Getränkeseite
    CustomSwipeAdapter adapter; // von Getränkeklasse

    String MiliLiter = "330ml";
    String Getraenk = "Wasser";
    String TrinkenGlas = MiliLiter+Getraenk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.getraenkeseite);
        ClickableViewPager viewPager = (ClickableViewPager) findViewById(R.id.view_page);
        adapter = new CustomSwipeAdapter(getBaseContext());
        viewPager.setAdapter(adapter);

        viewPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Getraenk = (position==0) ? "Wasser" : (position==1) ? "Limo" : (position==2) ? "Kaffee" : "Alkohol";
                TrinkenGlas = MiliLiter+Getraenk;
                Intent trinkIntent = new Intent(getApplicationContext(), MainActivity.class);
                trinkIntent.putExtra(TRINKEN_MESSAGE, TrinkenGlas);
                startActivity(trinkIntent);
            }
        });


        firstGlass_250ml = (ImageButton) findViewById(R.id.imageButton2);  // initialisierung des firstGlass250ml Buttons erstes Glas
        secondGlass_330ml = (ImageButton) findViewById(R.id.imageButton3); // initialisierung des secondGlass_330ml Buttons (330ml-menge)
        thirdGlass_500ml = (ImageButton) findViewById(R.id.imageButton4); // initialisierung des thirdGlass_500ml Buttons (500ml-menge)


        firstGlass_250ml.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        secondGlass_330ml.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        thirdGlass_500ml.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton2:
                firstGlass_250ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt3));
                secondGlass_330ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                thirdGlass_500ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                MiliLiter = "500ml";
                break;

            case R.id.imageButton3:
                firstGlass_250ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                secondGlass_330ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt3));
                thirdGlass_500ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                MiliLiter = "330ml";
                break;
            case R.id.imageButton4:
                firstGlass_250ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                secondGlass_330ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                thirdGlass_500ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt3));
                MiliLiter = "250ml";
                break;
        }
    }
}
