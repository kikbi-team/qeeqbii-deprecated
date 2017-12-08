package ch.epfl.sweng.qeeqbii;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.activities.ShoppingListActivity;
import ch.epfl.sweng.qeeqbii.clustering.ClusterTypeFirstLevel;
import ch.epfl.sweng.qeeqbii.clustering.ClusterTypeSecondLevel;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by guillaume on 04/12/17.
 * Test of main Shopping List functionalities.
 */

@RunWith(AndroidJUnit4.class)
public class ShoppingListTest {

    @Rule
    public final IntentsTestRule<ShoppingListActivity> mActivityRule =
            new IntentsTestRule<>(ShoppingListActivity.class);

    @Test
    public void addClusterTest()
    {
        onView(withId(R.id.addButton)).perform(click());
        onView(withText(ClusterTypeFirstLevel.PETIT_DEJEUNER.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.CONFITURES_PORTIONS_DE_MIEL.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.CONFITURES.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.CACAO_CHOCOLATS_EN_POUDRE.toString())).perform(click());
        pressBack();
        onView(withText(ClusterTypeFirstLevel.BOISSONS_CHAUDES_FROIDES.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.SIROP_SODA.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).perform(click());
        pressBack();
        pressBack();

        ShoppingListActivity.checkOrUncheckItem(ClusterTypeSecondLevel.CONFITURES_PORTIONS_DE_MIEL);
        ShoppingListActivity.checkOrUncheckItem(ClusterTypeSecondLevel.CONFITURES);
        ShoppingListActivity.checkOrUncheckItem(ClusterTypeSecondLevel.SIROP_SODA);

        onView(withId(R.id.removeButton)).perform(click());
        onView(withText(ClusterTypeSecondLevel.CACAO_CHOCOLATS_EN_POUDRE.toString())).check(matches(isDisplayed()));
        onView(withText(ClusterTypeSecondLevel.CONFITURES_PORTIONS_DE_MIEL.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).check(doesNotExist());
    }

    @Test
    public void deleteAll()
    {
        onView(withId(R.id.addButton)).perform(click());
        onView(withText(ClusterTypeFirstLevel.PETIT_DEJEUNER.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.CONFITURES_PORTIONS_DE_MIEL.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.CONFITURES.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.CACAO_CHOCOLATS_EN_POUDRE.toString())).perform(click());
        pressBack();
        onView(withText(ClusterTypeFirstLevel.BOISSONS_CHAUDES_FROIDES.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.SIROP_SODA.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).perform(click());
        pressBack();
        pressBack();    

        onView(withId(R.id.deleteButton)).perform(click());
        onView(withText(ClusterTypeSecondLevel.CACAO_CHOCOLATS_EN_POUDRE.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.CONFITURES_PORTIONS_DE_MIEL.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.CONFITURES.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.SIROP_SODA.toString())).check(doesNotExist());
    }

    @AfterClass
    public static void finish_all_activities() {
        ActivityFinisher.finishOpenActivities();
    }
}
