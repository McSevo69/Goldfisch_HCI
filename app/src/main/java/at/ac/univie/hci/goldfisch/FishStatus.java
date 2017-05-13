package at.ac.univie.hci.goldfisch;


        import android.content.Intent;
        import android.media.Image;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import at.ac.univie.hci.goldfisch.management.Benutzerverwaltung;



public class FishStatus extends AppCompatActivity implements View.OnClickListener{


    ImageButton homeFishStatus;
    TextView textInfoFishStatus;
    int fishLvl=20;
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
            fishLvl = (int) ((benver.getheutigenStatus().getTagesIstMenge()));
        } catch (Exception e) {
            fishLvl = 0;
        }

        fishstatusProgress.setProgress(fishLvl);   // Main Progress

        textInfoFishStatus.setText("Du hast bereits "+fishLvl + " Wasser getrunken dir fehlen noch wenige ml, um dein Tagesziel zu erreichen");

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
