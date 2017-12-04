package ch.epfl.sweng.qeeqbii;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.MenuItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.activities.CancerDataQueryActivity;
import ch.epfl.sweng.qeeqbii.activities.CancerDataShowActivity;
import ch.epfl.sweng.qeeqbii.activities.GraphsActivity;
import ch.epfl.sweng.qeeqbii.activities.MainActivity;
import ch.epfl.sweng.qeeqbii.activities.SavedProductsDatesActivity;
<<<<<<< HEAD
import ch.epfl.sweng.qeeqbii.activities.ShoppingListActivity;
=======
import ch.epfl.sweng.qeeqbii.activities.ShoppingCartActivity;
import ch.epfl.sweng.qeeqbii.chat.MainActivityChat;
>>>>>>> master
import ch.epfl.sweng.qeeqbii.shopping_cart.ShoppingCartStatistics;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by antoine on 07/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class barcodeScannerSliderTest {

    @Rule
    public final ActivityTestRule<BarcodeScannerActivity> mActivityRule =
            new ActivityTestRule<>(BarcodeScannerActivity.class);

    @Test
    public void testBackToMain() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);

        // open current activity.
        BarcodeScannerActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_main);
        myActivity.backToMain(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        MainActivity nextActivity = (MainActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testGoToGraphs() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(GraphsActivity.class.getName(), null, false);

        // open current activity.
        BarcodeScannerActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_graphs);
        myActivity.showGraphs(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        GraphsActivity nextActivity = (GraphsActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testGoToCancerShow() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(CancerDataShowActivity.class.getName(), null, false);

        // open current activity.
        BarcodeScannerActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_graphs);
        myActivity.cancerDataBaseShow(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        CancerDataShowActivity nextActivity = (CancerDataShowActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testGoToBarcodeReader() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(BarcodeScannerActivity.class.getName(), null, false);

        // open current activity.
        BarcodeScannerActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_graphs);
        myActivity.readBarcode(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        BarcodeScannerActivity nextActivity = (BarcodeScannerActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testGoToShoppingCart() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ShoppingListActivity.class.getName(), null, false);

        // open current activity.
        BarcodeScannerActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_graphs);
        myActivity.showShoppingList(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        ShoppingListActivity nextActivity = (ShoppingListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testGoToCancerQuery() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(CancerDataQueryActivity.class.getName(), null, false);

        // open current activity.
        BarcodeScannerActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_graphs);
        myActivity.cancerDataQuery(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        CancerDataQueryActivity nextActivity = (CancerDataQueryActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testGoToScannedProducts() {

    }

    @Test
    public void testGoToSavedProducts() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(SavedProductsDatesActivity.class.getName(), null, false);

        // open current activity.
        BarcodeScannerActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_graphs);
        myActivity.showSavedProducts(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        SavedProductsDatesActivity nextActivity = (SavedProductsDatesActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testGoToStats() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ShoppingCartStatistics.class.getName(), null, false);

        // open current activity.
        BarcodeScannerActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_graphs);
        myActivity.showStatistics(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        ShoppingCartStatistics nextActivity = (ShoppingCartStatistics) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testGoToChat() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivityChat.class.getName(), null, false);

        // open current activity.
        BarcodeScannerActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_graphs);
        myActivity.showChat(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        MainActivityChat nextActivity = (MainActivityChat) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }
}
