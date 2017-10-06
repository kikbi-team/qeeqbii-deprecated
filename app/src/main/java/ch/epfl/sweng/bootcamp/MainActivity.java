package ch.epfl.sweng.bootcamp;

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

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "ch.epfl.sweng.bootcamp.mainNameExtracted";

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
                sample.setmAgent(tokens[0]); //Here we can add the fact that the dataBase might have leaks
                sample.setmGroup(tokens[1]);
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
        setContentView(R.layout.activity_main);
        readCSVFile ();

    }

    /** Called when the user taps the Send button
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.mainName);                         //Bootcamp versiom
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/

    @SuppressWarnings("UnusedParameters")
    public void sendMessage(View view) //Called when the user taps the Go button
    {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText myText = (EditText) findViewById(R.id.mainName);
        String message = myText.getText().toString();
        /*String message = "";//"bisous";//importedData.get(0).getmAgent();

        for (int i = 0; i < importedData.size(); i = i + 1) {
            message += " " + importedData.get(i).getmAgent();
            message += " " + importedData.get(i).getmGroup() + "\n";
        }*/

        intent.putExtra(getString(R.string.hello), message);

        /*adds the EditText's value to the intent.
        An Intent can carry data types as key-value pairs called extras.
        Your key is a public constant EXTRA_MESSAGE because the next activity uses the key to
        retrieve the text value. It's a good practice to define keys for intent extras using your
        app's package name as a prefix. This ensures the keys are unique, in case your app interacts
        with other apps. */

        startActivity(intent);
        /*starts an instance of the DisplayMessageActivity specified by the Intent.
        Now you need to create that class.*/
    }
}
