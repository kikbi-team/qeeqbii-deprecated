package ch.epfl.sweng.qeeqbii;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by nicol on 05.11.2017.
 */

@RunWith(AndroidJUnit4.class)
public class ShareFacebookTest {
    @Rule
    public final IntentsTestRule<GraphsActivity> mActivityRule =
            new IntentsTestRule<>(GraphsActivity.class);

    @Test
    public void useAppContext() throws Exception {
        GraphsActivity activity = mActivityRule.getActivity();
        Thread.sleep(200);
        Espresso.closeSoftKeyboard();
        Thread.sleep(200);
        onView(withId(R.id.button_share_on_fb_graph)).perform(click());


    }
}