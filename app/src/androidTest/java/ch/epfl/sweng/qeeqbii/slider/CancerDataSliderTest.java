package ch.epfl.sweng.qeeqbii.slider;

import android.app.Instrumentation;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import android.view.MenuItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.activities.CancerDataQueryActivity;
import ch.epfl.sweng.qeeqbii.activities.CancerDataShowActivity;
import ch.epfl.sweng.qeeqbii.activities.GraphsActivity;
import ch.epfl.sweng.qeeqbii.activities.MainActivity;
import ch.epfl.sweng.qeeqbii.activities.ShoppingListActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by antoine on 07/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class CancerDataSliderTest {

    @Rule
    public final ActivityTestRule<CancerDataShowActivity> mActivityRule =
            new ActivityTestRule<>(CancerDataShowActivity.class);


    private int layoutId = R.id.cancerDataShow;
    private int navViewId = R.id.nav_view_cancer_data_show;


    @Test
    public void canGoToBarcodeScanner() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToBarcodeScanner(layoutId, navViewId);
    }

    @Test
    public void canGoToCancerDataQuery() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToCancerDataQuery(layoutId, navViewId);
    }

    @Test
    public void canGoToCancerdataShowActivity() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToCancerdataShowActivity(layoutId, navViewId);
    }

    @Test
    public void canGoToCancerMainActivityChat() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToCancerMainActivityChat(layoutId, navViewId);
    }

    @Test
    public void canGoToGraphs() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToGraphs(layoutId, navViewId);
    }

    @Test
    public void canGoToMain() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToMain(layoutId, navViewId);
    }

    @Test
    public void canGoToShoppingList() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToShoppingList(layoutId, navViewId);
    }

    @Test
    public void canGoToStatistics() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToStatistics(layoutId, navViewId);
    }


    @Test
    public void canGoToSavedProductsDate() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToSavedProductsDate(layoutId, navViewId);
    }

}
