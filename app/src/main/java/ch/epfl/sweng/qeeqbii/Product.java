package ch.epfl.sweng.qeeqbii;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guillaume on 01/11/17.
 * Product class contains all informations relative to a given product.
 */

class Product {

    private String mName = "";
    private String mQuantity = "";
    private String mIngredients = "";
    private String mNutrients = "";
    private String[] mParsedIngredients = null;
    private Map<String, Double> mParsedNutrients = null;

    Product() {}


    Product(String name, String quantity, String ingredients, String nutrients)
    {
        mName = name;
        mQuantity = quantity;
        mIngredients = ingredients;
        mNutrients = nutrients;
    }

    String getName()
    {
        return mName;
    }

    String getQuantity()
    {
        return mQuantity;
    }

    String getIngredients()
    {
        return mIngredients;
    }

    String getNutrients()
    {
        return mNutrients;
    }


    String[] GetParsedIngredients() throws ProductException
    {
        if(mParsedIngredients != null)
        {
            return mParsedIngredients;
        }

        if (mIngredients.matches("") | mIngredients.matches("Ingredients Not Found")) {
            throw new ProductException("Ingredient list is empty for this product: unable to execute the parsing operation.");
        }

        mParsedIngredients = mIngredients.split(",");

        return mParsedIngredients;
    }

    Map<String, Double> GetParsedNutrients() throws  ProductException {

        if (mParsedNutrients != null) {
            return mParsedNutrients;
        }

        if (mNutrients.matches("") | mNutrients.matches("Nutrients Not Found")) {
            throw new ProductException("Nutrient list is empty for this product: unable to execute the parsing operation.");

        }

        String[] parsed_nutrients = mNutrients.split("\\n");
        Map<String, Double> nutrient_map = new HashMap<>();

        for (String nut : parsed_nutrients) {
            int two_dots_index = nut.indexOf(':');
            String key = nut.substring(0, two_dots_index);
            int search_alpha = two_dots_index + 1;

            while (!Character.isLetter(nut.charAt(search_alpha))) {
                search_alpha += 1;
            }

            Double value = Double.parseDouble(nut.substring(two_dots_index + 2, search_alpha));

            String unit = nut.substring(search_alpha, nut.length());

            if (!key.contains("(" + unit + ")")) {
                key = key + " (" + unit + ")";
            }

            nutrient_map.put(key, value);

        }

        mParsedNutrients = nutrient_map;
        return nutrient_map;
    }
}
