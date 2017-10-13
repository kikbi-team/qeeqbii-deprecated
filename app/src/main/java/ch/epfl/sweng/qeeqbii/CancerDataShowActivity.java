package ch.epfl.sweng.qeeqbii;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CancerDataShowActivity extends AppCompatActivity {
    //CONSTRUCTOR
    public CancerDataShowActivity() {
        mIsTest = false;
    }
    private List<CancerSubstance> importedData = new ArrayList<>();
    private boolean mIsTest;

    //METHODS
    public void readCSVFile() {
        importedData.clear(); //adds the sample to the table with all the data
        InputStream inStream;
        if (mIsTest) {
             inStream = getResources().openRawResource(R.raw.test_text);
        }
        else {
             inStream = getResources().openRawResource(R.raw.original_classification_iarc_english);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, Charset.forName("UTF-8")));
        String line = null;

        try {
            reader.readLine(); // Step over the headers
            while ((line = reader.readLine()) != null) {
                //split by ','
                String[] tokens = line.split(",");
                //read the data
                CancerSubstance sample = new CancerSubstance();
                sample.setmLabel(Integer.parseInt(tokens[0]));
                sample.setmAgent(tokens[1]); //Here we can add the fact that the dataBase might have leaks
                sample.setmGroup(tokens[2]);
                //Note if we want to import an int or a double we have to write:
                //sample.setmGroup(Double.parseDouble(tokens[x]));
                //sample.setmGroup(Integer.parseInt(tokens[x]));
                importedData.add(sample); //adds the sample to the table with all the data
                Log.d("MainActivity","Just created: " + sample); //d stands for debugg
            }
        } catch (IOException error) {
            Log.wtf("MainActivity", "Error reading the data file in the .csv file" + line, error);
            error.printStackTrace();
        }
    }

    public List<CancerSubstance> getImportedData() {
        return importedData;
    }

    public List<String> getImportedDataString() {
        List<String> stringImportedData = new ArrayList<>();
        for (int i = 0; i < importedData.size(); i++) {
            stringImportedData.add(importedData.get(i).getmAgent());
            stringImportedData.add(importedData.get(i).getmGroup());
        }
        return stringImportedData;
    }

    public boolean ismIsTest() {return mIsTest;}
    public void setmIsTest(boolean mIsTest) {this.mIsTest = mIsTest;}
    public void setImportedData(List<CancerSubstance> importedData) {
        this.importedData = importedData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Instantiation of a CancerDataBase object followed by:
        // reading of the CSV file using the readCSVFile method
        // sending of a ess
        CancerDataBase cancer_data_base = new CancerDataBase();
        String message = new String();
        try {
            cancer_data_base.readCSVFile(getApplicationContext());
            message = cancer_data_base.sendOutputReadyToPrint();
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }


                /*" id\tAgent\t\tGroup\n";

        for (int i = 0; i < importedData.size(); i = i + 1) {
            message += " " + Integer.toString(i);
            message += "\t" + importedData.get(i).getmAgent();
            message += "\t\t\t" + importedData.get(i).getmGroup() + "\n";
        }
        */

        // Capture the layout's; TextView and set the string as its text
        TextView reportedMessage = (TextView) findViewById(R.id.cancerDataBaseMessage);
        reportedMessage.setTextSize(12);
        reportedMessage.setTextColor(Color.rgb(200,0,0));
        reportedMessage.setText(message);
    }
}