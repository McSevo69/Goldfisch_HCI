package at.ac.univie.hci.goldfisch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by root on 12.05.17.
 */

public class FischTeich extends AppCompatActivity implements View.OnClickListener{


    ImageButton home;
    ImageButton teilen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fischteich);

        home = (ImageButton) findViewById(R.id.homeButtonTeich);
        teilen = (ImageButton) findViewById(R.id.teilenButton);

        home.setOnClickListener(FischTeich.this);
        teilen.setOnClickListener(FischTeich.this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.homeButtonTeich:
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                //setContentView(R.layout.hauptseite);
                break;

            case R.id.teilenButton:

                //Pop-Up
                String TeilenMessage = "Willst du wirklich ein Bild deines Fisches mit deinen Freunden teilen?";

                AlertDialog alertDialog = new AlertDialog.Builder(FischTeich.this).create();
                alertDialog.setTitle("Zeig es der ganzen Welt ;-)");
                alertDialog.setMessage(TeilenMessage);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                break;
        }

    }
}
