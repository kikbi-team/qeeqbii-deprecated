package ch.epfl.sweng.qeeqbii;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import ch.epfl.sweng.qeeqbii.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by davidcleres on 05.11.17.
 */

public class AShoppingCartActivityShould {
    @Rule
    public final ActivityTestRule<ShoppingCartActivity> mActivityRule =
            new ActivityTestRule<>(ShoppingCartActivity.class);

    @Test
    public void launchNewActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ShoppingListActivity.class.getName(), null, false);

        // open current activity.
        ShoppingCartActivity myActivity = mActivityRule.getActivity();
        final View button = (View) myActivity.findViewById(R.id.app_bar);
        myActivity.showShoppingList(button);

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        ShoppingListActivity nextActivity = (ShoppingListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertNotNull(nextActivity);
        nextActivity.finish();
    }
}
