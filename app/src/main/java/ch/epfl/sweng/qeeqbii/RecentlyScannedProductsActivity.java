package ch.epfl.sweng.qeeqbii;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecentlyScannedProductsActivity extends AppCompatActivity {

    private ListView mRecentlyScannedProductsListView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_scanned_product);


        mRecentlyScannedProductsListView = (ListView) findViewById(R.id.recently_scanned_products_list_view);

        //ArrayList<String> recentlyScannedProductsNameList = RecentlyScannedProducts.getProductList();


        //final ArrayList<RecentlyScannedProducts> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
        int size = 100;
        String[] listItems = new String[size];
        for(int i = 0; i < size; i++){
            //Product currentProduct =
            //String productName = recentlyScannedProductsNameList.get(i);
            listItems[i] = "lourd";
        }


        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item_recently_scanned_product, R.id.recently_scanned_product_text_view, listItems);
        mRecentlyScannedProductsListView.setAdapter(adapter);
    }



}
