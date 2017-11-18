package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.qeeqbii.activities.MainActivity;
import ch.epfl.sweng.qeeqbii.open_food.Date;
import ch.epfl.sweng.qeeqbii.open_food.Product;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidcleres on 05.11.17.
 */

public class ARecyclerViewAdapterShould {

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void testConstructor() throws InterruptedException {
        Date date = new Date();
        Product item = new Product("cheese", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese);
        List<Product> products = new ArrayList<Product>();
        products.add(item);
        products.add(item);
        products.add(item);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mActivityRule.getActivity(), products);

        assertEquals(adapter.getItemCount(), 3);
        assertEquals(adapter.getProducts(), products);
        assertEquals(adapter.getActivity(), mActivityRule.getActivity());
    }
}
