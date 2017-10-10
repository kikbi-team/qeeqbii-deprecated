package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static ch.epfl.sweng.qeeqbii.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    public static final String BARCODE_READER = "ch.epfl.sweng.qeeqbii.mainBarcode";

    private List<CancerData> importedData = new ArrayList< >();

    private void readCSVFile() {
        InputStream inStream = getResources().openRawResource(R.raw.original_classification_iarc_english);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, Charset.forName("UTF-8")));

        String line = null;
        try {
            // Step over the headers
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                //split by ','
                String[] tokens = line.split(",");

                //read the data
                CancerData sample = new CancerData();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        readCSVFile ();
    }

    @SuppressWarnings("UnusedParameters")
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, CancerDataShowActivity.class);

        String message = "";
        message += " id\tAgent\t\tGroup\n";

        for (int i = 0; i < importedData.size(); i = i + 1) {
            message += " " + Integer.toString(i);
            message += "\t" + importedData.get(i).getmAgent();
            message += "\t\t\t" + importedData.get(i).getmGroup() + "\n";
        }

        intent.putExtra(getString(R.string.hello), message);
        startActivity(intent);
    }

    public void readBarcode(View view) {
        Intent intent = new Intent(this, BarcodeActivity.class);
        startActivity(intent);
    }

    public void searchProductFromBarcode(View view)
    {
        Intent intent = new Intent(this, BarcodeToProductActivity.class);
        EditText editText = (EditText) findViewById(R.id.mainName);
        String barcode = editText.getText().toString();
        intent.putExtra(BARCODE_READER, barcode);
        startActivity(intent);
    }

    public void showGraphs(View view)
    {
        Intent intent = new Intent(this, Graphs.class);
        startActivity(intent);
    }
}