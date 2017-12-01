package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.qeeqbii.activities.MainActivity;
import ch.epfl.sweng.qeeqbii.open_food.ClusterType;
import ch.epfl.sweng.qeeqbii.open_food.ClusterTypeSecondLevel;
import ch.epfl.sweng.qeeqbii.open_food.Date;
import ch.epfl.sweng.qeeqbii.open_food.Product;
import ch.epfl.sweng.qeeqbii.shopping_cart.ClusterProductList;

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
        Product item = new Product("cheese", "500 mg", "Stuff", "cool Nutrients", "001", ClusterTypeSecondLevel.FROMAGES);
        List<ClusterType> clusters = new ArrayList<>();
        clusters.add(item.getCluster());
        clusters.add(item.getCluster());
        clusters.add(item.getCluster());

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mActivityRule.getActivity(), new ClusterProductList(clusters));

        assertEquals(adapter.getItemCount(), 3);
        assertEquals(adapter.getClusters(), clusters);
        assertEquals(adapter.getActivity(), mActivityRule.getActivity());
    }
}
