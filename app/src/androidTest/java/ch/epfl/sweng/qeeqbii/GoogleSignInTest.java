package ch.epfl.sweng.qeeqbii;

import android.content.ComponentName;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class GoogleSignInTest {
    @Rule
    public final IntentsTestRule<GoogleSignInActivity> mActivityRule =
            new IntentsTestRule<>(GoogleSignInActivity.class);

    @Test
    public void useAppContext() throws Exception {
        GoogleSignInActivity activity = mActivityRule.getActivity();
        Thread.sleep(200);
        Espresso.closeSoftKeyboard();
        Thread.sleep(200);
        onView(withId(R.id.buttonSave)).perform(click());
        Thread.sleep(50);
        onView(withId(R.id.buttonSave)).perform(click());
    }
}