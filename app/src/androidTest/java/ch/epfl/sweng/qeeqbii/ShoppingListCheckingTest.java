package ch.epfl.sweng.qeeqbii;

import android.app.Activity;
import android.content.ComponentName;
import android.os.SystemClock;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.view.View;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Collection;

import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.activities.ShoppingCartActivity;
import ch.epfl.sweng.qeeqbii.activities.ShoppingListActivity;
import ch.epfl.sweng.qeeqbii.shopping_cart.ShoppingCart;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.VerificationModes.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.runner.lifecycle.Stage.RESUMED;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sergei on 11/23/17.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShoppingListCheckingTest {

    @Rule
    public final IntentsTestRule<ShoppingListActivity> mActivityRule =
            new IntentsTestRule<>(ShoppingListActivity.class);


    public Activity getActivityInstance(){
        final Activity[] currentActivity = new Activity[1];

        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()){
                    currentActivity[0] = (Activity) resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity[0];
    }


    @Test
    public void testCanCheckProduct() throws InterruptedException {
        // open current activity.
        ShoppingListActivity myActivity = mActivityRule.getActivity();

        // adding pizza
        final View button = (View) myActivity.findViewById(R.id.pizzaImageButton);
        myActivity.addPizza(button);

        // use mock version of clustering
        ShoppingCart.enableMockBarcodeChecking();

        // it is not checked
        assertFalse(ShoppingCart.m_items.get(0).isChecked());

        // going to barcode
        onView(withId(R.id.scanToCheckButton)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), BarcodeScannerActivity.class)));

        // scanning pizza
        ((BarcodeScannerActivity) getActivityInstance()).processBarcode("4001724819905");
        intended(hasComponent(new ComponentName(getTargetContext(), ShoppingCartActivity.class)), times(2));

        // now it is checked
        assertTrue(ShoppingCart.m_items.get(0).isChecked());
    }
}
