package ch.epfl.sweng.qeeqbii;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.spec.ECField;
import java.util.concurrent.CountDownLatch;

import ch.epfl.sweng.qeeqbii.activities.SavedProductsDatesActivity;
import ch.epfl.sweng.qeeqbii.activities.ShoppingCartSecondLevelActivity;
import ch.epfl.sweng.qeeqbii.activities.ShoppingListActivity;
import ch.epfl.sweng.qeeqbii.open_food.ClusterType;
import ch.epfl.sweng.qeeqbii.open_food.ClusterTypeFirstLevel;
import ch.epfl.sweng.qeeqbii.open_food.ClusterTypeSecondLevel;
import ch.epfl.sweng.qeeqbii.open_food.Product;

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
import static junit.framework.Assert.fail;

/**
 * Created by guillaume on 04/12/17.
 * Test of main Shopping List functionalities.
 */

@RunWith(AndroidJUnit4.class)
public class ShoppingListTest {

    @Rule
    public IntentsTestRule<ShoppingListActivity> mActivityRule =
            new IntentsTestRule<>(ShoppingListActivity.class);

    @Before
    public void loadTestDatabase() {
        ShoppingListActivity.load(mActivityRule.getActivity().getApplicationContext(), "shopping_list_test_3.json");
        final CountDownLatch rule_signal = new CountDownLatch(1);
        try{
            mActivityRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mActivityRule.getActivity().recreate();
                    } catch (java.lang.Throwable e)
                    {
                        System.err.println(e.getMessage());
                    } finally {
                        rule_signal.countDown();
                    }
                }
            });
        } catch (java.lang.Throwable e)
        {
            System.err.println(e.getMessage());
        }

        try {
            rule_signal.await(); //wait for callback
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }


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
        onView(withText(ClusterTypeSecondLevel.SIROPS_SODAS.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).perform(click());
        pressBack();
        pressBack();

        ShoppingListActivity.checkOrUncheckItem(ClusterTypeSecondLevel.CONFITURES_PORTIONS_DE_MIEL);
        ShoppingListActivity.checkOrUncheckItem(ClusterTypeSecondLevel.CONFITURES);
        ShoppingListActivity.checkOrUncheckItem(ClusterTypeSecondLevel.SIROPS_SODAS);

        onView(withId(R.id.removeButton)).perform(click());
        onView(withText(ClusterTypeSecondLevel.CACAO_CHOCOLATS_EN_POUDRE.toString())).check(matches(isDisplayed()));
        onView(withText(ClusterTypeSecondLevel.CONFITURES_PORTIONS_DE_MIEL.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).check(doesNotExist());

        onView(withId(R.id.deleteButton)).perform(click());
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
        onView(withText(ClusterTypeSecondLevel.SIROPS_SODAS.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).perform(click());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).perform(click());
        pressBack();
        pressBack();    

        onView(withId(R.id.deleteButton)).perform(click());
        onView(withText(ClusterTypeSecondLevel.CACAO_CHOCOLATS_EN_POUDRE.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.CONFITURES_PORTIONS_DE_MIEL.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.BOISSONS_ENERGETIQUES.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.CONFITURES.toString())).check(doesNotExist());
        onView(withText(ClusterTypeSecondLevel.SIROPS_SODAS.toString())).check(doesNotExist());

        onView(withId(R.id.deleteButton)).perform(click());
    }

    @Test
    public void JSONManagement()
    {
        final Product product1 = new Product("Tartiflette", "4000g", "Rebloch, Rebloch, Rebloch",
                "Sel: 0.65g", "0000000000", ClusterTypeSecondLevel.FROMAGES);
        final Product product2 = new Product("Tartiflette again", "4000g", "Rebloch, Rebloch, Rebloch et Reblochh",
                "Sel: 0.650g", "0000000001", ClusterTypeSecondLevel.FROMAGES);
        final Product product3 = new Product("Milka tendre au lait", "100g", "Chacolat, lait",
                "Sucres: 0.65g", "0000000002", ClusterTypeSecondLevel.CHOCOLAT);

        final CountDownLatch signal = new CountDownLatch(1);
        try{
            mActivityRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ShoppingListActivity.addProduct(product1, mActivityRule.getActivity().getApplicationContext());
                        ShoppingListActivity.addProduct(product2, mActivityRule.getActivity().getApplicationContext());
                        ShoppingListActivity.addProduct(product3, mActivityRule.getActivity().getApplicationContext());
                        System.out.println("///////////////////////////////////////////////////");
                    } catch (java.lang.Throwable e)
                    {
                        System.err.println("///////////////////////////////////////////" +e.getMessage());
                    } finally {
                        signal.countDown();
                    }
                }
            });
        } catch (java.lang.Throwable e)
        {
            System.err.println(e.getMessage());
        }


        final CountDownLatch signal2 = new CountDownLatch(1);

        try {
            signal.await(); //wait for callback
            assertEquals(ShoppingListActivity.getClusterProductList().getClusters().get(0), product1.getCluster());
            assertEquals(ShoppingListActivity.getClusterProductList().getClusters().get(1), product3.getCluster());

            mActivityRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ShoppingListActivity.deleteCluster(ClusterTypeSecondLevel.FROMAGES, mActivityRule.getActivity().getApplicationContext());
                        ShoppingListActivity.deleteCluster(ClusterTypeSecondLevel.CHOCOLAT, mActivityRule.getActivity().getApplicationContext());
                    } catch (java.lang.Throwable e)
                        {
                            System.err.println(e.getMessage());
                        } finally {
                            signal2.countDown();
                        }
                }
            });

        } catch (Throwable e) {
            fail(e.getMessage());
        }

        try {
            signal2.await(); //wait for callback
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(ShoppingListActivity.getClusterProductList().getClusters().size(),0);
        assertEquals(ShoppingListActivity.getClusterProductList().getProductList().size(),0);



    }


}
