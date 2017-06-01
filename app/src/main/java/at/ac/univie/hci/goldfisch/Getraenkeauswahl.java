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
 * Created on 11/05/2017.
 */

public class Getraenkeauswahl extends AppCompatActivity implements View.OnClickListener {

    //final keys for Intents
    public final static String LITER_MESSAGE = "LITER";
    public final static String TYP_MESSAGE = "TYP";

    //Buttons auf der Getraenkeseite
    ImageButton firstGlass_250ml;
    ImageButton secondGlass_330ml;
    ImageButton thirdGlass_500ml;

    ImageView tutorialMenue;

    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView [] dots;

    //Da gehts um die Getränkeseite
    CustomSwipeAdapter adapter; // von Getränkeklasse

    String MiliLiter = "330";
    String Getraenk = "Wasser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.getraenkeseite);


        ClickableViewPager viewPager = (ClickableViewPager) findViewById(R.id.view_page);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        adapter = new CustomSwipeAdapter(getBaseContext());
        viewPager.setAdapter(adapter);

        dotscount = adapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tutorialMenue = (ImageView) findViewById(R.id.tutorialMenue);

        //erstes mal trinken
        boolean isFirstMenu = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstMenu", true);

        if (isFirstMenu) {
            tutorialMenue = (ImageView) findViewById(R.id.tutorialMenue);
            tutorialMenue.bringToFront();
            tutorialMenue.setVisibility(View.VISIBLE);
        }

        viewPager.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Getraenk = (position==0) ? "Wasser" : (position==1) ? "Limo" : (position==2) ? "Kaffee" : "Alkohol";
                Intent trinkIntent = new Intent(getApplicationContext(), MainActivity.class);
                trinkIntent.putExtra(LITER_MESSAGE, MiliLiter);
                trinkIntent.putExtra(TYP_MESSAGE, Getraenk);
                startActivity(trinkIntent);

                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isFirstMenu", false)
                        .apply();
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
                MiliLiter = "500";
                break;

            case R.id.imageButton3:
                firstGlass_250ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                secondGlass_330ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt3));
                thirdGlass_500ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                MiliLiter = "330";
                break;
            case R.id.imageButton4:
                firstGlass_250ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                secondGlass_330ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt));
                thirdGlass_500ml.setBackgroundDrawable(getResources().getDrawable(R.drawable.amountt3));
                MiliLiter = "250";
                break;
        }
    }
}
