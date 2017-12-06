package ch.epfl.sweng.qeeqbii.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ch.epfl.sweng.qeeqbii.cancer.query.RatcliffQueryCancerDB;
import ch.epfl.sweng.qeeqbii.cancer.query.RatcliffQueryCancerDB;
import ch.epfl.sweng.qeeqbii.chat.MainActivityChat;
import ch.epfl.sweng.qeeqbii.custom_exceptions.ProductException;
import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.open_food.OpenFoodQuery;
import ch.epfl.sweng.qeeqbii.open_food.Product;
import ch.epfl.sweng.qeeqbii.open_food.RecentlyScannedProducts;
import ch.epfl.sweng.qeeqbii.shopping_cart.ShoppingCartStatistics;

/**
 * Created by guillaume on 10/10/17.
 * Activity that prints product details relative to the barcode entered
 * in the barcode text field in the MainActivity.
 */

public class BarcodeToProductActivity extends AppCompatActivity {

    private String barcode;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_details);

        Intent intent = getIntent();
        barcode = intent.getStringExtra(BarcodeScannerActivity.EXTRA_BARCODE);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.barcode_to_product);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // Capture the layout's; TextView and set the string as its text
        final TextView txt = (TextView) findViewById(R.id.product_details);
        txt.setTextSize(20);
        txt.setTextColor(Color.rgb(0, 0, 0));
        //OpenFoodQuery.ShowProduct(barcode, txt, getApplicationContext());
        try {
            Product product = OpenFoodQuery.GetOrCreateProduct(barcode, null);
            txt.setText(product.toString());
        }
        catch (Exception e) {
            System.err.print(e.getMessage());
            txt.setText(e.getMessage());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // go back to main activity after BACK button was pressed
            //Intent intent = new Intent(this, MainActivity.class);
            //startActivity(intent);
            finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    // Search for harmful ingredients contained in the product, making a query to the Cancer database.
    public void searchHarmfulIngredients(View view) {

        if (!(RecentlyScannedProducts.contains(barcode))) {
            return;
        }

        TextView txt_harmfull_ing = (TextView) findViewById(R.id.harmful_ingredients);

        String parsed_ingredients[];

        try {
            parsed_ingredients = RecentlyScannedProducts.getProduct(barcode).getParsedIngredients();
        } catch (ProductException e) {
            txt_harmfull_ing.setText(e.getMessage());
            return;
        }


        String str = "";
        RatcliffQueryCancerDB ratcliffQuery = new RatcliffQueryCancerDB();
        try {
            for (String ingredient : parsed_ingredients) {
                str += ratcliffQuery.query(ingredient).toString() + "\n";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        txt_harmfull_ing.setText(str);

    }

    // Call the Graphs activity to make generate plots from the nutrients of the product.
    public void showNutrientsGraphs(View view) {
        Intent intent = new Intent(this, GraphsActivity.class);
        intent.putExtra("barcode", barcode);
        startActivity(intent);
    }

    public void buyProduct(View view) {
        ShoppingListActivity.addProduct(RecentlyScannedProducts.getProduct(barcode));
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