package ch.epfl.sweng.qeeqbii;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static org.junit.Assert.assertEquals;

/**
 * Created by guillaume on 16.10.17.
 */

@RunWith(AndroidJUnit4.class)
public class OpenFoodQueryTest {

    @Rule
    public final ActivityTestRule<BarcodeToProductActivity> mActivityRule =
            new ActivityTestRule<>(BarcodeToProductActivity.class);

    @Test
    public void QueryOfExistingProduct()
    {
        String[] barcode = new String[1];
        barcode[0] = "7610848337010";


        new OpenFoodQuery()
        {
            @Override public void onPostExecute(String result)
            {

                String true_result = mActivityRule.getActivity().getResources()
                        .openRawResource(R.raw.example_response_openfood_761084337010).toString();

                result = result.substring(result.indexOf('['),result.indexOf(']'));
                assertEquals(result,true_result);

            }
        }.execute(barcode);



    }

}
