
package ch.epfl.sweng.qeeqbii.charting.data;

import android.util.Log;

import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Data object that allows the combination of Line-, Bar-, Scatter-, Bubble- and
 * CandleData. Used in the CombinedChart class.
 *
 * @author Philipp Jahoda
 */
public class CombinedData extends BarLineScatterCandleBubbleData<IBarLineScatterCandleBubbleDataSet<? extends com.github.mikephil.charting.data.Entry>> {

    private com.github.mikephil.charting.data.LineData mLineData;
    private com.github.mikephil.charting.data.BarData mBarData;
    private com.github.mikephil.charting.data.ScatterData mScatterData;
    private com.github.mikephil.charting.data.CandleData mCandleData;
    private com.github.mikephil.charting.data.BubbleData mBubbleData;

    public CombinedData() {
        super();
    }

    public void setData(com.github.mikephil.charting.data.LineData data) {
        mLineData = data;
        notifyDataChanged();
    }

    public void setData(com.github.mikephil.charting.data.BarData data) {
        mBarData = data;
        notifyDataChanged();
    }

    public void setData(com.github.mikephil.charting.data.ScatterData data) {
        mScatterData = data;
        notifyDataChanged();
    }

    public void setData(com.github.mikephil.charting.data.CandleData data) {
        mCandleData = data;
        notifyDataChanged();
    }

    public void setData(com.github.mikephil.charting.data.BubbleData data) {
        mBubbleData = data;
        notifyDataChanged();
    }

    @Override
    public void calcMinMax() {

        if(mDataSets == null){
            mDataSets = new ArrayList<>();
        }
        mDataSets.clear();

        mYMax = -Float.MAX_VALUE;
        mYMin = Float.MAX_VALUE;
        mXMax = -Float.MAX_VALUE;
        mXMin = Float.MAX_VALUE;

        mLeftAxisMax = -Float.MAX_VALUE;
        mLeftAxisMin = Float.MAX_VALUE;
        mRightAxisMax = -Float.MAX_VALUE;
        mRightAxisMin = Float.MAX_VALUE;

        List<BarLineScatterCandleBubbleData> allData = getAllData();

        for (com.github.mikephil.charting.data.ChartData data : allData) {

            data.calcMinMax();

            List<IBarLineScatterCandleBubbleDataSet<? extends com.github.mikephil.charting.data.Entry>> sets = data.getDataSets();
            mDataSets.addAll(sets);

            if (data.getYMax() > mYMax)
                mYMax = data.getYMax();

            if (data.getYMin() < mYMin)
                mYMin = data.getYMin();

            if (data.getXMax() > mXMax)
                mXMax = data.getXMax();

            if (data.getXMin() < mXMin)
                mXMin = data.getXMin();

            if (data.mLeftAxisMax > mLeftAxisMax)
                mLeftAxisMax = data.mLeftAxisMax;

            if (data.mLeftAxisMin < mLeftAxisMin)
                mLeftAxisMin = data.mLeftAxisMin;

            if (data.mRightAxisMax > mRightAxisMax)
                mRightAxisMax = data.mRightAxisMax;

            if (data.mRightAxisMin < mRightAxisMin)
                mRightAxisMin = data.mRightAxisMin;

        }
    }

    public com.github.mikephil.charting.data.BubbleData getBubbleData() {
        return mBubbleData;
    }

    public LineData getLineData() {
        return mLineData;
    }

    public BarData getBarData() {
        return mBarData;
    }

    public ScatterData getScatterData() {
        return mScatterData;
    }

    public CandleData getCandleData() {
        return mCandleData;
    }

    /**
     * Returns all data objects in row: line-bar-scatter-candle-bubble if not null.
     *
     * @return
     */
    public List<BarLineScatterCandleBubbleData> getAllData() {

        List<BarLineScatterCandleBubbleData> data = new ArrayList<BarLineScatterCandleBubbleData>();
        if (mLineData != null)
            data.add(mLineData);
        if (mBarData != null)
            data.add(mBarData);
        if (mScatterData != null)
            data.add(mScatterData);
        if (mCandleData != null)
            data.add(mCandleData);
        if (mBubbleData != null)
            data.add(mBubbleData);

        return data;
    }

    public BarLineScatterCandleBubbleData getDataByIndex(int index) {
        return getAllData().get(index);
    }

    @Override
    public void notifyDataChanged() {
        if (mLineData != null)
            mLineData.notifyDataChanged();
        if (mBarData != null)
            mBarData.notifyDataChanged();
        if (mCandleData != null)
            mCandleData.notifyDataChanged();
        if (mScatterData != null)
            mScatterData.notifyDataChanged();
        if (mBubbleData != null)
            mBubbleData.notifyDataChanged();

        calcMinMax(); // recalculate everything
    }

    /**
     * Get the Entry for a corresponding highlight object
     *
     * @param highlight
     * @return the entry that is highlighted
     */
    @Override
    public com.github.mikephil.charting.data.Entry getEntryForHighlight(Highlight highlight) {

        if (highlight.getDataIndex() >= getAllData().size())
            return null;

        com.github.mikephil.charting.data.ChartData data = getDataByIndex(highlight.getDataIndex());

        if (highlight.getDataSetIndex() >= data.getDataSetCount())
            return null;

        // The value of the highlighted entry could be NaN -
        //   if we are not interested in highlighting a specific value.

        List<com.github.mikephil.charting.data.Entry> entries = data.getDataSetByIndex(highlight.getDataSetIndex())
                .getEntriesForXValue(highlight.getX());
        for (com.github.mikephil.charting.data.Entry entry : entries)
            if (entry.getY() == highlight.getY() ||
                    Float.isNaN(highlight.getY()))
                return entry;

        return null;
    }

    /**
     * Get dataset for highlight
     *
     * @param highlight current highlight
     * @return dataset related to highlight
     */
    public IBarLineScatterCandleBubbleDataSet<? extends com.github.mikephil.charting.data.Entry> getDataSetByHighlight(Highlight highlight) {
        if (highlight.getDataIndex() >= getAllData().size())
            return null;

        BarLineScatterCandleBubbleData data = getDataByIndex(highlight.getDataIndex());

        if (highlight.getDataSetIndex() >= data.getDataSetCount())
            return null;

        return (IBarLineScatterCandleBubbleDataSet<? extends com.github.mikephil.charting.data.Entry>)
                data.getDataSets().get(highlight.getDataSetIndex());
    }

    public int getDataIndex(com.github.mikephil.charting.data.ChartData data) {
        return getAllData().indexOf(data);
    }

    @Override
    public boolean removeDataSet(IBarLineScatterCandleBubbleDataSet<? extends com.github.mikephil.charting.data.Entry> d) {

        List<BarLineScatterCandleBubbleData> datas = getAllData();

        boolean success = false;

        for (ChartData data : datas) {

            success = data.removeDataSet(d);

            if (success) {
                break;
            }
        }

        return success;
    }

    @Deprecated
    @Override
    public boolean removeDataSet(int index) {
        Log.e("MPAndroidChart", "removeDataSet(int index) not supported for CombinedData");
        return false;
    }

    @Deprecated
    @Override
    public boolean removeEntry(Entry e, int dataSetIndex) {
        Log.e("MPAndroidChart", "removeEntry(...) not supported for CombinedData");
        return false;
    }

    @Deprecated
    @Override
    public boolean removeEntry(float xValue, int dataSetIndex) {
        Log.e("MPAndroidChart", "removeEntry(...) not supported for CombinedData");
        return false;
    }
}
