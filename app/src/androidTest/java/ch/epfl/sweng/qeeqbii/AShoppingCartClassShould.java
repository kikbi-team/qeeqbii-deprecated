package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.qeeqbii.open_food.Product;


import static org.junit.Assert.assertEquals;

/**
 * Created by davidcleres on 01.11.17.
 * Test for the shopping cart
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
    public void testDefaultConstructor() throws InterruptedException {
        ShoppingCart shop = new ShoppingCart();
        ShoppingCart.emptyShoppingCartList();
        assertEquals(true, shop.getItemsInCart().isEmpty());
    }

    @Test
    //be able to sense that one clicked the button
    public void addSpecificItemToList() throws InterruptedException {
        Product item = new Product("sausage", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese);
        ShoppingCart shop = new ShoppingCart();
        shop.addSpecificItemInCart(0, item);
        assertEquals(shop.getSpecificItemInCart(0), item);
    }

    @Test
    public void addItemToList() throws InterruptedException {
        Product item = new Product("sausage", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese);
        ShoppingCart shop = new ShoppingCart();
        shop.addItemToCart(item);
        List<Product> items = shop.getItemsInCart();
        assertEquals(shop.getSpecificItemInCart(items.size()-1).getName(), item.getName());
    }

    @Test
    public void getItemsInList() throws InterruptedException {

        List<Product> content = new ArrayList<>();

        content.add(new Product("Cheese", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese));
        content.add(new Product("Wine", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese));
        content.add(new Product("Beer", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese));

        ShoppingCart shop = new ShoppingCart();
        shop.addItemToCart(new Product("Cheese", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese));
        shop.addItemToCart(new Product("Wine", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese));
        shop.addItemToCart(new Product("Beer", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese));

        assertEquals(shop.getItemsInCart().get(0).getName(), content.get(0).getName());
        assertEquals(shop.getItemsInCart().get(1).getName(), content.get(1).getName());
        assertEquals(shop.getItemsInCart().get(2).getName(), content.get(2).getName());
    }

    @Test
    public void testConstructor() throws InterruptedException {
        Product item = new Product("sausage", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese);

        List<Product> products = new ArrayList<>();
        ShoppingCart shop = new ShoppingCart(products);
        shop.addItemToCart(item);
        List<Product> items = shop.getItemsInCart();
        assertEquals(shop.getSpecificItemInCart(items.size()-1).getName(), item.getName());
    }
}