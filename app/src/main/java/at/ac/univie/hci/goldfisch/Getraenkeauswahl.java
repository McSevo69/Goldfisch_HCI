package at.ac.univie.hci.goldfisch;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Snezana on 11/05/2017.
 */

public class Getraenkeauswahl extends AppCompatActivity implements View.OnClickListener {


    //Buttons auf der Getraenkeseite
    ImageButton firstGlass_250ml;
    ImageButton secondGlass_330ml;
    ImageButton thirdGlass_500ml;


    ViewPager viewPager;
    LinearLayout sliderDot;
    private int dotscount;
    private ImageView [] dots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.getraenkeseite);

     /*  ----------Dots---------- noch nicht funk


        viewPager = (ViewPager) findViewById(R.id.viewPager);
       // sliderDot = (LinearLayout) findViewById(R.id.sliderDots);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for ( int i = 0; i < dotscount; i++ ){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.));
        }
        */


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
                //
                break;

            case R.id.imageButton3:
                //
                break;
            case R.id.imageButton4:
                //
                break;
        }
    }
}
