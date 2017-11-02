package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/*
@RunWith(AndroidJUnit4.class)
public class CancerDataQueryTest {
    //public final ActivityTestRule<CancerDataQueryActivity> mActivityRule =
    //       new ActivityTestRule<>(CancerDataQueryActivity.class);
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
              new ActivityTestRule<>(MainActivity.class);

    /*
    @Before
    public void Initialize() {
        try {
            CancerDataBase.readCSVFile(mActivityRule.getActivity().getApplicationContext());
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    */

    /*
    @Test
    public void testPerfectMatchCancerDataBase() {
        // Useful way of accessing resources
        onView(withId(R.id.cancerDataQuery)).perform(click());

        List<String[]> query_ans_pairs = new ArrayList<>();

        query_ans_pairs.add(new String[]{"Formaldehyde", "Substance{mId = 0, mAgent = 'Formaldehyde', mGroup = '1'}"});
        query_ans_pairs.add(new String[]{"", "Substance{mId = -1, mAgent = 'empty', mGroup = 'empty'}"});
        query_ans_pairs.add(new String[]{"Ethylene oxide", "Substance{mId = 102, mAgent = 'Ethylene oxide', mGroup = '1'}"});
        query_ans_pairs.add(new String[]{"1.3-Dichloro-2-propanol", "Substance{mId = 197, mAgent = '1.3-Dichloro-2-propanol', mGroup = '2B'}"});
        query_ans_pairs.add(new String[]{"β-Myrcene", "Substance{mId = 309, mAgent = 'β-Myrcene', mGroup = '2B'}"});
        query_ans_pairs.add(new String[]{"Sodium <i>ortho</i>-phenylphenate", "Substance{mId = 330, mAgent = 'Sodium <i>ortho</i>-phenylphenate', mGroup = '2B'}"});
        //query_ans_pairs.add(new String[]{null, "Substance{mId = -1, mAgent = 'empty', mGroup = 'empty'}"});
        // Apparently the replaceText method do not work with a null pointer which looks logical.

        for (String[] iter : query_ans_pairs) {
            onView(withId(R.id.cancerDataQueryTextField)).perform(replaceText(iter[0]));
            onView(withId(R.id.cancerDataQueryButton)).perform(click());
            onView(withId(R.id.queryCancerDataAnswerArea)).check(matches(withText(iter[1])));
        }
        //TextView text = (TextView)mActivityRule.getActivity().findViewById(R.id.perfectQueryAnswerArea);
        //onView(withId(R.id.greetingMessage)).check(matches(withText("Hello from my unit test!")));
    }
}*/
