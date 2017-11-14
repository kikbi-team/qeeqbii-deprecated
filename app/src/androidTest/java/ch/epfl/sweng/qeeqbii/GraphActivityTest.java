package ch.epfl.sweng.qeeqbii;

import android.app.Activity;
import android.app.Instrumentation;
import android.media.audiofx.BassBoost;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.MenuItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

/**
 * Created by davidcleres on 12.10.17.
 */
@RunWith(AndroidJUnit4.class)
public class GraphActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public GraphActivityTest() {
        super(MainActivity.class);
    }

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void recognizesWhenCalled() throws InterruptedException {

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
    }
}