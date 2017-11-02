package ch.epfl.sweng.qeeqbii;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecentlyScannedProductsActivity extends AppCompatActivity {


    private ArrayAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_scanned_products);


        ListView mRecentlyScannedProductsListView = (ListView) findViewById(R.id.recently_scanned_products_list_view);

        LinkedList<String> barcode_linked_list = RecentlyScannedProducts.getBarcodeList();


        //final ArrayList<RecentlyScannedProducts> recipeList = Recipe.getRecipesFromFile("recipes.json", this);

        ArrayList<String> listItems = new ArrayList<>();
        listItems.add(0, "Test1");
        listItems.add(1, "Test2");
        for(int i = 2; i < barcode_linked_list.size(); i++){
            Product current_product = RecentlyScannedProducts.getProduct(barcode_linked_list.get(i));
            listItems.add(i, current_product.getName());
        }

        // The 2nd argument of the ArrayAdapter constructor is the layout of the list item
        //      The list item is duplicated for each element of the list provided as 4th argument
        // The 3rd argument of the ArrayAdapter constructor is the text view in which the text provided
        //      by the list which is the 4th argument must be printed
        mAdapter = new ArrayAdapter(this.getApplicationContext(), R.layout.list_item_recently_scanned_product,
                R.id.recently_scanned_product_text_view, listItems);
        mRecentlyScannedProductsListView.setAdapter(mAdapter);
    }

    //ImageButton btn = (ImageButton) findViewById(R.id.delete_recently_scanned_product_button);





    public void deleteItems(View view) {
        RecentlyScannedProducts.clear();
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }


    public ArrayAdapter getmAdapter() {
        return mAdapter;
    }
}
