package com.irekasoft.astrofit;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Timer;

public class DashboardActivity extends Activity {

  private long elapsedTimeBeforePause;
  private long interval;
  Button btn_start;
  boolean isRunning;
  Chronometer stopWatch;
  long startTime;
  long countUp;
  TextView tv_timer;



  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dashboard);

    tv_timer = (TextView) findViewById(R.id.countUp);
    btn_start = (Button)findViewById(R.id.btn_start);

    PieChart pieChart = (PieChart) findViewById(R.id.chart);
    // creating data values
    ArrayList<Entry> entries = new ArrayList<>();
    entries.add(new Entry(12, 0));
    entries.add(new Entry(13, 1));


    PieDataSet dataset = new PieDataSet(entries, "# of Calls");

    // creating labels
    ArrayList<String> labels = new ArrayList<String>();
    labels.add("1");
    labels.add("2");


    PieData data = new PieData(labels, dataset); // initialize Piedata
    pieChart.setData(data); //set data into chart

    dataset.setColors(ColorTemplate.COLORFUL_COLORS);


  }


  public void start(View v){


    if (isRunning == false){
      btn_start.setText("Pause Activity");
      isRunning = true;

      stopWatch = (Chronometer) findViewById(R.id.chrono);

//      startTime = SystemClock.elapsedRealtime();

      startTime = SystemClock.currentThreadTimeMillis();

      stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
        @Override
        public void onChronometerTick(Chronometer arg0) {

          countUp = (SystemClock.elapsedRealtime() - arg0.getBase()) / 1000;

          int second = (int) (countUp % 60);
          int minute = (int) (countUp / 60);

          String asText = stringFromNumber(minute) + ":" + stringFromNumber(second);
          tv_timer.setText(asText);

        }
      });

      stopWatch.start();


    }else{
      btn_start.setText("Resume Activity");
      stopWatch.stop();
      isRunning = false;
    }


  }

  public String stringFromNumber(int number){
    String result = "";
    if (number < 10){
      result = "0"+number;
    }else{
      result = ""+number;
    }
    return result;
  }

  public void stop(View v){

    AlertDialog myAlert=
        new AlertDialog.Builder(this)
            .setTitle("Are you sure to stop Activity")
            .setPositiveButton("OK",
                new DialogInterface.OnClickListener(){
                  @Override
                  public void onClick(
                      DialogInterface dialog, int which) {
                    //do something as OK button clicked
                    isRunning = false;
                    btn_start.setText("Start Activity");
                    tv_timer.setText("00:00");
                    stopWatch.stop();
                  }
                })
            .setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                  @Override
                  public void onClick(
                      DialogInterface dialog, int which) {

                  }
                })
            .create();
    myAlert.show();

  }




}
