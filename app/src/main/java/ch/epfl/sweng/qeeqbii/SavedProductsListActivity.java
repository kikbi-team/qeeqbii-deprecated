package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guillaume on 14/11/17.
 * This activity shows the list of dates present in the history of scanned products.
 */

public class SavedProductsListActivity extends AppCompatActivity {

    private static final String TAG = "SavedProductsListActiv";

    private Map<String, Integer> name_to_index_map = new HashMap<>();

    Product[] mProducts;

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

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this.getApplicationContext(), R.layout.list_item_recently_scanned_product,
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
}
