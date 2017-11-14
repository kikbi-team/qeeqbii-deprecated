package ch.epfl.sweng.qeeqbii;

/**
 * Created by davidcleres on 14.11.17.
 */

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.MenuItem;
import android.view.View;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class ShoppingListTest {
    @Rule
    public final ActivityTestRule<ShoppingListActivity> mActivityRule =
            new ActivityTestRule<>(ShoppingListActivity.class);

    @Test
    public void testCanOpenClickOnImageSaussage() throws InterruptedException {
        onView(withId(R.id.sauceImageButton)).perform(click());
    }

    @Test
    public void testCanOpenClickOnImageMeat() throws InterruptedException {
        onView(withId(R.id.meatImageButton)).perform(click());
    }

    @Test
    public void testCanOpenClickOnImageCarot() throws InterruptedException {
        onView(withId(R.id.carotImageButton)).perform(click());
    }

    @Test
    public void testCanOpenClickOnImagePizza() throws InterruptedException {

            // register next activity that need to be monitored.
            Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ShoppingCartActivity.class.getName(), null, false);

            // open current activity.
            ShoppingListActivity myActivity = mActivityRule.getActivity();
            final View button = (View) myActivity.findViewById(R.id.pizzaImageButton);
            myActivity.addPizza(button);

            //Watch for the timeout
            //example values 5000 if in ms, or 5 if it's in seconds.
            ShoppingCartActivity nextActivity = (ShoppingCartActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
            // next activity is opened and captured.
            assertNotNull(nextActivity);
            nextActivity.finish();
    }

    @Test
    public void testCanOpenClickOnImageApple() throws InterruptedException {

        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ShoppingCartActivity.class.getName(), null, false);

        // open current activity.
        ShoppingListActivity myActivity = mActivityRule.getActivity();
        final View button = (View) myActivity.findViewById(R.id.appleImageButton);
        myActivity.addApple(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        ShoppingCartActivity nextActivity = (ShoppingCartActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testCanOpenClickOnImageChips() throws InterruptedException {
        onView(withId(R.id.chipsImageButton)).perform(click());
    }

    @Test
    public void testCanOpenClickOnImageWine() throws InterruptedException {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ShoppingCartActivity.class.getName(), null, false);

        // open current activity.
        ShoppingListActivity myActivity = mActivityRule.getActivity();
        final View button = (View) myActivity.findViewById(R.id.wineImageButton);
        myActivity.addWine(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        ShoppingCartActivity nextActivity = (ShoppingCartActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }

    @Test
    public void testCanOpenClickOnImageGrossery() throws InterruptedException {
        onView(withId(R.id.grosseryImageButton)).perform(click());
    }

    @Test
    public void testCanOpenClickOnImageCheese() throws InterruptedException {
        onView(withId(R.id.cheeseImageButton)).perform(click());
    }

    /*
    @Test
    public void testCanOpenClickOnImageMeat() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ShoppingListActivity.class.getName(), null, false);

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
    }*/
}
