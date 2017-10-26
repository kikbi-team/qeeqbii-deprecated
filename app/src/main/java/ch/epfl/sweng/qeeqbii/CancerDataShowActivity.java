package ch.epfl.sweng.qeeqbii;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class CancerDataShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Instantiation of a CancerDataBase object followed by:
        // reading of the CSV file using the readCSVFile method
        // sending of a message
        String message;
        try {
            message = CancerDataBase.sendOutputReadyToPrint();
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            message = "Failed to get the CancerDataBase in CancerDataShowActivity.";
        }

        // Capture the layout's; TextView and set the string as its text
        TextView reportedMessage = (TextView) findViewById(R.id.cancerDataBaseMessage);
        reportedMessage.setTextSize(12);
        reportedMessage.setTextColor(Color.rgb(200, 0, 0));
        reportedMessage.setText(message);
    }
}