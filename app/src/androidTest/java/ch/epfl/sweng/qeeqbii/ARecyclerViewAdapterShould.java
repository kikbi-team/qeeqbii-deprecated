package ch.epfl.sweng.qeeqbii;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.AppCompatActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
