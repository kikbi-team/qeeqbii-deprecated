package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import java.io.Serializable;

public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_shopping_list);
    }

    public void addCheese(View view) {

        Product prod = new Product("Cheese", "500 mg", "Stuff", "cool Nutrients");
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        //intent.putExtra("product", (Parcelable) prod);
        startActivity(intent);
    }

    public void addWine(View view) {
        Product prod = new Product("Wine", "500 mg", "Stuff", "cool Nutrients");
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addMeat(View view) {
        Product prod = new Product("Meat", "500 mg", "Stuff", "cool Nutrients");
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addChip(View view) {
        Product prod = new Product("Chips", "500 mg", "Stuff", "cool Nutrients");
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addVegetable(View view) {
        Product prod = new Product("Vegetable", "500 mg", "Stuff", "cool Nutrients");
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addApple(View view) {
        Product prod = new Product("Apple", "500 mg", "Stuff", "cool Nutrients");
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }
}
