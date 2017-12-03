package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.qeeqbii.activities.MainActivity;
import ch.epfl.sweng.qeeqbii.open_food.ClusterType;
import ch.epfl.sweng.qeeqbii.open_food.ClusterTypeSecondLevel;
import ch.epfl.sweng.qeeqbii.open_food.Product;
import ch.epfl.sweng.qeeqbii.shopping_cart.ClusterProductList;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidcleres on 01.11.17.
 * Test for the shopping cart
 */

public class AClusterProductListClassShould {
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    private ClusterProductList shop;

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
        shop = new ClusterProductList();
        shop.emptyList();
        assertEquals(true, shop.getItems().isEmpty());
    }

    @Test
    //be able to sense that one clicked the button
    public void addSpecificItemToList() throws InterruptedException {
        Product item = new Product("sausage", "500 mg", "Stuff", "Empty nutrients", "001", ClusterTypeSecondLevel.FROMAGES);
        ClusterProductList shop = new ClusterProductList();
        shop.addSpecificItemInList(0, item.getCluster());
        assertEquals(shop.getSpecificItemInList(0), item.getCluster());
    }

    @Test
    public void addItemToList() throws InterruptedException {
        Product item = new Product("sausage", "500 mg", "Stuff", "Empty nutrients", "001", ClusterTypeSecondLevel.FROMAGES);
        ClusterProductList shop = new ClusterProductList();
        shop.addItemToList(item.getCluster());
        List<ClusterType> items = shop.getItems();
        assertEquals(shop.getSpecificItemInList(items.size()-1).toString(), item.getCluster().toString());
    }

    @Test
    public void getItemsInList() throws InterruptedException {

        List<Product> content = new ArrayList<>();

        content.add(new Product("Cheese", "500 mg", "Stuff", "Empty nutrients", "001", ClusterTypeSecondLevel.FROMAGES));
        content.add(new Product("Wine", "500 mg", "Stuff", "Empty nutrients", "001", ClusterTypeSecondLevel.FROMAGES));
        content.add(new Product("Beer", "500 mg", "Stuff", "Empty nutrients", "001", ClusterTypeSecondLevel.FROMAGES));

        ClusterProductList shop = new ClusterProductList();
        shop.addItemToList(ClusterTypeSecondLevel.FROMAGES);
        shop.addItemToList(ClusterTypeSecondLevel.FROMAGES);
        shop.addItemToList(ClusterTypeSecondLevel.FROMAGES);

        assertEquals(shop.getItems().get(0).toString(), content.get(0).getCluster().toString());
        assertEquals(shop.getItems().get(1).toString(), content.get(1).getCluster().toString());
        assertEquals(shop.getItems().get(2).toString(), content.get(2).getCluster().toString());
    }

    @Test
    public void testConstructor() throws InterruptedException {
        Product item = new Product("sausage", "500 mg", "Stuff", "Empty nutrients", "001", ClusterTypeSecondLevel.FROMAGES);

        List<ClusterType> clusters = new ArrayList<>();
        ClusterProductList shop = new ClusterProductList(clusters);
        shop.addItemToList(item.getCluster());
        List<ClusterType> items = shop.getItems();
        assertEquals(shop.getSpecificItemInList(items.size()-1).toString(), item.getCluster().toString());
    }
}