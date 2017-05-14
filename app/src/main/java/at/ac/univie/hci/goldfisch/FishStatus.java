package at.ac.univie.hci.goldfisch;


        import android.content.Intent;
        import android.media.Image;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import java.text.NumberFormat;

        import at.ac.univie.hci.goldfisch.management.Benutzerverwaltung;



public class FishStatus extends AppCompatActivity implements View.OnClickListener{


    ImageButton homeFishStatus;
    TextView textInfoFishStatus;
    double fishLvl=0;
    Benutzerverwaltung benver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fishstatus);


        homeFishStatus = (ImageButton)findViewById(R.id.homeButtonFishStatus);
        homeFishStatus.setOnClickListener(this);

        textInfoFishStatus = (TextView)findViewById(R.id.FishstatusInfoText);
        final ProgressBar fishstatusProgress = (ProgressBar)findViewById(R.id.FishStatusBar);
        try {
            benver = Benutzerverwaltung.getInstance(getApplicationContext());
            fishLvl = ((benver.getheutigenStatus().getTagesIstMenge()));
        } catch (Exception e) {
            fishLvl = 0;
        }

        //Zur schöneren Darstellung
        NumberFormat n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(2);

        fishstatusProgress.setProgress(20);   // hardcode

        textInfoFishStatus.setText("Du hast bereits "+n.format(fishLvl) + " Liter Wasser getrunken. Super weiter so!  Trinke jeden Tag regelmäßig, um dein Fischlevel zu erhöhen.");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.homeButtonFishStatus:
                Intent backHomeIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backHomeIntent);
                break;
        }
    }
}
