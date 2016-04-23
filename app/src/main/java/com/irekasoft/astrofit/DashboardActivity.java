package com.irekasoft.astrofit;

import android.app.Activity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DashboardActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dashboard);

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
}
