package ch.epfl.sweng.qeeqbii;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrien on 12/10/17.
 */

public class CancerDataBase {

    // Attributes
    private List<CancerSubstance> msubstance_list;

    // mopen_state takes value 0 if no file have been read and takes value 1 if readCSVfile() have
    // been called and succeeded in reading a CSV file
    private int mopen_state;

    public CancerDataBase() {
        // mopen_state defined to 0 at instantiation because no CSV files have been read
        mopen_state = 0;
        msubstance_list = new ArrayList<CancerSubstance>();
    }

    // Method that reads a CSVFile
    public void readCSVFile(Context context) throws Exception {
        if (mopen_state == 0) {
            Resources resources = context.getResources();
            InputStream inStream = resources.openRawResource(R.raw.original_classification_iarc_english);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, Charset.forName("UTF-8")));

            String line = null;
            // Step over the headers
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                //split by ','
                String[] tokens = line.split(",");

                //read the data
                CancerSubstance new_substance = new CancerSubstance();
                new_substance.setmId(Integer.parseInt(tokens[0]));
                new_substance.setmAgent(tokens[1]); //Here we can add the fact that the dataBase might have leaks
                new_substance.setmGroup(tokens[2]);
                //Note if we want to import an int or a double we have to write:
                //sample.setmGroup(Double.parseDouble(tokens[x]));
                //sample.setmGroup(Integer.parseInt(tokens[x]));
                msubstance_list.add(new_substance); //adds the sample to the table with all the data
                //Log.d("MainActivity", "Just created: " + new_substance); //d stands for debugg
            }

            // Change the mopen_state to value 1 because the file is read
            mopen_state = 1;
        }
        else {
            throw new Exception("Tried to open carcinogenic database whereas it's already done.\n");
        }
    }

    // Method that outputs a string containing a summary of all the substances and their categorization
    public String sendOutputReadyToPrint() throws Exception {
        if (mopen_state == 1) {
            String output = " id\tAgent\t\tGroup\n";
            for (int i = 0; i < msubstance_list.size(); i++) {
                output += msubstance_list.get(i).toString();
                output += "\n";
            }
            return output;
        }
        else {
            throw new Exception("Read the carcinogenic database before trying to print it.\n");
        }
    }

    // Method that queries a substance of the DataBase and outputs
    // the substance and its group classification if the substance is found
    // or an empty CancerSubstance object if the queried substance didn't match perfectly
    // with a substance of the database
    public CancerSubstance perfectMatchQuery(String queried_substance) throws Exception {
        String[] output = new String[3];
        if (mopen_state == 1) {
            for (int i = 0; i < msubstance_list.size(); i++) {
                if (msubstance_list.get(i).getmAgent() == queried_substance) {
                    return msubstance_list.get(i);
                }
            }
            // Returning an empty CancerSubstance that is thrown if no perfect match have been found
            CancerSubstance empty_substance = new CancerSubstance();
            return empty_substance;
        }
        else {
            throw new Exception("Read the carcinogenic database before trying to query it.\n");
        }
    }
}
