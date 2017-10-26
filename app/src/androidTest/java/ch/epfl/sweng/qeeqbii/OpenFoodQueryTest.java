package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by guillaume on 16.10.17.
 *
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
            public void onPostExecute(String result) {

                HTTPRequestResponse resp;
                try {
                    resp = new HTTPRequestResponse(result);
                    assertEquals(resp.GetProductQuantity(), "245.0g");
                    assertEquals(resp.GetProductName("fr"), "Mangue : en tranches");
                    assertEquals(resp.GetProductIngredients("fr"), "mangue (Thaïlande), eau, sucre, acidifiant (E330)");
                    assertEquals(resp.GetProductNutrients("fr"), "Sel: 0.0g\nProtéines: 0.5g\nFibres alimentaires: 1.5g\nSucres: 15.0g\n" +
                            "Glucides: 15.0g\nAcides gras saturées: 0.0g\nMatières grasses: 0.0g\nÉnergie (kCal): 67.0kCal\nÉnergie: 280.0kJ\n");
                    Map<String, Double> parsed_nutrients = resp.ParseNutrients();

                    //Set<Map.Entry<String,Double>> set = parsed_nutrients.entrySet();
                    //Iterator<Map.Entry<String,Double>> it = set.iterator();
                    //Map.Entry<String,Double> e = it.next();
                    //assertEquals(e.getValue(),new Double(0.0));

                    assertEquals(parsed_nutrients.get("Sel (g)"), Double.valueOf(0.0));
                    assertEquals(parsed_nutrients.get("Énergie (kCal)"), Double.valueOf(67.0));
                    assertEquals(parsed_nutrients.get("Énergie (kJ)"), Double.valueOf(280.0));

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
    public void QueryOfNonExistingProduct() {
        {
            final CountDownLatch signal = new CountDownLatch(1);
            String[] barcode = new String[1];
            barcode[0] = "";

            new OpenFoodQuery() {
                public void onPostExecute(String result) {


                    HTTPRequestResponse resp = new HTTPRequestResponse();
                    try {
                        resp = new HTTPRequestResponse(result);
                        fail("request gave: " + resp);

                    } catch (Exception e) {
                        assertEquals(e.getMessage(), "ERROR: (openfood) Barcode not found in the database.");
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
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }


    }

    @Test
    public void CacheQueryTest()
    {
        String barcode = "7610848337010";
        try {
            HTTPRequestResponse resp = OpenFoodQuery.GetOrCreateHTTPRequestResponse(barcode);
            assertEquals(resp.GetProductQuantity(), "245.0g");
            assertEquals(resp.GetProductName("fr"), "Mangue : en tranches");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        if (OpenFoodQuery.isCached(barcode))
        {
            try {
                HTTPRequestResponse resp = OpenFoodQuery.get(barcode);
                assertEquals(resp.GetProductIngredients("fr"), "mangue (Thaïlande), eau, sucre, acidifiant (E330)");
                assertEquals(resp.GetProductNutrients("fr"), "Sel: 0.0g\nProtéines: 0.5g\nFibres alimentaires: 1.5g\nSucres: 15.0g\n" +
                        "Glucides: 15.0g\nAcides gras saturées: 0.0g\nMatières grasses: 0.0g\nÉnergie (kCal): 67.0kCal\nÉnergie: 280.0kJ\n");
            } catch (Exception e) {
                fail(e.getMessage());
            }
        } else {
            fail("barcode should be cached");
        }


    }

}
