package ch.epfl.sweng.qeeqbii.charting.interfaces.dataprovider;

import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.interfaces.dataprovider.*;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.BubbleDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;

/**
 * Created by philipp on 11/06/16.
 */
public interface CombinedDataProvider extends com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider, BarDataProvider, BubbleDataProvider, CandleDataProvider, ScatterDataProvider {

    CombinedData getCombinedData();
}
