package ch.epfl.sweng.qeeqbii.slider;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import android.view.MenuItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.activities.CancerDataQueryActivity;
import ch.epfl.sweng.qeeqbii.activities.CancerDataShowActivity;
import ch.epfl.sweng.qeeqbii.activities.GraphsActivity;
import ch.epfl.sweng.qeeqbii.activities.MainActivity;
import ch.epfl.sweng.qeeqbii.activities.SavedProductsDatesActivity;
import ch.epfl.sweng.qeeqbii.activities.ShoppingListActivity;
import ch.epfl.sweng.qeeqbii.chat.MainActivityChat;
import ch.epfl.sweng.qeeqbii.activities.StatisticsActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class SavedProductsActivitySliderTest {
    @Rule
    public final ActivityTestRule<SavedProductsDatesActivity> mActivityRule =
            new ActivityTestRule<>(SavedProductsDatesActivity.class);

    @Test
    public void testBackToMain() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);

        onView(withId(R.id.SavedProductsLayout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        // Start the screen of your activity.
        onView(withId(R.id.nav_view_recently_scanned_products)).perform(NavigationViewActions.navigateTo(R.id.nav_main));

        MainActivity nextActivity = (MainActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();

    }

    @Test
    public void testGoToGraphs() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(GraphsActivity.class.getName(), null, false);

        onView(withId(R.id.SavedProductsLayout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        // Start the screen of your activity.
        onView(withId(R.id.nav_view_recently_scanned_products)).perform(NavigationViewActions.navigateTo(R.id.nav_graphs));

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
        onView(withId(R.id.SavedProductsLayout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        // Start the screen of your activity.
        onView(withId(R.id.nav_view_recently_scanned_products)).perform(NavigationViewActions.navigateTo(R.id.nav_cancerdb));

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

        onView(withId(R.id.SavedProductsLayout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        // Start the screen of your activity.
        onView(withId(R.id.nav_view_recently_scanned_products)).perform(NavigationViewActions.navigateTo(R.id.nav_scan));

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

        onView(withId(R.id.SavedProductsLayout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        // Start the screen of your activity.
        onView(withId(R.id.nav_view_recently_scanned_products)).perform(NavigationViewActions.navigateTo(R.id.nav_shopping_cart));

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

        onView(withId(R.id.SavedProductsLayout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        // Start the screen of your activity.
        onView(withId(R.id.nav_view_recently_scanned_products)).perform(NavigationViewActions.navigateTo(R.id.nav_dataquery));

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

        onView(withId(R.id.SavedProductsLayout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        // Start the screen of your activity.
        onView(withId(R.id.nav_view_recently_scanned_products)).perform(NavigationViewActions.navigateTo(R.id.nav_scanning_history));

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
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(StatisticsActivity.class.getName(), null, false);

        onView(withId(R.id.SavedProductsLayout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        // Start the screen of your activity.
        onView(withId(R.id.nav_view_recently_scanned_products)).perform(NavigationViewActions.navigateTo(R.id.nav_stats));

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        StatisticsActivity nextActivity = (StatisticsActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testGoToChat() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivityChat.class.getName(), null, false);

        onView(withId(R.id.SavedProductsLayout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        // Start the screen of your activity.
        onView(withId(R.id.nav_view_recently_scanned_products)).perform(NavigationViewActions.navigateTo(R.id.nav_chat));

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        MainActivityChat nextActivity = (MainActivityChat) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }
}
