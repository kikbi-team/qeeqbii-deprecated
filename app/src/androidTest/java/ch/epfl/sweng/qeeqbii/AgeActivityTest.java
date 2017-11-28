package ch.epfl.sweng.qeeqbii;

import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import ch.epfl.sweng.qeeqbii.chat.AgeActivity;
import ch.epfl.sweng.qeeqbii.chat.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringEndsWith.endsWith;

/**
 * Created by nicol on 26.11.2017.
 */

public class AgeActivityTest {

    @Rule
    public ActivityTestRule<AgeActivity> mActivityTestRule =
            new ActivityTestRule<>(AgeActivity.class);

    @Test
    public void button_save_test () {

    //    ViewInteraction appCompatButton = onView(
      //          allOf(withId(R.id.age_save_btn),
        //                isDisplayed()));
        //appCompatButton.perform(click());


    }


}
