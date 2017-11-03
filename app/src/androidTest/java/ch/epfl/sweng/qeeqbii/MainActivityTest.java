package ch.epfl.sweng.qeeqbii;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Button;

import com.android.dx.command.Main;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    /*
    @Test
    public void testCanOpenCancerDataBase() throws InterruptedException {
        onView(withId(R.id.cancerDataBaseButton)).perform(click());
    } */

    @Test
    public void testCancerDataBaseShow() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(CancerDataShowActivity.class.getName(), null, false);

        // open current activity.
        MainActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_cancerdb);
        myActivity.cancerDataBaseShow(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        CancerDataShowActivity nextActivity = (CancerDataShowActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    /*
    @Test
    public void testCanAccessOpenFood() throws InterruptedException {
        onView(withId(R.id.SearchProductButton)).perform(click());
    }*/

    @Test
    public void testOpenGraphs() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(GraphsActivity.class.getName(), null, false);

        // open current activity.
        MainActivity myActivity = mActivityRule.getActivity();
        final MenuItem button = (MenuItem) myActivity.findViewById(R.id.nav_graphs);
        myActivity.showGraphs(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        GraphsActivity nextActivity = (GraphsActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }
    /*
    @Test
    public void testCanOpenGraphs() throws Exception {
        //onView(withId(R.id.buttonGraphs)).perform(click());

        MainActivity activity = mActivityRule.getActivity();
        activity.showGraphs(null);


        //MAINTENANT IL FAUT VERIFIER SI ON A BIEN CHANGER D'ACTIVITEET CELA PROUVE QUE LE BOUTON FONCTIONNE

        //MenuItem item = (MenuItem) activity.findViewById(R.id.nav_graphs);

        //assertEquals(item.getTitle().toString(), "Graphs");


        MainActivity activity = mActivityRule.getActivity();
        Instrumentation.ActivityMonitor am = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);

        // Click the menu option
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
        getInstrumentation().invokeMenuActionSync(activity, R.id.nav_graphs, 0);

        Activity a = getInstrumentation().waitForMonitorWithTimeout(am, 1000);
        assertEquals(true, getInstrumentation().checkMonitorHit(am, 1));

        // Check type of returned Activity:
        //assertNotNull(a);
        //assertTrue(a instanceof GraphsActivity);
        a.finish();

        //onView(withId(R.id.textView2)).perform(click());
        //onView(withId(R.id.nav_graphs)).perform(click());
        //openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        //onView(withId(R.id.nav_graphs)).perform(click());

    }*/
    /*
    @Before
    public void init(){
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Point[] coordinates = new Point[4];
        coordinates[0] = new Point(248, 1520);
        coordinates[1] = new Point(248, 929);
        coordinates[2] = new Point(796, 1520);
        coordinates[3] = new Point(796, 929);
        try {
            if (!uiDevice.isScreenOn()) {
                uiDevice.wakeUp();
                uiDevice.swipe(coordinates, 10);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCanOpenShoppingCart() throws InterruptedException {
        onView(withId(R.id.shoppingButton)).perform(click());
    }

    @Test
    public void testCanOpenRecentlyScannedProductsActivity() throws InterruptedException {
        onView(withId(R.id.listRecentlyScannedProductButton)).perform(click());
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("ch.epfl.sweng.qeeqbii", appContext.getPackageName());
    }
}
*/
    @Test
    public void DisplayProductTest()
    {
        String barcode = "7610848337010";
        String string_nutrients = "Sel: 0.0g\nProtéines: 0.5g\nFibres alimentaires: 1.5g\nSucres: 15.0g\n" +
                "Glucides: 15.0g\nAcides gras saturées: 0.0g\nMatières grasses: 0.0g\nÉnergie (kCal): 67.0kCal\nÉnergie: 280.0kJ\n";

        String string_ingredients = "mangue (Thaïlande), eau, sucre, acidifiant (E330)";

        String string_name = "Mangue : en tranches";

        String string_quantity = "245.0g";

        String s = string_name;
        s += "\n\nIngredients: " + string_ingredients;
        s += "\n\nQuantity: " + string_quantity;
        s += "\n\nNutrients: (per 100g)\n" + string_nutrients;
        onView(withId(R.id.mainName)).perform(typeText(barcode));
        //onView(withId(R.id.SearchProductButton)).perform(click());

        //onView(withId(R.id.product_details)).check(matches(withText(s)));
    }
    /*
    @Test
    public void testCanOpenQRCodesScanner() throws InterruptedException {
        onView(withId(R.id.mainBarcodeButton)).perform(click());

    }*/
}