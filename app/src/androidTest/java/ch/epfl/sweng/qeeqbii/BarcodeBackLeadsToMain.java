package ch.epfl.sweng.qeeqbii;

import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.activities.MainActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class BarcodeBackLeadsToMain {
    @Rule
    public final IntentsTestRule<BarcodeScannerActivity> mActivityRule =
            new IntentsTestRule<>(BarcodeScannerActivity.class);

    @Test
    public void useAppContext() throws Exception {
        BarcodeScannerActivity activity = mActivityRule.getActivity();
        activity.onKeyDown(KeyEvent.KEYCODE_BACK, new KeyEvent(0, KeyEvent.KEYCODE_BACK));
        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));
    }
}