package ch.epfl.sweng.qeeqbii.shopping_cart;

/**
 * Created by davidcleres on 13.11.17.
 */

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.activities.GraphsActivity;

import static android.content.ContentValues.TAG;

public class Tab1MonthlyStatistics extends Fragment {

    private PieChart pieChart;
    private final float[] yDataCalories = {0, 2000};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1_shopping_cart_statistics, container, false);
        //Intent intent = new Intent(getActivity(), GraphsActivity.class);
        //startActivity(intent);

        createGraph(R.id.idPieChartTab, getString(R.string.calories_graph));

        return rootView;
    }

    private void addDataSet(String nameGraph, float[] yData) {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        //ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        /*for (int i = 1; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }*/

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, nameGraph + " in 100g / daily needs");
        pieDataSet.setSliceSpace(0); //sets the size of the yEntrys on the graph
        pieDataSet.setValueTextSize(0);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.GRAY);
        /*colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);*/

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);

        Description description = pieChart.getDescription();
        float percentage = yData[0]/yData[1]*100;
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String str_per = numberFormat.format(percentage);
        description.setText( str_per + "% of your daily need in " + nameGraph + ".          ");

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        //pieChart.invalidate();
        pieChart.setEnabled(true);
    }

    private void createGraph(final int numberChart, final String nameGraph) {

        pieChart = (PieChart) getActivity().findViewById(numberChart);
        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(80f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText(nameGraph);
        pieChart.setCenterTextSize(13);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        float[] yData = {0, 1};
        if (numberChart == R.id.idPieChartTab) yData = yDataCalories;

        final float[] final_yData = yData;

        addDataSet(nameGraph,final_yData);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("(sum): ");
                String sales = e.toString().substring(pos1 + 7);

                for (int i = 0; i < final_yData.length; i++) {
                    if (final_yData[i] == Float.parseFloat(sales)) {
                        pos1 = i;
                        break;
                    }
                }
                //String employee = xData[pos1 + 1];
                //Toast.makeText(GraphsActivity.this, "Employee " + employee + "\n" + "Sales: $" + sales + "K", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }
}