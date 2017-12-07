package ch.epfl.sweng.qeeqbii;

import android.app.ProgressDialog;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity;
import ch.epfl.sweng.qeeqbii.chat.RegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by nicol on 01.12.2017.
 */

public class RegisterTest {

    @Rule
    public final ActivityTestRule<RegisterActivity> mActivityRule =
            new ActivityTestRule<>(RegisterActivity.class);
    @Test
    public void login(){

        String name = "UserTest";
        String email = "user" + randomInt() + "@example.com";
        String password = "password" + randomInt();

        //Enter Name
        enterName(name);
        // Enter email
        enterEmail(email);
        // Enter password
        enterPassword(password);

        // Click sign in
        ViewInteraction appCompatButton =onView(withId(R.id.reg_create_btn));
        appCompatButton.perform(click());

        // Force closing the progressDialog box in order to start new activity
        ProgressDialog progressDialog = mActivityRule.getActivity().getmRegProgress();
        if (progressDialog!=null)
        {
            if (progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
        }

        // Wait that BarcodeScanner activity starts (it takes a little bit of time since all the CSV files are read
        // when this activity is launched for the first time
        while (!BarcodeScannerActivity.isRunning()) {
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

    }


    private void enterName(String name) {
        ViewInteraction nameField = onView(withId(R.id.register_name));
        nameField.perform(replaceText(name));
    }

    private void enterEmail(String email) {
        ViewInteraction emailField = onView(withId(R.id.register_email_text));
        emailField.perform(replaceText(email));
    }

    private void enterPassword(String password) {
        ViewInteraction passwordField = onView((withId(R.id.register_password)));
        passwordField.perform(replaceText(password));
    }
    private String randomInt() {
        return String.valueOf(((new Random()).nextInt(100000)));
    }
}

