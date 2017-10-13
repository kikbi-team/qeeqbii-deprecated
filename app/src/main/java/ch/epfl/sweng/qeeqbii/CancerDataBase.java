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
            for (int i = 0; i < msubstance_list.size(); i = i + 1) {
                output += " " + Integer.toString(i);
                output += "\t" + msubstance_list.get(i).getmAgent();
                output += "\t\t\t" + msubstance_list.get(i).getmGroup() + "\n";
            }
            return output;
        }
        else {
            throw new Exception("Read the carcinogenic database before trying to print it.\n");
        }
    }
}
