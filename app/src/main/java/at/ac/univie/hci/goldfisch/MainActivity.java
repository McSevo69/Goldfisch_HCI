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

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import at.ac.univie.hci.goldfisch.management.Behaelterverwaltung;
import at.ac.univie.hci.goldfisch.management.Benutzerverwaltung;
import at.ac.univie.hci.goldfisch.management.Einstellungenverwaltung;
import at.ac.univie.hci.goldfisch.management.GesundheitstippsVerwaltung;
import at.ac.univie.hci.goldfisch.model.AppEinstellungen;
import at.ac.univie.hci.goldfisch.model.Behaeltnis;
import at.ac.univie.hci.goldfisch.model.Benutzer;
import at.ac.univie.hci.goldfisch.model.Status;
import pl.droidsonroids.gif.GifTextView;

import static java.lang.Double.parseDouble;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //default Behaelter
    String GetraenkeTyp = "Wasser";
    String Menge = "250";
    String AktiverBehaelter = Menge + "ml" + GetraenkeTyp;
    String trinkInfobutton = GetraenkeTyp+" : "+Menge + "ml";


    //Notification Variablen
    private PendingIntent pendingIntent;
    NotificationManager notificationManager;
    int notifID = 33;
    boolean isNotificActive = false;


    //Verwaltungsklassen
    Benutzerverwaltung benver;
    Behaelterverwaltung behver;
    Einstellungenverwaltung einver;
    GesundheitstippsVerwaltung gesver;

    Benutzer aktBenutzer;

    //Buttons auf der Hauptseite
    ImageButton shopping;//Einkaufswagen Button auf der Hauptseite
    ImageButton setting; //Einstellungen Button auf der Hauptseite
    ImageButton trophae;//Trophaen Button auf der Hauptseite
    ImageButton info;
    ImageButton tipps;
    ImageButton teich;
    ImageButton statistikButton;
    Button trinkKreis; // Kreis der sich dreht beim Trinken
    TextView trinkInfo;
    GifTextView fischStatusView;

    //Variable der Prozentanzeige
    int percentStatus = 0; //Variable der Prozentanzeige
    private Handler handler = new Handler(); // dient zum Aufrufen der Einstellungen des Kreises
    TextView trinkStatus; // Zeigt die Prozentzahl der Kreises im Kreis an.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hauptseite);

        Context context =  getApplicationContext();

        //Receiver für Benachrichtungen
        ComponentName receiver = new ComponentName(context, AlertReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        //Getränke einstellungen
        try {
            Intent intent = getIntent();
            Menge = intent.getStringExtra(Getraenkeauswahl.LITER_MESSAGE);
            GetraenkeTyp = intent.getStringExtra(Getraenkeauswahl.TYP_MESSAGE);

            if (Menge==null) Menge="250";
            if (GetraenkeTyp==null) GetraenkeTyp="Wasser";
            AktiverBehaelter = Menge + "ml" + GetraenkeTyp;
            trinkInfobutton = GetraenkeTyp+" : "+Menge + "ml";
        } catch (Exception e) {
            System.out.println("Noch kein Getraenk ausgewaehlt!");
        }


        benver = Benutzerverwaltung.getInstance(getApplicationContext());
        behver = Behaelterverwaltung.getInstance(getApplicationContext());
        einver = Einstellungenverwaltung.getInstance(getApplicationContext());
        gesver = GesundheitstippsVerwaltung.getInstance(getApplicationContext());

        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate); // Lässt den Kreis beim Trinken rotieren
        shopping = (ImageButton) findViewById(R.id.shoppingButton); // initialisierung des shopping Buttons
        setting = (ImageButton) findViewById(R.id.settingsButton);  // initialisierung des settings Buttons
        trophae = (ImageButton) findViewById(R.id.trophaeButton);  // initialisierung des Stern Buttons
        trinkKreis = (Button) findViewById(R.id.trinkenKreis);  // initialisierung des Trinktreises der sich beim lange drücken dreht
        info = (ImageButton) findViewById(R.id.infoButton);
        tipps = (ImageButton) findViewById(R.id.tippsButton);
        teich = (ImageButton) findViewById(R.id.teichButton);
        trinkInfo = (TextView)findViewById(R.id.trinkInfoText);
        trinkInfo.setText(trinkInfobutton);
        statistikButton = (ImageButton) findViewById(R.id.erfolgeButton);
        fischStatusView = (GifTextView) findViewById(R.id.gifTextView);

        shopping.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        setting.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        trophae.setOnClickListener(this); // listerner aktivieren damit die Button wissen wenn sie gedrückt werden
        trinkKreis.setOnClickListener(this);
        info.setOnClickListener(this);
        tipps.setOnClickListener(this);
        teich.setOnClickListener(this);
        statistikButton.setOnClickListener(this);
        fischStatusView.setOnClickListener(this);

        //Kreis mit Prozentanzeige
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar); // initialisierung des Kreises

        int percentage;

        try {
            percentage = (int) ((benver.getheutigenStatus().getTagesIstMenge() / benver.getheutigenStatus().getTagesSollMenge()) * 100);
        } catch (Exception e) {
            percentage = 0;
            System.out.println("Heutigen Status setzen!");
        }
        mProgress.setProgress(percentage);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);


        trinkStatus = (TextView) findViewById(R.id.trinkStatus);
        trinkStatus.setText(percentage + "%");

        trinkKreis.setOnLongClickListener(new View.OnLongClickListener() { // Listener der sofort die Rotationsbewegung ausführt wenn er lange gedrückt wird
            @Override
            public boolean onLongClick(View arg0) {
                arg0.startAnimation(animRotate);

                try {
                    benver.getraenkTrinken(behver.getBehaeltnisByName(AktiverBehaelter));
                } catch (Exception e) {
                    System.out.println("Trinken geht nicht. Administrator regelt das ;-)");
                }

                try {
                    percentStatus = (int) ((benver.getheutigenStatus().getTagesIstMenge()/benver.getheutigenStatus().getTagesSollMenge())*100);
                } catch (Exception e) {
                    System.out.println("ADMIN!!!!");
                }
                mProgress.setProgress(percentStatus);
                trinkStatus.setText(percentStatus + "%");

                //Zur schöneren Darstellung
                NumberFormat n = NumberFormat.getInstance();
                n.setMaximumFractionDigits(2);

                double effektiv;

                try {
                    effektiv = behver.getBehaeltnisByName(AktiverBehaelter).getEffektiveTrinkmenge();
                } catch (Exception e) {
                    System.out.println("Effektiv geht nicht. Administrator regelt das ;-)");
                    effektiv = 0.33; //per default
                }

                //Pop-Up
                String TrinkMessage = "Du hast soeben ein Getränk mit " + n.format(effektiv)
                + " Liter reinem Wasser getrunken! \n\nTagesstand: " + n.format(benver.getheutigenStatus().getTagesIstMenge()) +
                        "/" + n.format(benver.getheutigenStatus().getTagesSollMenge()) + " Liter";
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
            einver.initialisiere();
            benver.initialisiere();
            behver.initialisiere();
            gesver.initialisiere();
        }catch (Exception e){
            e.printStackTrace();
        }

        //Begrueßungstext
        checkFirstRun();

    }//oncreate

    //Diese Funktion checkt ob der User die App gerade das erste mal geöffnet hat und zeigt ihm ein Tutorial
    public void checkFirstRun() {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun){
            // Place your dialog code here to display the dialog

             //Pop-Up
            String HelloMessage = "Ziehe einen Fisch groß indem du regelmäßig genug trinkst." +
                    "\nTrinkst Du zu wenig, sinkt der Wasserstand im Goldfisch Glas und das gilt " +
                    "es zu vermeiden." +
                    "\nDrücke kurz auf das Glas um Behältergröße und Getränketyp festzulegen." +
                    "\nDrückst Du lang auf das Glas wird die effektive Wassermenge (je nach Getränketyp) " +
                    "dem aktuellen Tagesstand hinzugefügt." +
                    "\nUm deinen empfohlenen Tagesbedarf ausrechnen zu können gib bitte dein Gewicht " +
                    "und deine Körpergröße in den Einstellungen an. " +
                    "Keine Sorge - wir speichern keine personenbezogenen Daten ;-)" +
                    "\n\nViel Spaß mit deinem neuen Haustier wünscht Dir dein Goldfisch-Team!";

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Herzlich Willkommen bei Goldfisch!");
            alertDialog.setMessage(HelloMessage);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            //Intent zur Übertragung der Daten an den Endscreen
                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);
                        }
                    });
            alertDialog.show();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

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


    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.shoppingButton:
                    Intent fishIntent = new Intent(getApplicationContext(), FishShop.class);
                    startActivity(fishIntent);
                    break;

                case R.id.erfolgeButton:
                    Intent statistikIntent = new Intent(getApplicationContext(), Statistik.class);
                    startActivity(statistikIntent);
                    break;

                case R.id.settingsButton:
                    //Intent zur Übertragung der Daten an den Endscreen
                    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.teichButton:

                    Intent teichIntent = new Intent(getApplicationContext(), FischTeich.class);
                    startActivity(teichIntent);
                    break;

                case R.id.tippsButton:

                    Intent tippsIntent = new Intent(getApplicationContext(), Tipps.class);
                    startActivity(tippsIntent);

                    break;

                case R.id.infoButton:

                    //Pop-Up
                    String InfoMessage = "Drücke kurz auf das Glas um Behältergröße und Getränketyp festzulegen." +
                            "\nDrückst du lang auf das Glas wird die effektive Wassermenge (je nach Getränketyp) " +
                            "dem aktuellen Tagesstand hinzugefügt." +
                            "\nUm deinen empfohlenen Tagesbedarf ausrechnen zu können gib bitte dein Gewicht " +
                            "und deine Körpergröße in den Einstellungen an. " +
                            "\nKeine Sorge - wir speichern keine personenbezogenen Daten ;-)";

                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Du brauchst Hilfe?");
                    alertDialog.setMessage(InfoMessage);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    break;

                case R.id.trophaeButton:
                    Intent trophaeIntent = new Intent(getApplicationContext(), Erfolge.class);
                    startActivity(trophaeIntent);
                    break;

                case R.id.trinkenKreis:
                    //getraenkeseite ist zustaendig für die mengeneinheiten und beinhaltet radiobuttons
                    //setContentView(R.layout.getraenkeseite);
                    Intent trinkIntent = new Intent(getApplicationContext(), Getraenkeauswahl.class);
                    startActivity(trinkIntent);
                    break;

                case R.id.gifTextView:

                    //setContentView(R.layout.getraenkeseite);
                    Intent FischStatusIntent = new Intent(getApplicationContext(), FishStatus.class);
                    startActivity(FischStatusIntent);
                    break;
            }


    }
}
