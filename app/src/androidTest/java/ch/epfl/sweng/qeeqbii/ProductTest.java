package ch.epfl.sweng.qeeqbii;

import android.app.Activity;
import android.app.Instrumentation;
import android.media.audiofx.BassBoost;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.MenuItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.CustomExceptions.ProductException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

/**
 * Created by davidcleres on 12.10.17.
 */
@RunWith(AndroidJUnit4.class)
public class ProductTest {

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void defaultConstructorTest() {
        Product prod = new Product();
        assertEquals(prod.getName(), "");
    }

    @Test
    public void getNameIdTest() {
        Date date = new Date();
        Product item = new Product("cheese", "500 mg", "Stuff", "cool Nutrients", R.drawable.cheese,
                date);
        assertEquals(item.getImageId(), R.drawable.cheese);
    }

    @Test
    public void getPaserIngredientTest() throws ProductException {
        Date date = new Date();
        Product item = new Product("cheese", "500 mg", "Stuff", "cool Nutrients", R.drawable.cheese,
                date);
        String[] ingredients = new String[]{"beans", "tomato"};
        item.setParsedIngredients(ingredients);
        assertEquals(ingredients, item.getParsedIngredients());
    }

}

