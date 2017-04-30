package at.ac.univie.hci.goldfisch.regenbogengoldfisch;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import at.ac.univie.hci.goldfisch.regenbogengoldfisch.dao.GesundheitsDAO;
import at.ac.univie.hci.goldfisch.regenbogengoldfisch.dao.GesundheitsDAOImpl;
import at.ac.univie.hci.goldfisch.regenbogengoldfisch.model.Gesundheitstipp;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private EditText txt_ueberschrift;
    private EditText txt_tipp;
    private TextView txt_ausgabe;
    private TextView txt_helloWorld;
    private TextView lbl_ueberschrift;
    private Button btn_speichern;
    private Button btn_getSavedData;
    private GesundheitsDAO gesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        lbl_ueberschrift = (TextView) findViewById(R.id.lbl_ueberschrift);
        txt_ueberschrift = (EditText) findViewById(R.id.txt_ueberschrift);
        txt_tipp = (EditText) findViewById(R.id.txt_tipp);
        txt_ausgabe = (TextView) findViewById(R.id.lbl_ausgabe);
        txt_helloWorld = (TextView) findViewById(R.id.txt_helloWorld);
        btn_speichern = (Button) findViewById(R.id.btn_speichern);
        btn_getSavedData = (Button) findViewById(R.id.btn_getSavedData);

        try {
            this.gesDao = new GesundheitsDAOImpl(getApplicationContext(), "gesundheitstipps.dat");
            //this.gesDao.saveTipp(new Gesundheitstipp("ich","du"));
            System.out.println("Tipps: ");
            for(Gesundheitstipp t : this.gesDao.getTipps()){
                System.out.println(t);
            }

        }catch (Exception e){
            System.err.println("Fehler beim erstellen des GesundheitsDaos!!");
            e.printStackTrace();
        }



        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private static String getFileContent(FileInputStream fis) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
    }

    public void speichereTipps(View view){
        setContentView(R.layout.content_main);
        System.out.println("Speichere Tipps: ");

        try {
            Gesundheitstipp t = new Gesundheitstipp("heading","tipp");
            System.out.println("Probiere zu speichern:  " + txt_ueberschrift.getText().toString() + ": "+ txt_tipp.getText().toString());
            gesDao.saveTipp(t);
            System.out.println("gespeichert");
        }catch (Exception e){
            System.err.println("Fehler beim Speichern des neuen Tipps");
        }
    }

    public void holeTipps(View view){
        StringBuffer zwischen = new StringBuffer();
        try{
            for(Gesundheitstipp t : gesDao.getTipps()){
                System.out.println(t);
                zwischen = zwischen.append(t.getTipp());
            }
            System.out.println("Alle tipps: "+zwischen.toString());
            txt_ausgabe.setText(zwischen.toString());
        }catch (Exception e){
            System.err.println("Fehler beim Holen der Tipps");
        }
    }

}
