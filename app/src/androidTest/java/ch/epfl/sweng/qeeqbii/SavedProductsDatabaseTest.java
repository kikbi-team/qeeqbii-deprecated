package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.dx.command.Main;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.spec.ECField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by guillaume on 14/11/17.
 */

@RunWith(AndroidJUnit4.class)
public class SavedProductsDatabaseTest {

    @Rule
    public final ActivityTestRule<SavedProductsDatesActivity> mActivityRule =
            new ActivityTestRule<>(SavedProductsDatesActivity.class);

    @Before
    public void loadTestDatabase() {
        try
        {
            SavedProductsDatabase.getDates();
            SavedProductsDatabase.load(mActivityRule.getActivity().getApplicationContext(), R.raw.saved_products_database_test);
        } catch (Exception e)
        {
            fail(e.getMessage());
        }

    }
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void loadDatabaseTest()
    {
        try
        {
            Date[] dates = SavedProductsDatabase.getDates();
            assertEquals(formatter.parse("13/11/2017"),dates[0]);

        } catch( Exception e)
        {
            fail(e.getMessage());
        }

    }

    @Test
    public void getProductTest()
    {
        try
        {
            Product[] products = SavedProductsDatabase.getProductsFromDate(formatter.parse("13/11/2017"));

            assertEquals(2,products.length);
            assertEquals("Tartiflette", products[0].getName());
            assertEquals("Rebloch, Rebloch, Rebloch et Rebloch", products[1].getIngredients());

        } catch (Exception e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void addProductTest()
    {
        Product product = new Product("Raclette", "1000g", "Cheese," +
                " Cheese ,Cheese", "Sel: 0.200g", "00055232323", ClusterType.CHEESE);


    }





}
