package ch.epfl.sweng.qeeqbii;

import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class BarcodeLeadsToProduct {
    @Rule
    public IntentsTestRule<BarcodeActivity> mActivityRule =
            new IntentsTestRule<BarcodeActivity>(BarcodeActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // https://www.openfood.ch/en/products/972

        BarcodeActivity activity = mActivityRule.getActivity();
        activity.processBarcode("7611654884033");
        intended(hasComponent(new ComponentName(getTargetContext(), BarcodeToProductActivity.class)));
        onView(withId(R.id.product_details)).check(matches(withText(startsWith("Chocolat au lait aux noisettes"))));
    }
}