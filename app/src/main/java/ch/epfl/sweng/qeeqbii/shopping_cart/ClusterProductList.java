package ch.epfl.sweng.qeeqbii.shopping_cart;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.epfl.sweng.qeeqbii.open_food.ClusterType;
import ch.epfl.sweng.qeeqbii.open_food.ClusterTypeSecondLevel;
import ch.epfl.sweng.qeeqbii.open_food.Product;

/**
 * Created by gmollard on 01.12.17.
 *
 */
public class ClusterProductList {

    private Map<ClusterType, Boolean> is_checked_item = new HashMap<>();

    private List<ClusterType> m_items = new ArrayList<>();

    private List<Product> product_list= new ArrayList<>();

    private JSONObject json = null;

    public ClusterProductList(){
        //To avoid an empty list
        //addItemToCart(new Product("Please Click on + to add an item", "0 mg", "Stuff", "cool Nutrients"));
    }

    public ClusterProductList(List<ClusterType> items) {

        m_items = items;
        for (ClusterType cluster : m_items)
        {
            is_checked_item.put(cluster,false);
        }
    }

    public ClusterProductList(String filename, Context context) {
        String[] filelist = context.fileList();
        Boolean exists = false;
        for (String file : filelist) {
            if (filename.equals(file))
                exists = true;
        }
        try {
            if (!exists)
                context.openFileOutput(filename, 0).close();

            load(filename, context);
        } catch (IOException|JSONException e) {

        }
    }

    private void load(String filename, Context context) throws IOException, JSONException
    {
        InputStream in = context.openFileInput(filename);
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        in.close();
        json = new JSONObject(responseStrBuilder.toString());
        JSONArray clusters = json.getJSONArray("Clusters");
        for (int i = 0; i < clusters.length(); ++i)
        {
            ClusterTypeSecondLevel cluster = ClusterTypeSecondLevel.getClusterType(clusters.getJSONObject(i).getString("cluster"));
            m_items.add(cluster);
            JSONArray products = clusters.getJSONObject(i).getJSONArray("products");
            for (int j = 0; j < products.length(); ++j) {
                JSONObject product = products.getJSONObject(i);
                product_list.add(new Product(product.getString("name"), product.getString("quantity"),
                        product.getString("ingredients"),product.getString("nutrient"),product.getString("barcode"), cluster));
            }
        }

    }


    public void addClusterAndSave(ClusterType cluster, Context context, String filename)
    {
        addCluster(cluster);
        save(filename, context);
    }

    public void addCluster(ClusterType cluster) {
        m_items.add(cluster);
        if (json != null)
        {
            try
            {
                JSONArray clusters = json.getJSONArray("Clusters");
                JSONObject json_cluster = new JSONObject();
                json_cluster.put("cluster", cluster.toString());
                json_cluster.put("products", new JSONArray());
                json.getJSONArray("Clusters").put( clusters.length(), json_cluster);

            } catch (JSONException e)
            {
                System.err.println(e.getMessage());
            }

        }
        is_checked_item.put(cluster,false);
    }

    public void addSpecificItemInList(int index, ClusterType cluster) {
        m_items.add(index, cluster);
    }

    public List<ClusterType> getClusters() {
        return m_items;
    }

    public ClusterType getSpecificItemInList(int index) {
        return m_items.get(index);
    }

    public Boolean isCheckedItem(ClusterType cluster)
    {
        return is_checked_item.get(cluster);
    }

    public void deleteCluster(ClusterType cluster) {
        m_items.remove(cluster);
        if (json != null) {
            try {
                JSONArray clusters = json.getJSONArray("Clusters");
                int index = -1;
                for (int i = 0; i < clusters.length(); ++i)
                {
                    if(clusters.getJSONObject(i).getString("cluster").matches(cluster.toString()))
                        index = i;
                }

                if (index != -1)
                    clusters.remove(index);

            } catch (JSONException e) {
                System.err.println(e.getMessage());
            }
        }
        is_checked_item.remove(cluster);
    }

    public void deleteClusterAndSave(ClusterType cluster, Context context, String filename) {
        deleteCluster(cluster);
        save(filename,context);
    }

    public void clear()
    {
        m_items.clear();
        is_checked_item.clear();
    }

    public void checkItem(ClusterType cluster)
    {
        is_checked_item.put(cluster,true);
    }

    public void checkOrUncheckItem(ClusterType cluster)
    {
        if (!isCheckedItem(cluster))
        {
            is_checked_item.put(cluster,true);
        } else {
            is_checked_item.put(cluster,false);
        }
    }

    public int getClusterPosition(ClusterType cluster)
    {
        return m_items.indexOf(cluster);
    }

    public void addProduct(Product product) {
        product_list.add(product);
        if (json != null)
        {
            try
            {
                JSONArray clusters = json.getJSONArray("Clusters");
                int index = -1;
                for (int i = 0; i < clusters.length(); ++i)
                {
                    if(clusters.getJSONObject(i).getString("cluster").matches(product.getCluster().toString()))
                        index = i;
                }

                JSONObject json_product = new JSONObject();
                json_product.put("name", product.getName());
                json_product.put("quantity", product.getQuantity());
                json_product.put("ingredients", product.getIngredients());
                json_product.put("nutrients", product.getNutrients());

                JSONArray products = json.getJSONArray("Clusters").getJSONObject(index).getJSONArray("products");
                products.put( products.length(), json_product);

            } catch (JSONException e)
            {
                System.err.println(e.getMessage());
            }

        }
    }

    public void addProductAndSave(Product product, Context context, String filename)
    {
        addProduct(product);
        save(filename, context);
    }

    public List<Product> getProductList(ClusterType cluster)
    {
        List<Product> cluster_product_list = new ArrayList<>();
        for (Product prod: product_list)
        {
            if (prod.getCluster() == cluster)
            {
                cluster_product_list.add(prod);
            }
        }

        return cluster_product_list;
    }

    public void save(String filename, Context context)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(json.toString());
            outputStreamWriter.close();
        } catch(IOException e)
        {
            System.err.println("Unable to save json of list: " + e.getMessage());
        }
    }


}