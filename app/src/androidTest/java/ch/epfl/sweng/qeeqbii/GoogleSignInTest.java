package ch.epfl.sweng.qeeqbii;

/**
 * Created by nicol on 20.10.2017.
 */
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.startsWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GoogleSignInTest {

        @Rule
        public ActivityTestRule<GoogleSignInActivity> mActivityTestRule =
                new ActivityTestRule<>(GoogleSignInActivity.class);

        @Test
        public void GoogleSignInTest_() {
            // Sign out if possible
            signOutIfPossible();
            //Disconnect if possible
            DisconnectIfPossible();
            //Disconnect if possible
            SaveIfPossible();

            // Click sign in
            onView(allOf(withId(R.id.sign_in_button), isDisplayed()))
                    .perform(click());


        }

        private void signOutIfPossible() {
            try {
                onView(allOf(withId(R.id.sign_out_button), isDisplayed()))
                        .perform(click());
            } catch (NoMatchingViewException e) {
                // Ignore
            }

        }
        private void DisconnectIfPossible() {
            try {
                onView(allOf(withId(R.id.disconnect_button), isDisplayed()))
                        .perform(click());
            } catch (NoMatchingViewException e) {
                // Ignore
            }

        }

    private void SaveIfPossible() {
        try {
            onView(allOf(withId(R.id.buttonSave), isDisplayed()))
                    .perform(click());
        } catch (NoMatchingViewException e) {
            // Ignore
        }

    }


}
