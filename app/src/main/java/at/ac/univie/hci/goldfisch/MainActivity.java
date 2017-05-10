package at.ac.univie.hci.goldfisch;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import at.ac.univie.hci.goldfisch.management.Behaelterverwaltung;
import at.ac.univie.hci.goldfisch.management.Benutzerverwaltung;
import at.ac.univie.hci.goldfisch.management.Einstellungenverwaltung;
import at.ac.univie.hci.goldfisch.management.GesundheitstippsVerwaltung;
import at.ac.univie.hci.goldfisch.model.AppEinstellungen;
import at.ac.univie.hci.goldfisch.model.Behaeltnis;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Notification Variablen
    private PendingIntent pendingIntent;
    NotificationManager notificationManager;
    int notifID = 33;
    boolean isNotificActive = false;

    //Trinkvariablen - Hardcode (müssen noch aus Menü ausgelesen werden)
    double literProKiloNormal = 0.04031;
    double literProKiloAktiv = 0.04535;
    double literProKiloSitzend = 0.03359;
    double userKiloDefault = 75; //falls keine Körperangaben gemacht werden
    double literProTag = literProKiloNormal * userKiloDefault;
    double behaelterDefault = 0.25;
    double wasserstand = 0;
    double hydrationsFaktor = 1; //per default Wasser

    //Verwaltungsklassen
    Benutzerverwaltung benver;
    Behaelterverwaltung behver;
    Einstellungenverwaltung einver;
    GesundheitstippsVerwaltung gesver;

    //Buttons auf der Hauptseite
    ImageButton shopping;//Einkaufswagen Button auf der Hauptseite
    ImageButton setting; //Einstellungen Button auf der Hauptseite
    ImageButton trophae;//Trophaen Button auf der Hauptseite
    Button trinkKreis; // Kreis der sich dreht beim Trinken

	
    //Da gehts um die Getränkeseite
    ViewPager viewPager;  //PageButton
    CustomSwipeAdapter adapter; // von Getränkeklasse

    //Variable der Prozentanzeige
    int percentStatus = 0; //Variable der Prozentanzeige
    private Handler handler = new Handler(); // dient zum Aufrufen der Einstellungen des Kreises
    TextView trinkStatus; // Zeigt die Prozentzahl der Kreises im Kreis an.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("oncreate: "+new Date());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hauptseite);

        Context context =  getApplicationContext();

        ComponentName receiver = new ComponentName(context, AlertReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        setAlarm();

        benver = Benutzerverwaltung.getInstance(getApplicationContext());
        behver = Behaelterverwaltung.getInstance(getApplicationContext());
        einver = Einstellungenverwaltung.getInstance(getApplicationContext());
        gesver = GesundheitstippsVerwaltung.getInstance(getApplicationContext());

        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate); // Lässt den Kreis beim Trinken rotieren
        shopping = (ImageButton) findViewById(R.id.shoppingButton); // initialisierung des shopping Buttons
        setting = (ImageButton) findViewById(R.id.settingsButton);  // initialisierung des settings Buttons
        trophae = (ImageButton) findViewById(R.id.trophaeButton);  // initialisierung des Stern Buttons
        trinkKreis = (Button) findViewById(R.id.trinkenKreis);  // initialisierung des Trinktreises der sich beim lange drücken dreht
        shopping.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        setting.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        trophae.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        trinkKreis.setOnClickListener(this);

        //Kreis mit Prozentanzeige
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar); // initialisierung des Kreises
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);


        trinkStatus = (TextView) findViewById(R.id.trinkStatus);
        trinkStatus.setText(percentStatus + "%");

        trinkKreis.setOnLongClickListener(new View.OnLongClickListener() { // Listener der sofort die Rotationsbewegung ausführt wenn er lange gedrückt wird
            @Override
            public boolean onLongClick(View arg0) {
                arg0.startAnimation(animRotate);
                wasserstand += (behaelterDefault*hydrationsFaktor);
                percentStatus = (int) ((wasserstand/literProTag)*100);
                mProgress.setProgress(percentStatus);
                trinkStatus.setText(percentStatus + "%");

                //Pop-Up
                String TrinkMessage = "Du hast heute " + wasserstand + " Liter von empfohlenen " + literProTag + " Liter getrunken.";
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Gut gemacht!");
                alertDialog.setMessage(TrinkMessage);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

                return true;
            }
        });


        try {
            System.out.println("Beginne mit BackendSteuff:");
            einver.initialisiere();
            benver.initialisiere();
            behver.initialisiere();
            gesver.initialisiere();

            einver.printAll();
            gesver.printAll();
            behver.printAll();
            benver.printAll();

            System.out.println("\nNun 10 randomTipps, weil wir's koennen: ");
            for(int i=0;i<10;i++)
                System.out.println(i+":"+gesver.getRandomTipp());

        }catch (Exception e){
            e.printStackTrace();
        }
    }//oncreate


    private void showNotification(View view){

        String notiTtl = "Dein Fisch braucht dich!";
        String notiMsg = "Trink doch gleich ein Glas Wasser.";

        NotificationCompat.Builder notificBuilder = new
                NotificationCompat.Builder(this)
                .setContentTitle(notiTtl)
                .setContentText(notiMsg)
                .setTicker("Zeit für ein Glas Wasser")
                .setSmallIcon(R.drawable.notification_pic)
                .setAutoCancel(true);

        Intent notifyServiceIntent = new Intent(this , MainActivity.class);

        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(this);

        tStackBuilder.addParentStack(MainActivity.class);

        tStackBuilder.addNextIntent(notifyServiceIntent);

        pendingIntent = tStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        notificBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifID, notificBuilder.build());

        isNotificActive = true;

    }


    public void setAlarm() {

        //Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, PendingIntent.getBroadcast(this, 1, alertIntent,
                PendingIntent.FLAG_UPDATE_CURRENT));

    }

    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.shoppingButton:
                    //
                    break;

                case R.id.erfolgeButton:
                    //
                    break;

                case R.id.settingsButton:
                    //Intent zur Übertragung der Daten an den Endscreen
                    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.teichButton:
                    //
                    break;

                case R.id.tippsButton:
                    //
                    break;

                case R.id.trophaeButton:
                    //
                    break;

                case R.id.trinkenKreis:
                    //getraenkeseite ist zustaendig für die mengeneinheiten und beinhaltet radiobuttons
                    setContentView(R.layout.getraenkeseite);
                    viewPager = (ViewPager) findViewById(R.id.view_page);
                    adapter = new CustomSwipeAdapter(getBaseContext());
                    viewPager.setAdapter(adapter);

            }


    }
}
