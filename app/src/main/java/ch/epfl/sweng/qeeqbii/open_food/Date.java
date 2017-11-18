package ch.epfl.sweng.qeeqbii.open_food;

import java.util.Calendar;

/**
 * Created by davidcleres on 13.11.17.
 */

public class Date {

    private int mScanningDay;
    private int mScanningMonth;
    private int mScanningYear;

    public Date() {
        Calendar calendar = Calendar.getInstance();
        mScanningDay = calendar.get(Calendar.DAY_OF_MONTH);
        mScanningMonth = calendar.get(Calendar.MONTH);
        mScanningDay = calendar.get(Calendar.YEAR);
    }

    public int getScanningDay() {return mScanningDay; }

    public int getScanningMonth() {return mScanningMonth; }

    public int getScanningYear() {return mScanningYear; }
}
