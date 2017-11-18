package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.activities.CancerDataShowActivity;
import ch.epfl.sweng.qeeqbii.cancer.CancerDataBase;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class CancerDataShowActivityTest {

    @Rule
    public final ActivityTestRule<CancerDataShowActivity> mActivityRule =
            new ActivityTestRule<>(CancerDataShowActivity.class);


    @Test
    public void testPerfectMatchCancerDataBase() {

        //onView(withId(R.id.cancerDataBaseButton)).perform(click());
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