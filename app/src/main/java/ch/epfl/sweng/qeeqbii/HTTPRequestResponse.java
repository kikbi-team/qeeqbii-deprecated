package ch.epfl.sweng.qeeqbii;

/**
 * Created by guillaume on 09/10/17.
 */

public class HTTPRequestResponse {
    private static String resp_body;

    public HTTPRequestResponse(String str)
    {
        resp_body = str;
    }

    public static String GetProductName(String language)
    {
        int names_ind = resp_body.indexOf("display_name_translations");
        int language_ind = resp_body.indexOf("\"" + language + "\"", names_ind);
        int two_dots_ind = resp_body.indexOf(':', language_ind);

        return resp_body.substring(two_dots_ind + 2, resp_body.indexOf('\"',two_dots_ind+2));
    }

    public static String GetProductIngredients(String language)
    {
        int ingredients_ind = resp_body.indexOf("ingredients_translation");
        int language_ind = resp_body.indexOf("\"" + language + "\"", ingredients_ind);
        int two_dots_ind = resp_body.indexOf(':', language_ind);

        return resp_body.substring(two_dots_ind + 2, resp_body.indexOf('\"',two_dots_ind+2));
    }


    public static String GetProductNutrients(String language)
    {
        String str = "";
        int nutrients_ind = resp_body.indexOf("nutrients");
        int current_index = nutrients_ind + 10;
        while (resp_body.indexOf("{",current_index) < resp_body.indexOf("}",current_index))
        {
            //current_index = resp_body.indexOf("\"",current_index)
            //str += resp_body.substring(current_index+1,resp_body.indexOf("\"",current_index));
            current_index = resp_body.indexOf("\"" + language + "\"",current_index);
            current_index = resp_body.indexOf(':', current_index);
            str += resp_body.substring(current_index + 2,resp_body.indexOf("\"",current_index+3)) +": ";
            current_index = resp_body.indexOf("\"unit\"",current_index) + 6;
            String unit = resp_body.substring(resp_body.indexOf("\"",current_index)+1,resp_body.indexOf("\"",current_index +3));
            current_index = resp_body.indexOf("\"per_hundred\"",current_index)+14;
            str += resp_body.substring(current_index, resp_body.indexOf(",",current_index)) + unit + "\n";
            current_index = resp_body.indexOf("},",current_index) +2;
        }

        return str;
    }
}