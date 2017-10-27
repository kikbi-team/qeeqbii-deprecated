package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class CancerDataShowActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    //public final ActivityTestRule<CancerDataShowActivity> mActivityRule =
    //        new ActivityTestRule<CancerDataShowActivity>(CancerDataShowActivity.class);
    /*
    @Before
    public void Initialize() {
        try {
            CancerDataBase.readCSVFile(mActivityRule.getActivity().getApplicationContext());
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    */

    @Test
    public void testPerfectMatchCancerDataBase() {

        onView(withId(R.id.cancerDataBaseButton)).perform(click());
        String string_tester;
        try {
            string_tester = CancerDataBase.sendOutputReadyToPrint();
        }
        catch (Exception e) {
            string_tester = "Did not manage to Read the CancerDataBase.";
        }
        onView(withId(R.id.cancerDataBaseMessage)).check(matches(withText(string_tester)));
    }
}