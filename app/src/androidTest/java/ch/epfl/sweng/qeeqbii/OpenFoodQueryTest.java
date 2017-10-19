package ch.epfl.sweng.qeeqbii;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.fail;
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
    public void QueryOfExistingProduct() {
        final CountDownLatch signal = new CountDownLatch(1);
        String[] barcode = new String[1];
        barcode[0] = "7610848337010";

        new OpenFoodQuery() {
            @Override
            public void onPostExecute(String result) {


                HTTPRequestResponse resp = new HTTPRequestResponse();
                try {
                    HTTPRequestResponse resp2 = new HTTPRequestResponse(result);
                    assertEquals(resp2.GetProductQuantity(), "245.0g");
                    assertEquals(resp2.GetProductName("fr"), "Mangue : en tranches");
                    assertEquals(resp2.GetProductIngredients("fr"), "mangue (Thaïlande), eau, sucre, acidifiant (E330)");
                    assertEquals(resp2.GetProductNutrients("fr"), "Sel: 0.0g\nProtéines: 0.5g\nFibres alimentaires: 1.5g\nSucres: 15.0g\n" +
                            "Glucides: 15.0g\nAcides gras saturées: 0.0g\nMatières grasses: 0.0g\nÉnergie (kCal): 67.0kCal\nÉnergie: 280.0kJ\n");

                } catch (Exception e) {

                    fail(e.getMessage());
                } finally {

                    signal.countDown();
                }


            }
        }.execute(barcode);

        try {
            signal.await(); //wait for callback
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

        @Test
        public void QueryOfNonExistingProduct()
        {
        {
            final CountDownLatch signal = new CountDownLatch(1);
            String[] barcode = new String[1];
            barcode[0] = "";

            new OpenFoodQuery()
            {
                @Override public void onPostExecute(String result)
                {


                    HTTPRequestResponse resp = new HTTPRequestResponse();
                    try {
                        resp = new HTTPRequestResponse(result);
                        fail("request gave: " + resp);

                    }
                    catch(Exception e)
                    {
                        assertEquals(e.getMessage(),"Barcode not found in the database.");
                        assertEquals(resp.GetProductQuantity(), "Quantity Not Found");
                        assertEquals(resp.GetProductName("fr"), "Name Not Found");
                        assertEquals(resp.GetProductIngredients("fr"), "Ingredients Not Found");
                        assertEquals(resp.GetProductNutrients("fr"), "Nutrients Not Found");


                    } finally {

                        signal.countDown();
                    }


                }
            }.execute(barcode);

            try {
                signal.await(); //wait for callback
            } catch(Exception e) {
                fail(e.getMessage());
            }
        }



    }

}
