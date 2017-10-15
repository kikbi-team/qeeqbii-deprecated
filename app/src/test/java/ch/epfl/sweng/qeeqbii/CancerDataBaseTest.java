package ch.epfl.sweng.qeeqbii;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by adrien on 13/10/17.
 */

public class CancerDataBaseTest extends AppCompatActivity {
    // ContextWrapper mocked_class = Mockito.mock(ContextWrapper.class);

    // The idea of this test is to print the whole CancerDataBase to see if it has been loaded
    // properly
    @Test
    public void printDataBase() throws Exception {
        CancerDataBase cancer_data_base = new CancerDataBase();
        String message;
        try {
            //Context context;
            cancer_data_base.readCSVFile(getApplicationContext());
            message = cancer_data_base.sendOutputReadyToPrint();
            System.out.println(message);
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

    }

}
