package ch.epfl.sweng.qeeqbii;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import ch.epfl.sweng.qeeqbii.activities.ShoppingCartFirstLevelActivity;
import ch.epfl.sweng.qeeqbii.activities.ShoppingListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by davidcleres on 05.11.17.
 */

public class AShoppingCartActivityShould {
    @Rule
    public final ActivityTestRule<ShoppingListActivity> mActivityRule =
            new ActivityTestRule<>(ShoppingListActivity.class);

    @Test
    public void launchNewActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(ShoppingCartFirstLevelActivity.class.getName(), null, false);

        // open current activity.
        ShoppingListActivity myActivity = mActivityRule.getActivity();
        final View button = (View) myActivity.findViewById(R.id.app_bar);
        myActivity.showShoppingList(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        ShoppingCartFirstLevelActivity nextActivity = (ShoppingCartFirstLevelActivity) getInstrumentation()
                .waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }
}
