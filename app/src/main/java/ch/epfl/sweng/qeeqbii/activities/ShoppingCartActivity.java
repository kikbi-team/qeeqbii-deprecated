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
import ch.epfl.sweng.qeeqbii.open_food.Product;
import ch.epfl.sweng.qeeqbii.shopping_cart.ShoppingCart;

import static ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity.ACTION_TYPE.ACTION_SHOPPING_CART;
import static ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity.EXTRA_ACTION;
import static ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity.EXTRA_BARCODE;

public class ShoppingCartActivity extends AppCompatActivity {

    private ShoppingCart m_cart = new ShoppingCart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        //create and set layout manager for each RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Initializing and set adapter for each RecyclerView
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, m_cart.getItemsInCart());
        recyclerView.setAdapter(adapter);

        // obtain action if present
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_BARCODE)) {
            String barcode = extras.getString(EXTRA_BARCODE);
            Log.d("STATE", "Shopping cart: checking " + barcode);
        } else {
            Log.d("STATE", "Regular onCreate()");
        }
    }

    public void showShoppingList(View view) {
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

    public void deleteShoppingList(View view) {
        ShoppingCart.emptyShoppingCartList();
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void deleteSingleItem (View view) {
        ShoppingCart.deleteSingleItemShoppingCartList();
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
