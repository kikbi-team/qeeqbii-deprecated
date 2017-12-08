package ch.epfl.sweng.qeeqbii.shopping_cart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.activities.StatisticsActivity;
import ch.epfl.sweng.qeeqbii.custom_exceptions.ProductException;
import ch.epfl.sweng.qeeqbii.open_food.Product;


public class BarChartMonthlyFrag extends SimpleFragment implements OnChartGestureListener {

    public static Fragment newInstance() {
        return new BarChartMonthlyFrag();
    }

    private BarChart mChart;
    private PieChart mChartPie;

    private List<Float> mSalts = new ArrayList<>();
    private List<Float> mGlucides = new ArrayList<>();
    private List<Float> mFats = new ArrayList<>();
    private List<Float> mCalories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1_statistics_month, container, false);

        //FIRST GRAPH
        mChartPie = (PieChart) v.findViewById(R.id.idPieChartTabMonth);
        mChartPie.getDescription().setEnabled(false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        mChartPie.setCenterTextTypeface(tf);
        mChartPie.setCenterText(generateCenterText());
        mChartPie.setCenterTextSize(10f);
        mChartPie.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mChartPie.setHoleRadius(45f);
        mChartPie.setTransparentCircleRadius(50f);

        Legend l = mChartPie.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        mChartPie.setData(generatePieData());

        // create a new chart object
        mChart = new BarChart(getActivity());
        mChart.getDescription().setEnabled(false);
        mChart.setOnChartGestureListener(this);

        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv);

        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        Typeface tfBars = Typeface.createFromAsset(getActivity().getAssets(),"OpenSans-Light.ttf");

        //FOR THE FUTURE
        /*
        List<BarEntry> entriesFats = new ArrayList<>();
        List<BarEntry> entriesSalts = new ArrayList<>();
        List<BarEntry> entriesGlucides = new ArrayList<>();
        List<BarEntry> entriesCalories = new ArrayList<>();
        */

        List<BarEntry> entries = new ArrayList<>();

        //GIVES THE VALUES FOR THE GRAPHS
        try {
            fillLists();
        } catch (ProductException e) {
            e.printStackTrace();
        }

        // FUTURE
        /*
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 30f));
        entries.add(new BarEntry(2f, 30f));
        entries.add(new BarEntry(3f, 30f));
        */

        entries.add(new BarEntry(0f, sumList(mCalories)));
        entries.add(new BarEntry(2f, sumList(mFats)));
        entries.add(new BarEntry(4f, sumList(mGlucides)));
        entries.add(new BarEntry(6f, sumList(mSalts)));

        BarDataSet set = new BarDataSet(entries, "Nutrients Intake over the last Month");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        mChart.setData(data);
        mChart.setFitBars(true); // make the x-axis fit exactly all bars
        mChart.invalidate(); // refresh

        //mChart.setData(generateBarData(1, 20000, 12)); //If you want to change this file you have to
        //take the files from git hub and to change it locally here to get a better graph.

        Legend legend = mChart.getLegend();
        legend.setTypeface(tfBars);
        //legend.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "Set1", "Set2", "Set3", "Set4" });

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(false);

        //programatically add the chart
        FrameLayout parent = (FrameLayout) v.findViewById(R.id.barChartMonth);
        parent.addView(mChart);

        return v;
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START");
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END");
        mChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Past\nMonth");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

    //THE list cannot be empty
    private void fillLists() throws ProductException {
        if(!StatisticsActivity.m_items_month.isEmpty())
        {
            for (Product element : StatisticsActivity.m_items_month)
            {
                Map<String,Double> nutrients = element.getParsedNutrients();

                if (nutrients.containsKey("Énergie (kCal)"))
                {
                    mCalories.add(nutrients.get("Énergie (kCal)").floatValue());
                }
                if (nutrients.containsKey("Matières grasses (g)"))
                {
                    mFats.add(nutrients.get("Matières grasses (g)").floatValue());
                }
                if (nutrients.containsKey("Sucres (g)"))
                {
                    mGlucides.add(nutrients.get("Sucres (g)").floatValue());
                }
                if (nutrients.containsKey("Sel (g)")) {
                    mSalts.add(nutrients.get("Sel (g)").floatValue());
                }
            }
        }
        else {
            //In case the list is empty at the beginning by doing so we have not iterator on empyty list issue
            //CHANGE TO ZERO AGAIN !!!
            mSalts.add(3f);
            mGlucides.add(8f);
            mFats.add(4f);
            mCalories.add(5f);
        }
    }

    //Returns the sum of all the elements in the list
    private Float sumList(List<Float> list) {
        if (list.isEmpty()) {
            return 0f;
        }

        float sum = 0;
        for (float element : list) {
            sum += element;
        }
        return sum;
    }
}