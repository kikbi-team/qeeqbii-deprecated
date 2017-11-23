package ch.epfl.sweng.qeeqbii.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.RecyclerViewAdapter;
import ch.epfl.sweng.qeeqbii.open_food.Product;
import ch.epfl.sweng.qeeqbii.shopping_cart.ShoppingCart;

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
}
