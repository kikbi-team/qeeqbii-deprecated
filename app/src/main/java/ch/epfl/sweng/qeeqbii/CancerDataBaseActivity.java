package ch.epfl.sweng.qeeqbii;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by adrien on 13/10/17.
 */

public class CancerDataBaseActivity extends AppCompatActivity {

    // The idea of this test is to simply print the whole CancerDataBase to see if it has been loaded
    // properly
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        CancerDataBase cancer_data_base = new CancerDataBase();
        String message;
        try {
            cancer_data_base.readCSVFile(getApplicationContext());
            message = cancer_data_base.sendOutputReadyToPrint();
            System.out.println(message);
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

    }
}
