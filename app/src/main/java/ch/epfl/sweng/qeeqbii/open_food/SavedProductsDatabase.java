package ch.epfl.sweng.qeeqbii.open_food;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ch.epfl.sweng.qeeqbii.R;

/**
 * Created by guillaume on 13/11/17.
 * Manage the database of products scanned History.
 */

public class SavedProductsDatabase
{
    private static DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

    private static JSONObject saved_products_json = null;

    private static Map<Date,Integer> dates_indices = new HashMap<>();

    private static Integer max_date_index;

    public static void load(Context context) throws IOException, JSONException
    {
        if (saved_products_json == null) {
            //load(context.openFileInput("saved_products_database.json"));
            load(context.getResources().openRawResource(R.raw.saved_products_database_test));
        }
    }

    public static void load(InputStream inStream) throws IOException, JSONException
    {
        try {

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            saved_products_json = new JSONObject(responseStrBuilder.toString());
            saved_products_json.getJSONArray("Dates");
            dates_indices = new HashMap<>();
        } catch(Exception e )
        {
            saved_products_json = new JSONObject().put("Dates",new JSONArray());
        }
    }

    public static Date[] getDates() throws JSONException, ParseException {
        JSONArray dates_arr = saved_products_json.getJSONArray("Dates");
        Date[] dates = new Date[dates_arr.length()];
        max_date_index = dates_arr.length() - 1;
        for (int i = 0; i < dates_arr.length(); ++i) {
            dates[i] = formatter.parse(dates_arr.getJSONObject(i).getString("date"));
            dates_indices.put(dates[i], i);
        }
        return dates;
    }

    public static Product[] getProductsFromDate(Date date) throws JSONException, IOException
    {
        Integer index = dates_indices.get(date);
        JSONArray products_json_array = saved_products_json.getJSONArray("Dates")
                .getJSONObject(index).getJSONArray("products");


        Product[] products = new Product[products_json_array.length()];
        for(int i = 0; i < products_json_array.length(); ++i)
        {
            JSONObject item = products_json_array.getJSONObject(i);
            products[i] = new Product(item.getString("name"),item.getString("quantity"),item.getString("ingredients"),
                    item.getString("nutrients"), item.getString("barcode"), ClusterTypeSecondLevel.getClusterType(item.getString("cluster type")));
        }
        return products;

    }

    public static void addProduct(Product product) throws ParseException, JSONException {
        String date_of_the_day = formatter.format(Calendar.getInstance().getTime());
        if (!dates_indices.containsKey(formatter.parse(date_of_the_day))) {
            max_date_index += 1;
            JSONObject new_json_object = new JSONObject();
            new_json_object.put("date", date_of_the_day);
            new_json_object.put("products", new JSONArray());
            saved_products_json.getJSONArray("Dates").put(max_date_index, new_json_object);
            dates_indices.put(formatter.parse(date_of_the_day), max_date_index);
        }

        JSONArray json_today_products = saved_products_json.getJSONArray("Dates")
                .getJSONObject(dates_indices.get(formatter.parse(date_of_the_day))).getJSONArray("products");

        JSONObject new_product = new JSONObject();

        new_product.put("name", product.getName());
        new_product.put("barcode", product.getBarcode());
        new_product.put("ingredients", product.getIngredients());
        new_product.put("nutrients", product.getNutrients());
        new_product.put("quantity", product.getQuantity());
        new_product.put("cluster type", product.getCluster().toString());

        json_today_products.put(json_today_products.length(), new_product);
    }

    public static void save(Context context, String filename) throws IOException
    {

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
        outputStreamWriter.write(saved_products_json.toString());
        outputStreamWriter.close();
    }
}
