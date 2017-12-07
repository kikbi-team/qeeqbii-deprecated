package ch.epfl.sweng.qeeqbii.open_food;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by davidcleres on 13.11.17.
 */



public class Date implements Serializable {

    private int mScanningDay;
    private int mScanningMonth;
    private int mScanningYear;

    public Date() {
        Calendar calendar = Calendar.getInstance();
        mScanningDay = calendar.get(Calendar.DAY_OF_MONTH);
        mScanningMonth = calendar.get(Calendar.MONTH) +1;
        mScanningYear = calendar.get(Calendar.YEAR);
    }

    public Date(int day, int month, int year) {
        mScanningDay = day;
        mScanningMonth = month;
        mScanningYear = year;
    }

    public String toString() {
        return mScanningDay + "/" + mScanningMonth + "/" + mScanningYear;
    }

    public Date(String date) {
        String[] parsed_date = date.split("/");
        mScanningDay = Integer.valueOf(parsed_date[0]);
        mScanningMonth = Integer.valueOf(parsed_date[1]);
        mScanningYear = Integer.valueOf(parsed_date[2]);
    }

    @Override
    public boolean equals(Object o) {
        try {
            Date date_o = (Date) o;
            return ((date_o.mScanningDay == mScanningDay) & (date_o.mScanningMonth == mScanningMonth) &
                    (date_o.mScanningYear == mScanningYear));
        } catch (Exception e) {
            return false;
        }
    }

    public int getScanningDay() {
        return mScanningDay;
    }

    public int getScanningMonth() {
        return mScanningMonth;
    }

    public int getScanningYear() {
        return mScanningYear;
    }

    public Boolean isBefore(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date date_to_compare = formatter.parse(date.toString());
            java.util.Date current_date = formatter.parse(toString());

            return (current_date.before(date_to_compare) | current_date.equals(date_to_compare));
        } catch(ParseException e)
        {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public Boolean isAfter(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date date_after = formatter.parse(date.toString());
            java.util.Date current_date = formatter.parse(toString());

            return (current_date.after(date_after) | current_date.equals(date_after));
        } catch(ParseException e)
        {
            System.err.println(e.getMessage());
        }
        return false;
    }
}