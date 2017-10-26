package ch.epfl.sweng.qeeqbii;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    /*
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void testCanOpenCancerDataBase() throws InterruptedException {
        onView(withId(R.id.cancerDataBaseButton)).perform(click());
    }

    @Test
    public void testCanOpenQRCodesScanner() throws InterruptedException {
        onView(withId(R.id.mainBarcodeButton)).perform(click());
    }

    @Test
    public void testCanAccessOpenFood() throws InterruptedException {
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void testCanOpenGraphs() throws InterruptedException {
        onView(withId(R.id.buttonGraphs)).perform(click());
    }
    */

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("ch.epfl.sweng.qeeqbii", appContext.getPackageName());
    }
}