package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by davidcleres on 12.10.17.
 */

public class AGraphClassShould {
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    /*
    @Test
    //be able to sense that one clicked the button
    public void recognizesWhenCalled() throws InterruptedException {
        onView(withId(R.id.nav_graphs)).perform(click());
    }
    */
}