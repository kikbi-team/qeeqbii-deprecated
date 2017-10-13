package ch.epfl.sweng.qeeqbii;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by adrien on 13/10/17.
 */

public class CancerDataQueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancer_data_query);

        CancerDataBase cancer_data_base = new CancerDataBase();
        String message = new String();
        try {
            cancer_data_base.readCSVFile(getApplicationContext());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }




}
