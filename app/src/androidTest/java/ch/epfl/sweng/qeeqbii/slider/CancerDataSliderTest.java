package ch.epfl.sweng.qeeqbii.slider;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.ActivityFinisher;
import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.activities.BarcodeToProductActivity;
import ch.epfl.sweng.qeeqbii.activities.CancerDataShowActivity;

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

    // disable product adding for these tests
    @BeforeClass
    public static void run_before() {
        BarcodeToProductActivity.setProductAddingAllowed(false);
    }

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

    @Test
    public void canGoToProductsComparisonActivity() {
        SliderTest sliderTest = new SliderTest();
        sliderTest.canGoToProductsComparisonActivity(layoutId, navViewId);
    }

    @AfterClass
    public static void finish_all_activities() {
        ActivityFinisher.finishOpenActivities();
    }
}
