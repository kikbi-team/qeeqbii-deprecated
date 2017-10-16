package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by adrien on 16.10.17.
 */

@RunWith(AndroidJUnit4.class)
public class CancerDataQueryTest {
    @Rule
    public final ActivityTestRule<CancerDataQueryActivity> mActivityRule =
            new ActivityTestRule<>(CancerDataQueryActivity.class);
    @Test
    public void testCanGreetUsers() {
        onView(withId(R.id.cancerDataQueryTextField)).perform(typeText(getString(R.string.Formaldehyde)));
        onView(withId(R.id.cancerDataQueryButton)).perform(click());
        //TextView text = (TextView)findViewById(R.id.perfectQueryAnswerArea);
        // onView(withId(R.id.greetingMessage)).check(matches(withText("Hello from my unit test!")));
    }
}
