package ch.epfl.sweng.qeeqbii;

/**
 * Created by sergei on 11/30/17.
 */

import android.app.Activity;
import android.content.ComponentName;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.activities.BarcodeToProductActivity;
import ch.epfl.sweng.qeeqbii.activities.ProductComparisonActivity;
import ch.epfl.sweng.qeeqbii.open_food.RecentlyScannedProducts;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.VerificationModes.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.runner.lifecycle.Stage.RESUMED;
import static java.lang.Thread.sleep;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class ProductComparisonActivityTest {

    @Rule
    public final IntentsTestRule<ProductComparisonActivity> mActivityRule =
            new IntentsTestRule<>(ProductComparisonActivity.class);

    @Test
    public void testCanShowInsufficientProducts() {
        RecentlyScannedProducts.clear();
        onView(withId(R.id.product_name_1)).check(matches(withText("Name 1")));
        onView(withId(R.id.product_name_2)).check(matches(withText("Name 2")));
        ListView ls = (ListView) mActivityRule.getActivity().findViewById(R.id.graphs);
        assertTrue(ls.getCount() == 0);
    }

    public Activity getActivityInstance() {
        final Activity[] currentActivity = new Activity[1];

        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity[0] = (Activity) resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity[0];
    }

    @Test
    public void testCanCompareProducts() {
        RecentlyScannedProducts.clear();

        onView(withId(R.id.scan_button)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), BarcodeScannerActivity.class)), times(1));

        // scanning chocolate bar
        ((BarcodeScannerActivity) getActivityInstance()).processBarcode("7610848493136");
        intended(hasComponent(new ComponentName(getTargetContext(), BarcodeToProductActivity.class)), times(1));

        // going back to scanner
        onView(isRoot()).perform(ViewActions.pressBack());
        intended(hasComponent(new ComponentName(getTargetContext(), BarcodeScannerActivity.class)), times(1));

        // scanning another bar
        ((BarcodeScannerActivity) getActivityInstance()).processBarcode("7611654884033");
        intended(hasComponent(new ComponentName(getTargetContext(), BarcodeToProductActivity.class)), times(2));
        onView(isRoot()).perform(ViewActions.pressBack());

        intended(hasComponent(new ComponentName(getTargetContext(), BarcodeScannerActivity.class)), times(1));
        ((BarcodeScannerActivity) getActivityInstance()).goToMain();

        /*
        try {
            sleep(5000);
        } catch (Exception e)
        {

        }
        */

        // go to product comparison
        onView(withId(R.id.productComparisonButton)).perform(click());

        // checking if the view displays names and charts
        onView(withId(R.id.product_name_1)).check(matches(withText(startsWith("Chocolat"))));
        onView(withId(R.id.product_name_2)).check(matches(withText(startsWith("Chocolat"))));
        ListView ls = (ListView) mActivityRule.getActivity().findViewById(R.id.graphs);

        // check if button is visible
        onView(withId(R.id.scan_button)).check(matches(isDisplayed()));

//        assertTrue(ls.getAdapter().getCount() > 0);
    }
}