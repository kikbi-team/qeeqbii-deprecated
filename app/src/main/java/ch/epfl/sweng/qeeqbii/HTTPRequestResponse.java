package ch.epfl.sweng.qeeqbii;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guillaume on 09/10/17.
 *
 */

public class HTTPRequestResponse {
    private String resp_body = "";

    private String ingredients = "";
    private String nutrients = "";

    public HTTPRequestResponse() {
        resp_body = "";
        ingredients = "";
        nutrients = "";
    }

    public HTTPRequestResponse(String str) throws Exception {
        if (str.charAt(0) != '{') throw new Exception(str);
        if ((str.substring(0, 10).indexOf("[]") != -1))
            throw new Exception("Request gave an empty field, the barcode seems not to be present in the database.");
        resp_body = str;
        ingredients = "";
    }

    public String GetProductName(String language) {
        int names_ind = resp_body.indexOf("display_name_translations\":");
        if (names_ind == -1) return "Name Not Found";
        int language_ind = resp_body.indexOf("\"" + language + "\"", names_ind);
        int two_dots_ind = resp_body.indexOf(':', language_ind);

        return resp_body.substring(two_dots_ind + 2, resp_body.indexOf('\"', two_dots_ind + 2)).replace("\\n", "\n").replace("\\r", "\r");
    }

    public String GetProductIngredients(String language) {
        int ingredients_ind = resp_body.indexOf("ingredients_translations\":");
        if (ingredients_ind == -1) {
            ingredients = "Ingredients Not Found";
            return ingredients;
        }
        int language_ind = resp_body.indexOf("\"" + language + "\"", ingredients_ind);
        int two_dots_ind = resp_body.indexOf(':', language_ind);


        ingredients = resp_body.substring(two_dots_ind + 2, resp_body.indexOf('\"', two_dots_ind + 2)).replace("\\n", "\n").replace("\\r", "\r");
        return ingredients;
        //We have to check if the replace is worth it
    }


    public String GetProductNutrients(String language) {
        String str = "";
        int nutrients_ind = resp_body.indexOf("nutrients\":");
        if (nutrients_ind == -1) {
            nutrients = "Nutrients Not Found";
            return nutrients;
        } else {
            int current_index = nutrients_ind + 10;
            while (resp_body.indexOf("{", current_index) < resp_body.indexOf("}", current_index)) {
                if (resp_body.indexOf("}", current_index) - resp_body.indexOf("{", current_index) == 1)
                    return "Empty nutrients";
                //current_index = resp_body.indexOf("\"",current_index)
                //str += resp_body.substring(current_index+1,resp_body.indexOf("\"",current_index));
                current_index = resp_body.indexOf("\"" + language + "\"", current_index);
                current_index = resp_body.indexOf(':', current_index);
                str += resp_body.substring(current_index + 2, resp_body.indexOf("\"", current_index + 3)) + ": ";
                current_index = resp_body.indexOf("\"unit\"", current_index) + 6;
                String unit = resp_body.substring(resp_body.indexOf("\"", current_index) + 1, resp_body.indexOf("\"", current_index + 3));
                current_index = resp_body.indexOf("\"per_hundred\"", current_index) + 14;
                str += resp_body.substring(current_index, resp_body.indexOf(",", current_index)) + unit + "\n";
                current_index = resp_body.indexOf("},", current_index) + 2;
            }

            nutrients = str.replace("\\n", "\n").replace("\\r", "\r");
            return nutrients;
        }

    }

    public String GetProductQuantity() {
        int quantity_ind = resp_body.indexOf("\"quantity\":");
        if (quantity_ind == -1) return "Quantity Not Found";
        String quantity = resp_body.substring(quantity_ind + 11, resp_body.indexOf(',', quantity_ind));
        int unit_ind = resp_body.indexOf("\"unit\"", quantity_ind);
        String unit = resp_body.substring(unit_ind + 8, resp_body.indexOf(',', unit_ind) - 1);

        return (quantity + unit).replace("\\n", "\n").replace("\\r", "\r");

    }

    public String[] ParseIngredients() {
        if (ingredients.matches("") | ingredients.matches("Ingredients Not Found")) {
            GetProductIngredients("fr");
        }

        String[] parsed_ingredients = ingredients.split(",");

        return parsed_ingredients;

    }

    public Map<String, Double> ParseNutrients() {
        if (nutrients.matches("") | nutrients.matches("Nutrients Not Found")) {
            GetProductNutrients("fr");
        }
        String[] parsed_nutrients = nutrients.split("\\n");
        Map<String, Double> nutrient_map = new HashMap<String, Double>();

        for (int i = 0; i < parsed_nutrients.length; ++i) {
            String str = parsed_nutrients[i];
            int two_dots_index = str.indexOf(':');
            String key = str.substring(0, two_dots_index);
            int search_alpha = two_dots_index + 1;

            while (!Character.isLetter(str.charAt(search_alpha))) {
                search_alpha += 1;
            }

            Double value = Double.parseDouble(str.substring(two_dots_index + 2, search_alpha));
            System.out.println(value);

            String unit = str.substring(search_alpha, str.length());

            if (key.indexOf("(" + unit + ")") == -1) {
                key = key + " (" + unit + ")";
            }

            nutrient_map.put(key, value);

        }

        return nutrient_map;


    }


}
