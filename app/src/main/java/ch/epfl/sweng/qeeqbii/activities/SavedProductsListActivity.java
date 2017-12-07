package ch.epfl.sweng.qeeqbii.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.chat.MainActivityChat;
import ch.epfl.sweng.qeeqbii.open_food.RecentlyScannedProducts;
import ch.epfl.sweng.qeeqbii.open_food.SavedProductsDatabase;
import ch.epfl.sweng.qeeqbii.open_food.Product;
import ch.epfl.sweng.qeeqbii.open_food.Date;
import ch.epfl.sweng.qeeqbii.shopping_cart.ShoppingCartStatistics;

/**
 * Created by guillaume on 14/11/17.
 * This activity shows the list of dates present in the history of scanned products.
 */

public class SavedProductsListActivity extends AppCompatActivity {

    private static final String TAG = "SavedProductsListActiv";

    private Map<String, Integer> name_to_index_map = new HashMap<>();
    private ActionBarDrawerToggle mToggle;

    Product[] mProducts;

    ArrayAdapter<String> mAdapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_scanned_products);

        ListView list_saved_products= (ListView) findViewById(R.id.recently_scanned_products_list_view);

        String[] product_names = new String[0];

        try
        {
            SavedProductsDatabase.load(getApplicationContext());
            mProducts = SavedProductsDatabase.getProductsFromDate((Date) getIntent().getSerializableExtra("date"));
            product_names = new String[mProducts.length];
            int i = 0;
            for (Product prod : mProducts)
            {
                product_names[i] = prod.getName();
                name_to_index_map.put(prod.getName(), i);
                ++i;
            }
        } catch (Exception e)
        {
            Log.d(TAG,e.getMessage());
        }

        mAdapter = new ArrayAdapter<>(this.getApplicationContext(), R.layout.list_item_recently_scanned_product,
                R.id.recently_scanned_product_text_view, product_names);

        list_saved_products.setAdapter(mAdapter);

        list_saved_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long arg3) {
                Intent intent = new Intent(SavedProductsListActivity.this, ShowProductActivity.class);
                String txt = (String) adapter.getItemAtPosition(position);
                intent.putExtra("product", mProducts[name_to_index_map.get(txt)]);
                startActivity(intent);
            }

        });
    }


    public void shareOnFacebookRecentlyScanned(View view)
    {
        Intent intent = new Intent(this, ShareOnFacebookActivity.class);
        view.setVisibility(View.INVISIBLE);
        ShareOnFacebookActivity.view = findViewById(R.id.recently_scanned_products);
        startActivity(intent);
    }

    public void deleteItems(View view) {
        RecentlyScannedProducts.clear();
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }

    public ArrayAdapter getmAdapter() {
        return mAdapter;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void cancerDataBaseShow(MenuItem item) {
        Intent intent = new Intent(this, CancerDataShowActivity.class);
        startActivity(intent);
    }


    public void readBarcode(MenuItem item) {
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        startActivity(intent);
    }



    public void showShoppingList(MenuItem view) {
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

    public void showGraphs(MenuItem item) {
        Intent intent = new Intent(this, GraphsActivity.class);
        startActivity(intent);
    }

    public void cancerDataQuery(MenuItem item) {
        Intent intent = new Intent(this, CancerDataQueryActivity.class);
        startActivity(intent);
    }

    public void showRecentlyScannedProductsActivity(MenuItem item) {
        Intent intent = new Intent(this, RecentlyScannedProductsActivity.class);
        startActivity(intent);
    }

    public void backToMain(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showSavedProducts(MenuItem item) {
        Intent intent = new Intent(this, SavedProductsDatesActivity.class);
        startActivity(intent);
    }

    public void showStatistics(MenuItem item) {
        Intent intent = new Intent(this, ShoppingCartStatistics.class);
        startActivity(intent);
    }

    public void showChat(MenuItem item) {
        Intent intent = new Intent(this, MainActivityChat.class);
        startActivity(intent);
    }
}
