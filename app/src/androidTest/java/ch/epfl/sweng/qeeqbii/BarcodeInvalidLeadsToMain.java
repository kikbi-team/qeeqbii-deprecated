package ch.epfl.sweng.qeeqbii;

import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.zxing.Result;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static com.google.zxing.BarcodeFormat.QR_CODE;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class BarcodeInvalidLeadsToMain {
    @Rule
    public final IntentsTestRule<BarcodeScannerActivity> mActivityRule =
            new IntentsTestRule<>(BarcodeScannerActivity.class);

    @Test
    public void useAppContext() throws Exception {
        BarcodeScannerActivity activity = mActivityRule.getActivity();
        Result x = new Result("", null, null, QR_CODE);
        activity.handleResult(x);
        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));
    }
}