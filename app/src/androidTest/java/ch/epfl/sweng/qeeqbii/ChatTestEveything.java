package ch.epfl.sweng.qeeqbii;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import ch.epfl.sweng.qeeqbii.chat.RegisterActivity;
import ch.epfl.sweng.qeeqbii.chat.StartActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by nicol on 01.12.2017.
 */

public class ChatTestEveything {

    @Rule
    public final ActivityTestRule<StartActivity> mActivityRule =
            new ActivityTestRule<>(StartActivity.class);

    @Test
    public void allreadyhaveaccount() {

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

    }
}




