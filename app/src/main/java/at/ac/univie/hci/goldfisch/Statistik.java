package at.ac.univie.hci.goldfisch;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class Statistik extends AppCompatActivity implements View.OnClickListener {


    BarChart barchart;
    ImageButton home_statistik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistik);
        //Setting the Data per Days
        barchart = (BarChart) findViewById(R.id.bargrapf);


        ArrayList<BarEntry> theAmount = new ArrayList<>();
        theAmount.add(new BarEntry(3000f, 0));
        theAmount.add(new BarEntry(2200f, 1));
        theAmount.add(new BarEntry(2500f, 2));
        theAmount.add(new BarEntry(1200f, 3));
        theAmount.add(new BarEntry(200f, 4));
        theAmount.add(new BarEntry(3000f, 5));
        theAmount.add(new BarEntry(2100f, 6));
        BarDataSet barDataSet = new BarDataSet(theAmount, "Menge in ml");
        barDataSet.setColor(Color.rgb(110, 149, 234));

        ArrayList<String> theDays = new ArrayList<>();
        theDays.add("Montag");
        theDays.add("Dienstag");
        theDays.add("Mittwoch");
        theDays.add("Donnerstag");
        theDays.add("Freitag");
        theDays.add("Samstag");
        theDays.add("Sonntag");

        //setting the graphs
        BarData theData = new BarData(theDays, barDataSet);
        barchart.setData(theData);
        barchart.setTouchEnabled(true);
        barchart.setDragEnabled(true);
        barchart.setScaleEnabled(true);
        barchart.setScaleXEnabled(true);
        barchart.setDescription("");
        //setting of the legend under the graphics
        Legend legend = barchart.getLegend();
        legend.setTextColor(Color.BLACK);
        legend.setTextSize(12f);
        legend.setFormSize(10f);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

    }
    //homebutton back to the .MainActiity
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.homebuttonStatistik:
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                break;

        }

    }

}