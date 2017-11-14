package ch.epfl.sweng.qeeqbii;

/**
 * Created by davidcleres on 13.11.17.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentManager;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ShoppingCartStatitisticsTest {

    @Rule
    public final ActivityTestRule<ShoppingCartStatistics> mActivityRule =
            new ActivityTestRule<>(ShoppingCartStatistics.class);

    @Test
    public void testCanOpenMonthButton() throws InterruptedException {
        onView(withId(R.id.showMonthGraph)).perform(click());
    }

    @Test
    public void CountTest() throws InterruptedException {
        FragmentManager fm = mActivityRule.getActivity().getSupportFragmentManager();
        ShoppingCartStatistics.SectionsPagerAdapter section;
        section = new ShoppingCartStatistics.SectionsPagerAdapter(fm);
        assertEquals(section.getCount(), 3);
    }

    @Test
    public void getPageTitleTest() throws InterruptedException {
        FragmentManager fm = mActivityRule.getActivity().getSupportFragmentManager();
        ShoppingCartStatistics.SectionsPagerAdapter section;
        section = new ShoppingCartStatistics.SectionsPagerAdapter(fm);
        assertNotNull(section.getPageTitle(2));
    }

    @Test
    public void getPageTitleNullTest() throws InterruptedException {
        FragmentManager fm = mActivityRule.getActivity().getSupportFragmentManager();
        ShoppingCartStatistics.SectionsPagerAdapter section;
        section = new ShoppingCartStatistics.SectionsPagerAdapter(fm);
        assertNull(section.getPageTitle(4));
    }
}