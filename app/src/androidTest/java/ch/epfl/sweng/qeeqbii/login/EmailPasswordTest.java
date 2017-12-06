package ch.epfl.sweng.qeeqbii.login;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import ch.epfl.sweng.qeeqbii.BaseActivityIdlingResource;
import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.activities.login.EmailPasswordActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EmailPasswordTest {

    private IdlingResource mActivityResource;

    @Rule
    public ActivityTestRule<EmailPasswordActivity> mActivityTestRule =
            new ActivityTestRule<>(EmailPasswordActivity.class);

    @Before
    public void setUp() {
        if (mActivityResource != null) {
            Espresso.unregisterIdlingResources(mActivityResource);
        }

        // Register Activity as idling resource
        mActivityResource = new BaseActivityIdlingResource(mActivityTestRule.getActivity());
        Espresso.registerIdlingResources(mActivityResource);
    }

    @After
    public void tearDown() {
        if (mActivityResource != null) {
            Espresso.unregisterIdlingResources(mActivityResource);
        }
    }

    @Test
    public void failedSignInTest() {
        String email = "test@test.com";
        String password = "123456";
        //String firstname= "Nicolas";
        //String lastname="Lesimple";
        //String allergie = "cacahètes";
        //String aliment = "fruits";

        // Make sure we're signed out
        signOutIfPossible();

        //enter informations
       // enterFirstName(firstname);
       // enterLastName(lastname);
       // enterAllergies(allergie);
       // enterAliment(aliment);

        // Enter email
        enterEmail(email);

        // Enter password
        enterPassword(password);

        // Click sign in
        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.email_sign_in_button), withText(R.string.sign_in),
                        withParent(withId(R.id.email_password_buttons)),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Check that auth failed
        onView(withText(R.string.auth_failed))
                .check(matches(isDisplayed()));
    }

    @Test
    public void successfulSignUpAndSignInTest() {
        String email = "user" + randomInt() + "@example.com";
        String password = "password" + randomInt();
        //String firstname= "Nicolas";
        //String lastname="Lesimple";
        //String allergie = "cacahètes";
        //String aliment = "fruits";

        // Make sure we're signed out
        signOutIfPossible();

        // Enter email
        enterEmail(email);

        // Enter password
        enterPassword(password);

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
        //enter informations
        //enterFirstName(firstname);
        //enterLastName(lastname);
        //enterAllergies(allergie);
        //enterAliment(aliment);

        // Sign out
        signOutIfPossible();

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

        saveAndContinue();
    }

    private void signOutIfPossible() {
        try {
            onView(allOf(withId(R.id.sign_out_button), withText(R.string.sign_out), isDisplayed()))
                    .perform(click());
        } catch (NoMatchingViewException e) {
            // Ignore
        }

    }

    private void enterEmail(String email) {
        ViewInteraction emailField = onView(
                allOf(withId(R.id.field_email),
                        withParent(withId(R.id.email_password_fields)),
                        isDisplayed()));
        emailField.perform(replaceText(email));
    }

    private void enterPassword(String password) {
        ViewInteraction passwordField = onView(
                allOf(withId(R.id.field_password),
                        withParent(withId(R.id.email_password_fields)),
                        isDisplayed()));
        passwordField.perform(replaceText(password));
    }

    private void enterFirstName (String firstname){
        ViewInteraction passwordField = onView(
                allOf(withId(R.id.editTextFirstName),
                        withParent(withId(R.id.informationsProfile)),
                        isDisplayed()));
        passwordField.perform(replaceText(firstname));
    }
    private void enterLastName (String lastname) {
        ViewInteraction passwordField = onView(
                allOf(withId(R.id.editTextLastName),
                        withParent(withId(R.id.informationsProfile)),
                        isDisplayed()));
        passwordField.perform(replaceText(lastname));
    }
    private void enterAllergies (String allergies) {
        ViewInteraction passwordField = onView(
                allOf(withId(R.id.editTextAllergie),
                        withParent(withId(R.id.informationsProfile)),
                        isDisplayed()));
        passwordField.perform(replaceText(allergies));
    }
    private void enterAliment (String aliment) {
        ViewInteraction passwordField = onView(
                allOf(withId(R.id.editTextGout),
                        withParent(withId(R.id.informationsProfile)),
                        isDisplayed()));
        passwordField.perform(replaceText(aliment));
    }
    private String randomInt() {
        return String.valueOf(((new Random()).nextInt(100000)));
    }

    private void saveAndContinue() {
        try {
            onView(allOf(withId(R.id.buttonSave), isDisplayed()))
                    .perform(click());
        } catch (NoMatchingViewException e) {
            // Ignore
        }

    }
}
