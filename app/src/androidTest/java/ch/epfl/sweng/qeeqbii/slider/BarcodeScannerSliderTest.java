package ch.epfl.sweng.qeeqbii.slider;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.ActivityFinisher;
import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.activities.BarcodeToProductActivity;

import static android.os.SystemClock.sleep;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by antoine on 07/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class BarcodeScannerSliderTest {

    @Rule
    public final ActivityTestRule<BarcodeScannerActivity> mActivityRule =
            new ActivityTestRule<>(BarcodeScannerActivity.class);

    private int layoutId = R.id.barcode_scanner;
    private int navViewId = R.id.nav_view_barcode_scanner;

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
        try {
            // Click sign in
            ViewInteraction appCompatButton = onView(withId(R.id.start_login_btn));
            appCompatButton.perform(click());

            String email = "nicolaslesimple@noos.fr";
            String password = "123456";
            //Enter email
            ViewInteraction emailField = onView(withId(R.id.email_login_chat));
            emailField.perform(replaceText(email));

            // Enter password
            ViewInteraction passwordField = onView((withId(R.id.password_login_chat)));
            passwordField.perform(replaceText(password));

            // Click sign in
            ViewInteraction appCompatButton2 = onView(withId(R.id.login_btn));
            appCompatButton2.perform(click());

            sleep(5000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

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
