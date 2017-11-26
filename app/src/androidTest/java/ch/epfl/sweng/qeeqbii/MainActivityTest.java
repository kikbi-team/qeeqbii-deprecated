package ch.epfl.sweng.qeeqbii;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.epfl.sweng.qeeqbii.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void DisplayProductTest() {
        String barcode = "7610848337010";
        String string_nutrients = "Sel: 0.0g\nProtéines: 0.5g\nFibres alimentaires: 1.5g\nSucres: 15.0g\n" +
                "Glucides: 15.0g\nAcides gras saturées: 0.0g\nMatières grasses: 0.0g\nÉnergie (kCal): 67.0kCal\nÉnergie: 280.0kJ\n";

        String string_ingredients = "mangue (Thaïlande), eau, sucre, acidifiant (E330)";

        String string_name = "Mangue : en tranches";

        String string_quantity = "245.0g";

        String s = string_name;
        s += "\n\nIngredients: " + string_ingredients;
        s += "\n\nQuantity: " + string_quantity;
        s += "\n\nNutrients: (per 100g)\n" + string_nutrients;
        onView(withId(R.id.Barcode)).perform(typeText(barcode));
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.product_details)).check(matches(withText(s)));
    }
}