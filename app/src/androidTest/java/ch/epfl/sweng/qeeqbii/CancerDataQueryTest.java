package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.qeeqbii.activities.CancerDataQueryActivity;
import ch.epfl.sweng.qeeqbii.cancer.CancerDataBase;
import ch.epfl.sweng.qeeqbii.cancer.CancerSubstance;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class CancerDataQueryTest {
    //public final ActivityTestRule<CancerDataQueryActivity> mActivityRule =
    //       new ActivityTestRule<>(CancerDataQueryActivity.class);
    @Rule
    public final ActivityTestRule<CancerDataQueryActivity> mActivityRule =
              new ActivityTestRule<>(CancerDataQueryActivity.class);


    @Before
    public void Initialize() {
        try {
            CancerDataBase.readCSVFile(mActivityRule.getActivity().getApplicationContext());
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }



    @Test
    public void testPerfectMatchCancerDataBase() {
        // Useful way of accessing resources
        //onView(withId(R.id.cancerDataQuery)).perform(click());

        List<String[]> query_ans_pairs = new ArrayList<>();

        query_ans_pairs.add(new String[]{"Formaldéhyde", "Substance{mId = 0, mAgent = 'Formaldéhyde', mGroup = '1'}"});
        query_ans_pairs.add(new String[]{"", "Substance{mId = -1, mAgent = 'empty', mGroup = 'empty'}"});
        query_ans_pairs.add(new String[]{"Fluorure de vinyle", "Substance{mId = 98, mAgent = 'Fluorure de vinyle', mGroup = '2A'}"});
        query_ans_pairs.add(new String[]{"Uréthane", "Substance{mId = 889, mAgent = 'Uréthane', mGroup = '2A'}"});
        //query_ans_pairs.add(new String[]{null, "Substance{mId = -1, mAgent = 'empty', mGroup = 'empty'}"});
        // Apparently the replaceText method do not work with a null pointer which looks logical.

        for (String[] iter : query_ans_pairs) {
            onView(withId(R.id.cancerDataQueryTextField)).perform(replaceText(iter[0]));
            onView(withId(R.id.cancerDataQueryButton)).perform(click());
            onView(withId(R.id.queryCancerDataAnswerArea)).check(matches(withText(iter[1])));
        }
    }


    @Test
    public void testLevenshteinCancerDataBase() {
        //onView(withId(R.id.cancerDataQuery)).perform(click());

        List<String[]> query_ans_pairs = new ArrayList<>();

        CancerSubstance levenshteinOutput1 = new CancerSubstance();
        CancerSubstance levenshteinOutput2 = new CancerSubstance();
        try {
            levenshteinOutput1 = CancerDataBase.levenshteinMatchQuery("");
            levenshteinOutput2 = CancerDataBase.levenshteinMatchQuery("caffeine");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        query_ans_pairs.add(new String[]{"", levenshteinOutput1.toString()});
        query_ans_pairs.add(new String[]{"caffeine", levenshteinOutput2.toString()});


        for (String[] iter : query_ans_pairs) {
            onView(withId(R.id.cancerDataQueryTextField)).perform(replaceText(iter[0]));
            onView(withId(R.id.levenshteinCancerDataQueryButton)).perform(click());
            onView(withId(R.id.queryCancerDataAnswerArea)).check(matches(withText(iter[1])));
        }
    }
}
