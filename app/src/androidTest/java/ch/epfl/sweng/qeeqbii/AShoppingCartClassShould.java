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

    //TEST THIS
    /*
    @Test
    //be able to sense that one clicked the button
    public void recognizesWhenCalled() throws InterruptedException {
        onView(withId(R.id.shoppingButton)).perform(click());
    }
    */

    @Test
    //be able to sense that one clicked the button
    public void addSpecificItemToList() throws InterruptedException {
        Product item = new Product("sausage", "500 mg", "Stuff", "cool Nutrients");
        ShoppingCart shop = new ShoppingCart();
        shop.addSpecificItemInCart(0, item);
        assertEquals(shop.getSpecificItemInCart(0), item);
    }

    @Test
    public void addItemToList() throws InterruptedException {
        Product item = new Product("sausage", "500 mg", "Stuff", "cool Nutrients");
        ShoppingCart shop = new ShoppingCart();
        shop.addItemToCart(item);
        List<Product> items = shop.getItemsInCart();
        assertEquals(shop.getSpecificItemInCart(items.size()-1).getName(), item.getName());
    }

    @Test
    public void getItemsInList() throws InterruptedException {

        List<Product> content = new ArrayList<Product>();
        content.add(new Product("Cheese", "500 mg", "Stuff", "cool Nutrients"));
        content.add(new Product("Wine", "500 mg", "Stuff", "cool Nutrients"));
        content.add(new Product("Beer", "500 mg", "Stuff", "cool Nutrients"));

        ShoppingCart shop = new ShoppingCart();
        shop.addItemToCart(new Product("Cheese", "500 mg", "Stuff", "cool Nutrients"));
        shop.addItemToCart(new Product("Wine", "500 mg", "Stuff", "cool Nutrients"));
        shop.addItemToCart(new Product("Beer", "500 mg", "Stuff", "cool Nutrients"));

        assertEquals(shop.getItemsInCart().get(0).getName(), content.get(0).getName());
        assertEquals(shop.getItemsInCart().get(1).getName(), content.get(1).getName());
        assertEquals(shop.getItemsInCart().get(2).getName(), content.get(2).getName());
    }
}