package at.ac.univie.hci.goldfisch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Snezana on 11/05/2017.
 */

public class FishShop extends AppCompatActivity implements View.OnClickListener {

            ImageButton fish_orange;
            ImageButton fish_green;
            ImageButton fish_blue;
            ImageButton fish_gray;






            @Override
            protected void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);
                setContentView(R.layout.fishshop);

                fish_orange = (ImageButton) findViewById(R.id.fishorange);  // initialisierung des fishorange Buttons
                fish_green = (ImageButton) findViewById(R.id.fishgreen); // initialisierung des fishgreen Buttons
                fish_blue = (ImageButton) findViewById(R.id.fishblue); // initialisierung des  fishblue Buttons
                fish_gray = (ImageButton) findViewById(R.id.fishgray); // initialisierung des  fishgray Buttons


                fish_orange.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
                fish_green.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
                fish_blue.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
                fish_gray.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden

            }



            @Override
            public void onClick(View v) {

            }



}
