package ch.epfl.sweng.qeeqbii;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.MenuItem;

import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.activities.GraphsActivity;
import ch.epfl.sweng.qeeqbii.activities.MainActivity;
import ch.epfl.sweng.qeeqbii.activities.SavedProductsDatesActivity;
import ch.epfl.sweng.qeeqbii.chat.AgeActivity;
import ch.epfl.sweng.qeeqbii.chat.AllergiesActivity;
import ch.epfl.sweng.qeeqbii.chat.ChatActivity;
import ch.epfl.sweng.qeeqbii.chat.DegoutActivity;
import ch.epfl.sweng.qeeqbii.chat.MainActivityChat;
import ch.epfl.sweng.qeeqbii.chat.ProfileActivity;
import ch.epfl.sweng.qeeqbii.chat.RegisterActivity;
import ch.epfl.sweng.qeeqbii.chat.Request;
import ch.epfl.sweng.qeeqbii.chat.SettingsActivity;
import ch.epfl.sweng.qeeqbii.chat.StartActivity;
import ch.epfl.sweng.qeeqbii.chat.StatusActivity;


import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by nicol on 01.12.2017.
 */

public class ChatTestEveything {

    @Rule
    public final ActivityTestRule<StartActivity> mActivityRule =
            new ActivityTestRule<>(StartActivity.class);

    @Test
    public void changeImage() {
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

        // Click chat on slider

        Intent intent = new Intent(mActivityRule.getActivity(), MainActivityChat.class);
        mActivityRule.launchActivity(intent);
        Intent intent2 = new Intent(mActivityRule.getActivity(), SettingsActivity.class);
        mActivityRule.launchActivity(intent2);
        Intent intent3 = new Intent(mActivityRule.getActivity(), AgeActivity.class);
        mActivityRule.launchActivity(intent3);
        Intent intent4 = new Intent(mActivityRule.getActivity(), AllergiesActivity.class);
        mActivityRule.launchActivity(intent4);
        Intent intent5 = new Intent(mActivityRule.getActivity(), DegoutActivity.class);
        mActivityRule.launchActivity(intent5);
        Intent intent6 = new Intent(mActivityRule.getActivity(), StatusActivity.class);
        mActivityRule.launchActivity(intent6);
        Intent intent7 = new Intent(mActivityRule.getActivity(), ProfileActivity.class);
        mActivityRule.launchActivity(intent7);
        Intent intent8 = new Intent(mActivityRule.getActivity(), ChatActivity.class);
        mActivityRule.launchActivity(intent8);






       // openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        //ViewInteraction appCompatButton3 = onView(withText(R.string.Chat));
        //appCompatButton3.perform(click());




        // Click on the right top button
       // openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        //ViewInteraction appCompatButton4 = onView(withText(R.string.AccountSettings));
        //appCompatButton4.perform(click());



        // Click settings
      //  ViewInteraction appCompatButton5 = onView(withId(R.id.settings_image_btn));
        //appCompatButton5.perform(click());
    }

   /* @Test
    public void changeStatus() {
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

        // Click settings
        Context appContext = InstrumentationRegistry.getTargetContext();
        openActionBarOverflowOrOptionsMenu(appContext);
        ViewInteraction appCompatButton3 = onView(withId(R.id.main_settings_btn));
        appCompatButton3.perform(click());

        // Click settings
        ViewInteraction appCompatButton4 = onView(withId(R.id.settings_status_btn));
        appCompatButton4.perform(click());
    }
/*
    @Test
    public void changeAllergie() {
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

        // Click settings
        ViewInteraction appCompatButton3 = onView(withId(R.id.main_settings_btn));
        appCompatButton3.perform(click());

        // Click settings
        ViewInteraction appCompatButton4 = onView(withId(R.id.));
        appCompatButton4.perform(click());
    }

    @Test
    public void changeStatus() {
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

        // Click settings
        ViewInteraction appCompatButton3 = onView(withId(R.id.main_settings_btn));
        appCompatButton3.perform(click());

        // Click settings
        ViewInteraction appCompatButton4 = onView(withId(R.id.settings_status_btn));
        appCompatButton4.perform(click());
    }

    @Test
    public void changeStatus() {
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

        // Click settings
        ViewInteraction appCompatButton3 = onView(withId(R.id.main_settings_btn));
        appCompatButton3.perform(click());

        // Click settings
        ViewInteraction appCompatButton4 = onView(withId(R.id.settings_status_btn));
        appCompatButton4.perform(click());
    }
*/
}




