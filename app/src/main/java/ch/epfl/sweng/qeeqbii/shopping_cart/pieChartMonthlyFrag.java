package ch.epfl.sweng.qeeqbii.shopping_cart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import ch.epfl.sweng.qeeqbii.R;


public class pieChartMonthlyFrag extends SimpleFragment {

    public static Fragment newInstance() {
        return new pieChartMonthlyFrag();
    }

    private PieChart mChart;
    private PieChart mChart2;
    private BarChart mChart3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1_shopping_cart_statistics, container, false);

        //FIRST GRAPH
        mChart = (PieChart) v.findViewById(R.id.idPieChartTabMonth);
        mChart.getDescription().setEnabled(false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        mChart.setCenterTextTypeface(tf);
        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);
        mChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        mChart.setData(generatePieData());

        //SECOND GRAPH
        mChart2 = (PieChart) v.findViewById(R.id.pieChart);
        mChart2.getDescription().setEnabled(false);

        Typeface tf2 = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        mChart2.setCenterTextTypeface(tf2);
        mChart2.setCenterText(generateCenterText());
        mChart2.setCenterTextSize(10f);
        mChart2.setCenterTextTypeface(tf2);

        // radius of the center hole in percent of maximum radius
        mChart2.setHoleRadius(45f);
        mChart2.setTransparentCircleRadius(50f);

        Legend l2 = mChart2.getLegend();
        l2.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l2.setOrientation(Legend.LegendOrientation.VERTICAL);
        l2.setDrawInside(false);

        mChart2.setData(generatePieData());

        return v;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Past\nMonth");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }
}