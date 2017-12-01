package ch.epfl.sweng.qeeqbii.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.RecyclerViewAdapter;
import ch.epfl.sweng.qeeqbii.open_food.ClusterType;
import ch.epfl.sweng.qeeqbii.shopping_cart.ClusterProductList;

import static ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity.ACTION_TYPE.ACTION_SHOPPING_CART;
import static ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity.EXTRA_ACTION;

public class ShoppingCartActivity extends AppCompatActivity {

    private static ClusterProductList m_cart = new ClusterProductList();
    private static RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);

        //create and set layout manager for each RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Initializing and set adapter for each RecyclerView

        mAdapter = new RecyclerViewAdapter(this, m_cart);
        recyclerView.setAdapter(mAdapter);

        // obtain action if present
        /*Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_BARCODE)) {
            String barcode = extras.getString(EXTRA_BARCODE);
            Log.d("STATE", "Shopping cart: checking " + barcode);
            m_cart.checkItemFromBarcode(barcode);
        } else {
            Log.d("STATE", "Regular onCreate()");
        }*/
    }

    public static void addCluster(ClusterType cluster)
    {
        mAdapter.addItem(cluster);
    }

    public void showShoppingList(View view) {
        Intent intent = new Intent(this, ShoppingCartFirstLevelActivity.class);
        startActivity(intent);
    }

    public void deleteShoppingList(View view) {
        m_cart.emptyList();
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void deleteSingleItem (View view) {
        m_cart.deleteSingleItem();
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void scanToCheck(View view) {
        Log.d("STATE", "Going to scan barcode");
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        intent.putExtra(EXTRA_ACTION, ACTION_SHOPPING_CART);
        startActivity(intent);
    }
}
