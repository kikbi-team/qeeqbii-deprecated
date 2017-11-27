package ch.epfl.sweng.qeeqbii;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import ch.epfl.sweng.qeeqbii.activities.login.EmailPasswordActivity;
import ch.epfl.sweng.qeeqbii.chat.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static ch.epfl.sweng.qeeqbii.R.id.login_email;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by nicol on 26.11.2017.
 */

public class LoginChatTest {

    private IdlingResource mActivityResource;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);



    @Test
    public void failedSignInTest() {
        String email = "test@test.com";
        String password = "123456";



        // Click sign in
        //ViewInteraction appCompatButton = onView(
          //      allOf(withId(R.id.login_btn), withText("LOGIN"),
            //            isDisplayed()));
        //appCompatButton.perform(click());

        // Check that auth failed
       // onView(withText("Error: "))
         //       .check(matches(isDisplayed()));
    }

    @Test
    public void successfulSignUpAndSignInTest() {
       // String email = "user" + randomInt() + "@example.com";
        //String password = "password" + randomInt();

        // Enter email
       // enterEmail(email);

        // Enter password
        //enterPassword(password);

     //   Login();
/***
        // Click sign up
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_create_account_button), withText(R.string.create_account),
                        withParent(withId(R.id.email_password_buttons)),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Sign out button shown
        onView(allOf(withId(R.id.sign_out_button), withText(R.string.sign_out), isDisplayed()));

        // User email shown
        String emailString = mActivityTestRule.getActivity()
                .getString(R.string.emailpassword_status_fmt, email,false);
        onView(withText(emailString))
                .check(matches(isDisplayed()));

        // Sign back in with the email and password
        enterEmail(email);
        enterPassword(password);

        // Click sign in
        ViewInteraction signInButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText(R.string.sign_in),
                        withParent(withId(R.id.email_password_buttons)),
                        isDisplayed()));
        signInButton.perform(click());

        // User email shown
        onView(withText(emailString))
                .check(matches(isDisplayed()));

        saveAndContinue();**/
    }



    private void enterEmail(String email) {
        ViewInteraction emailField = onView(
                allOf(withId(login_email),
                        isDisplayed()));
        //emailField.perform(setText(email));
    }

    private void enterPassword(String password) {
        ViewInteraction passwordField = onView(
                allOf(withId(R.id.login_password),
                        isDisplayed()));
        //passwordField.perform(replaceText(password));
    }

    private String randomInt() {
        return String.valueOf(((new Random()).nextInt(100000)));
    }

    private void Login() {
        try {
            onView(allOf(withId(R.id.login_btn), isDisplayed()))
                    .perform(click());
        } catch (NoMatchingViewException e) {
            // Ignore
        }

    }
}
