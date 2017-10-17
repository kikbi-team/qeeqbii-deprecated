package ch.epfl.sweng.qeeqbii;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.runner.AndroidJUnit4;

/**
 * Created by guillaume on 16.10.17.
 */

@RunWith(AndroidJUnit4.class)
public class OpenFoodQueryTest {

    @Rule
    public final ActivityTestRule<BarcodeToProductActivity> mActivityRule =
            new ActivityTestRule<>(BarcodeToProductActivity);

    @Test
    public void QueryOfExistingProduct()
    {
        String[] barcode = new String[1];
        barcode[0] = "7610848337010";


        new OpenFoodQuery()
        {
            @Override public void onPostExecute(String result)
            {

                getResources().openRawResource();

                result = result.substring(result.indexOf('['),result.indexOf(']'));
                assertEquals(result,true_result);

            }
        }.execute(barcode);



    }

}
