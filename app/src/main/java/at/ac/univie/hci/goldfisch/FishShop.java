package at.ac.univie.hci.goldfisch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    ImageButton homebutton_fishshop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fishshop);


        fish_orange = (ImageButton) findViewById(R.id.fishorange);  // initialisierung des fishorange Buttons
        fish_green = (ImageButton) findViewById(R.id.fishgreen); // initialisierung des fishgreen Buttons
        fish_blue = (ImageButton) findViewById(R.id.fishblue); // initialisierung des  fishblue Buttons
        fish_gray = (ImageButton) findViewById(R.id.fishgray); // initialisierung des  fishgray Buttons
        homebutton_fishshop = (ImageButton) findViewById(R.id.homeButtonFishShopAnzeige);


        fish_orange.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        fish_green.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        fish_blue.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        fish_gray.setOnClickListener(this);// listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        homebutton_fishshop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.homeButtonFishShopAnzeige:
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                //setContentView(R.layout.hauptseite);
                break;
            case R.id.fishblue:
                //Pop-Up
                String TrinkMessage = "Cooler Fisch! Um ihn auswählen zu können musst du aber noch deinen aktuellen Fisch großziehen. ;-)";
                AlertDialog alertDialog = new AlertDialog.Builder(FishShop.this).create();
                alertDialog.setTitle("Upps!");
                alertDialog.setMessage(TrinkMessage);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                break;
            case R.id.fishgray:
                String TrinkMessage1 = "Cooler Fisch! Um ihn auswählen zu können musst du aber noch deinen aktuellen Fisch großziehen. ;-)";
                AlertDialog alertDialog1 = new AlertDialog.Builder(FishShop.this).create();
                alertDialog1.setTitle("Upps!");
                alertDialog1.setMessage(TrinkMessage1);
                alertDialog1.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog1.show();
                break;
            case R.id.fishgreen:
                String TrinkMessage_green = "Cooler Fisch! Um ihn auswählen zu können musst du aber noch deinen aktuellen Fisch großziehen. ;-)";
                AlertDialog alertDialog_green = new AlertDialog.Builder(FishShop.this).create();
                alertDialog_green.setTitle("Upps!");
                alertDialog_green.setMessage(TrinkMessage_green);
                alertDialog_green.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog_green.show();
                break;
            case R.id.fishorange:
                String TrinkMessage_orange = "Cooler Fisch! Um ihn auswählen zu können musst du aber noch deinen aktuellen Fisch großziehen. ;-)";
                AlertDialog alertDialog_orange = new AlertDialog.Builder(FishShop.this).create();
                alertDialog_orange.setTitle("Upps!");
                alertDialog_orange.setMessage(TrinkMessage_orange);
                alertDialog_orange.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog_orange.show();
                break;

        }


    }



}
