package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Created by davidcleres on 01.11.17.
 */

public class AShoppingCartClassShould {
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    //be able to sense that one clicked the button
    public void recognizesWhenCalled() throws InterruptedException {
        onView(withId(R.id.shoppingButton)).perform(click());
    }

    @Test
    //be able to sense that one clicked the button
    public void addSpecificItemToList() throws InterruptedException {
        String itemName = "sausage";
        ShoppingCart shop = new ShoppingCart();
        shop.addSpecificItemInCart(2, itemName);
        assertEquals(shop.getSpecificItemInCart(2), itemName);
    }

    @Test
    public void addItemToList() throws InterruptedException {
        String itemName = "sausage";
        ShoppingCart shop = new ShoppingCart();
        shop.addItemToCart(itemName);
        List<String> items = shop.getItemsInCart();
        assertEquals(shop.getSpecificItemInCart(items.size()-1), itemName);
    }

    @Test
    public void getItemsInList() throws InterruptedException {
        List<String> content = new ArrayList<String>();
        content.add("Cheese");
        content.add("Wine");
        content.add("Beer");
        ShoppingCart shop = new ShoppingCart();
        assertEquals(shop.getItemsInCart(), content);
    }
}
