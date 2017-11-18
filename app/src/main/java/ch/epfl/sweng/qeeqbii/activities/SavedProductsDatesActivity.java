package ch.epfl.sweng.qeeqbii.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.open_food.SavedProductsDatabase;

/**
 * Created by guillaume on 14/11/17.
 * This activity shows the list of products scanned at a given date.
 */

public class SavedProductsDatesActivity extends AppCompatActivity {

    private static final String TAG = "SavedProductsDatesActiv";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_scanned_products);


        ListView dates_saved_products= (ListView) findViewById(R.id.recently_scanned_products_list_view);

        Date[] dates = new Date[0];

        try
        {
            if (getIntent().hasExtra("test")) {
                SavedProductsDatabase.load(getApplicationContext()
                        .getResources().openRawResource(getIntent().getIntExtra("test",0)));
            } else {
                SavedProductsDatabase.load(getApplicationContext());
            }
            dates = SavedProductsDatabase.getDates();

        } catch (Exception e)
        {
            Log.d(TAG,e.getMessage());
        }

        ArrayAdapter<Date> mAdapter = new ArrayAdapter<>(this.getApplicationContext(), R.layout.list_item_recently_scanned_product,
                R.id.recently_scanned_product_text_view, dates);
        dates_saved_products.setAdapter(mAdapter);

        dates_saved_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long arg3) {
                Intent intent = new Intent(SavedProductsDatesActivity.this, SavedProductsListActivity.class);
                Date date = (Date) adapter.getItemAtPosition(position);
                intent.putExtra("date",date);
                startActivity(intent);
            }

        });
    }
}
