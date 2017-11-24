package ch.epfl.sweng.qeeqbii.open_food;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ch.epfl.sweng.qeeqbii.custom_exceptions.ProductException;

/**
 * Created by guillaume on 01/11/17.
 * Product class contains all informations relative to a given product.
 */

public class Product implements Serializable {
    private String mName = "";
    private String mQuantity = "";
    private String mIngredients = "";
    private String mNutrients = "";
    private String[] mParsedIngredients = null;
    private Map<String, Double> mParsedNutrients = null;
    private ClusterType mType = ClusterTypeSecondLevel.UNDETERMINED;
    private String mBarcode = "";
    private Boolean mIsChecked = false;

    public Product() {}
    
    public Product(String name, String quantity, String ingredients, String nutrients, String barcode, ClusterType type)
    {
        mName = name;
        mQuantity = quantity;
        mIngredients = ingredients;
        mNutrients = nutrients;
        mBarcode = barcode;
        mType = type;
        mIsChecked = false;
    }

    public String getName()
    {
        return mName;
    }

    public String getQuantity()
    {
        return mQuantity;
    }

    public String getIngredients()
    {
        return mIngredients;
    }

    public String getNutrients()
    {
        return mNutrients;
    }

    public String getBarcode() { return mBarcode; }

    public int getImageId() { return mType.getImageId(); }

    public ClusterType getCluster() { return mType; }

    public String[] getParsedIngredients() throws ProductException
    // Returns an array of string. Each entry of the array corresponds to an ingredient.
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

    public Map<String, Double> getParsedNutrients() throws  ProductException {
    // Returns a map binding a nutrient to its quantity.
    // Key entered in the map can be e.g. "Sel (g)" or "Sucres (g)"
    // The quantity is returned as a double.

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

    @Override
    public String toString() {
        String s = getName();
        s += "\n\nIngredients: " + getIngredients();
        s += "\n\nQuantity: " + getQuantity();
        s += "\n\nNutrients: (per 100g)\n" + getNutrients();

        return s;
    }

    public void setParsedIngredients(String[] parsedIngredients) {
        this.mParsedIngredients = parsedIngredients;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(Boolean isChecked) {
        mIsChecked = isChecked;
    }
}
