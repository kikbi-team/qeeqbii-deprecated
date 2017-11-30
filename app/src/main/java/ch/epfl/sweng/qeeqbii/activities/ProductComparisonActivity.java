package ch.epfl.sweng.qeeqbii.activities;

/*
Created by sergei on 30 Nov 2017

This class implements a product comparison activity
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.activities.comparison.ComparisonGraphAdapter;
import ch.epfl.sweng.qeeqbii.activities.comparison.ProductsLine;


public class ProductComparisonActivity extends AppCompatActivity {

    private ComparisonGraphAdapter adapter = null;

    // add data to a bar chart
    public static void setData(HorizontalBarChart chart, ProductsLine line) {
        ArrayList<ProductsLine> lines = new ArrayList<ProductsLine>();
        lines.add(line);
        setData(chart, lines);
    }

    // add multiple lines to a bar chart
    private static void setData(HorizontalBarChart chart, ArrayList<ProductsLine> lines) {
        chart.getXAxis().setPosition(XAxis.XAxisPosition.TOP);
        chart.getDescription().setText("");

        chart.getAxisLeft().setAxisMinimum(0);

        BarData data = new BarData();
        chart.setData(data);

        final int bars_per_line = 3;
        final int line_n = lines.size();

        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i < line_n * bars_per_line; i++)
            labels.add("");

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();

        int i = 0;
        for (ProductsLine line : lines) {
            valueSet1.add(new BarEntry(i * bars_per_line, line.value1.floatValue()));
            valueSet2.add(new BarEntry(i * bars_per_line + 1, line.value2.floatValue()));
            labels.set(i * bars_per_line, line.criteria);
            i += 1;
        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Product 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Product 2");
        barDataSet1.setColor(Color.rgb(155, 0, 0));

        chart.getData().addDataSet(barDataSet1);
        chart.getData().addDataSet(barDataSet2);

        LabelFormatter formatter = new LabelFormatter(line_n > 1 ? labels : null);
        chart.getXAxis().setValueFormatter(formatter);

        chart.setDrawGridBackground(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);

        if (line_n == 1)
            chart.getDescription().setText(lines.get(0).criteria);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_comparison);

        adapter = new ComparisonGraphAdapter();
        adapter.setContext(getApplicationContext());
        adapter.addLine(new ProductsLine("Salt (g)", 100., 200.));
        adapter.addLine(new ProductsLine("Energy (J)", 5000., 6000.));
        adapter.addLine(new ProductsLine("Salt (g)", 100., 200.));
        adapter.addLine(new ProductsLine("Energy (J)", 5000., 6000.));
        adapter.addLine(new ProductsLine("Salt (g)", 100., 200.));
        adapter.addLine(new ProductsLine("Energy (J)", 5000., 6000.));
        adapter.addLine(new ProductsLine("Salt (g)", 100., 200.));
        adapter.addLine(new ProductsLine("Energy (J)", 5000., 6000.));
        adapter.addLine(new ProductsLine("Salt (g)", 100., 200.));
        adapter.addLine(new ProductsLine("Energy (J)", 5000., 6000.));
        ListView list = (ListView) findViewById(R.id.graphs);
        list.setAdapter(adapter);

    }

    // scan barcode button handler
    public void scanBarcode(View w) {
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        startActivity(intent);
    }

    // formatter for axis which outputs pre-defined values
    public static class LabelFormatter implements IAxisValueFormatter {
        private final ArrayList<String> mLabels;

        public LabelFormatter(ArrayList<String> labels) {
            mLabels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int idx = (int) value;
            if (mLabels != null && (float) idx - value < 1e-2)
                return mLabels.get(idx);
            else return "";
        }
    }
}
