package ch.epfl.sweng.qeeqbii.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ch.epfl.sweng.qeeqbii.R;



public class ProductComparisonActivity extends AppCompatActivity {

    public class ProductsLine {
        public String criteria;
        public Double value1;
        public Double value2;
        ProductsLine(String criteria_, Double val1, Double val2) {
            criteria = criteria_;
            value1 = val1;
            value2 = val2;
        }
    }

    public class LabelFormatter implements IAxisValueFormatter {
        private final ArrayList<String> mLabels;

        public LabelFormatter(ArrayList<String> labels) {
            mLabels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int idx = (int) value;
            if(mLabels != null && (float) idx - value < 1e-2)
                return mLabels.get(idx);
            else return "";
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_comparison);

        HorizontalBarChart mChart = (HorizontalBarChart) findViewById(R.id.chart1);
        setData(mChart, new ProductsLine("Salt (g)", 100., 200.));
    }


    private void setData(HorizontalBarChart chart, ProductsLine line) {
        ArrayList<ProductsLine> lines = new ArrayList<ProductsLine>();
        lines.add(line);
        setData(chart, lines);
        chart.getDescription().setText(line.criteria);
    }

    private void setData(HorizontalBarChart chart, ArrayList<ProductsLine> lines) {

        chart.getXAxis().setPosition(XAxis.XAxisPosition.TOP);
        chart.getDescription().setText("");

        chart.getAxisLeft().setAxisMinimum(0);

        BarData data = new BarData();
        chart.setData(data);

        final int bars_per_line = 3;
        final int line_n = lines.size();

        ArrayList<String> labels = new ArrayList<String>();
        for(int i = 0; i < line_n * bars_per_line; i++)
            labels.add("");

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();

        int i = 0;
        for(ProductsLine line: lines) {
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
    }

    public void scanBarcode(View w) {
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        startActivity(intent);
    }
}
