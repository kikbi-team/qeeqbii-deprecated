package ch.epfl.sweng.qeeqbii;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import ch.epfl.sweng.qeeqbii.chat.AgeActivity;
import ch.epfl.sweng.qeeqbii.chat.DegoutActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by nicol on 26.11.2017.
 */

public class DegoutActivityTest {
    @Rule
    public ActivityTestRule<DegoutActivity> mActivityTestRule =
            new ActivityTestRule<>(DegoutActivity.class);

    @Test
    public void button_save_test () {

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.degout_save_btn),
                        isDisplayed()));
        appCompatButton.perform(click());


    }
}

