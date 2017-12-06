package ch.epfl.sweng.qeeqbii;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import ch.epfl.sweng.qeeqbii.activities.SavedProductsDatesActivity;
import ch.epfl.sweng.qeeqbii.chat.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;

/**
 * Created by nicol on 01.12.2017.
 */

public class LoginTest {
    @Rule
    public final ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);
    @Test
    public void login(){
        String email = "nicolaslesimple@noos.fr";
        String password = "123456";

        // Enter email
        enterEmail(email);

        // Enter password
        enterPassword(password);

        // Click sign in
        ViewInteraction appCompatButton =onView(withId(R.id.login_btn));
        appCompatButton.perform(click());

    }



    private void enterEmail(String email) {
        ViewInteraction emailField = onView(withId(R.id.email_login_chat));
        emailField.perform(replaceText(email));
    }

    private void enterPassword(String password) {
        ViewInteraction passwordField = onView((withId(R.id.password_login_chat)));
        passwordField.perform(replaceText(password));
    }

}
