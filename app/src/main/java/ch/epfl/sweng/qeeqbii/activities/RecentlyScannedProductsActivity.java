package ch.epfl.sweng.qeeqbii.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ch.epfl.sweng.qeeqbii.open_food.Product;
import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.open_food.RecentlyScannedProducts;

public class RecentlyScannedProductsActivity extends AppCompatActivity {

    public static Map<String,String> displayed_products = new HashMap<>();

    private ArrayAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_scanned_products);


        ListView mRecentlyScannedProductsListView = (ListView) findViewById(R.id.recently_scanned_products_list_view);

        LinkedList<String> barcode_linked_list = RecentlyScannedProducts.getBarcodeList();

        //System.out.println(barcode_linked_list.getLast());


        //final ArrayList<RecentlyScannedProducts> recipeList = Recipe.getRecipesFromFile("recipes.json", this);

        ArrayList<String> listItems = new ArrayList<>();
        for(int i = 0; i < barcode_linked_list.size(); i++){
            Product current_product = RecentlyScannedProducts.getProduct(barcode_linked_list.get(i));
            listItems.add(i, current_product.getName());
            displayed_products.put(current_product.getName(),barcode_linked_list.get(i));
        }

        // The 2nd argument of the ArrayAdapter constructor is the layout of the list item
        //      The list item is duplicated for each element of the list provided as 4th argument
        // The 3rd argument of the ArrayAdapter constructor is the text view in which the text provided
        //      by the list which is the 4th argument must be printed
        mAdapter = new ArrayAdapter<>(this.getApplicationContext(), R.layout.list_item_recently_scanned_product,
                R.id.recently_scanned_product_text_view, listItems);
        mRecentlyScannedProductsListView.setAdapter(mAdapter);

    }

    //ImageButton btn = (ImageButton) findViewById(R.id.delete_recently_scanned_product_button);





    public void deleteItems(View view) {
        RecentlyScannedProducts.clear();
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }

    public void shareOnFacebookRecentlyScanned(View view)
    {
        Intent intent = new Intent(this, ShareOnFacebookActivity.class);
        view.setVisibility(View.INVISIBLE);
        ShareOnFacebookActivity.view = findViewById(R.id.recently_scanned_products);
        startActivity(intent);
    }


    public ArrayAdapter getmAdapter() {
        return mAdapter;
    }
}
