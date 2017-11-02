package ch.epfl.sweng.qeeqbii;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

public class RecentlyScannedProductsActivity extends AppCompatActivity {


    private ListView mRecentlyScannedProductsListView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_scanned_product);


        mRecentlyScannedProductsListView = (ListView) findViewById(R.id.recently_scanned_products_list_view);

        LinkedList<String> barcode_linked_list = RecentlyScannedProducts.getBarcodeList();


        //final ArrayList<RecentlyScannedProducts> recipeList = Recipe.getRecipesFromFile("recipes.json", this);

        String[] listItems = new String[barcode_linked_list.size()+2];
        listItems[0] = "Test1";
        listItems[1] = "Test2";
        for(int i = 2; i < barcode_linked_list.size(); i++){
            Product current_product = RecentlyScannedProducts.getProduct(barcode_linked_list.get(i));
            listItems[i] = current_product.getName();
        }

        // The 2nd argument of the ArrayAdapter constructor is the layout of the list item
        //      The list item is duplicated for each element of the list provided as 4th argument
        // The 3rd argument of the ArrayAdapter constructor is the text view in which the text provided
        //      by the list which is the 4th argument must be printed
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item_recently_scanned_product, R.id.recently_scanned_product_text_view, listItems);
        mRecentlyScannedProductsListView.setAdapter(adapter);
    }














}
