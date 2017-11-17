package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;

public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }

    public void addCheese(View view) {
        Product prod = new Product("Cheese", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.cheese);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addWine(View view) {
        Product prod = new Product("Wine", "500 mg", "Stuff", "cool Nutrients","001", R.drawable.wine);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addMeat(View view) {
        Product prod = new Product("Meat", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.meat);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addChip(View view) {
        Product prod = new Product("Chips", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.chips);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addVegetable(View view) {
        Product prod = new Product("Vegetable", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.vegetables);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addApple(View view) {
        Product prod = new Product("Apple", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.apple);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addGrossery(View view) {
        Product prod = new Product("Grossery", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.boulangerie);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addSauce(View view) {
        Product prod = new Product("Sauce", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.sauce);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addPizza(View view) {
        Product prod = new Product("Pizza", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.pizza);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addCarrot(View view) {
        Product prod = new Product("Carrot", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.carot);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void addSaussage(View view) {
        Product prod = new Product("Saussage", "500 mg", "Stuff", "cool Nutrients", "001", R.drawable.saussage);
        ShoppingCart.addToShoppingCartList(prod);
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        return Actions.newView("ShoppingList", "http://[ENTER-YOUR-URL-HERE]");
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        FirebaseUserActions.getInstance().start(getIndexApiAction());
    }

    @Override
    public void onStop() {
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        FirebaseUserActions.getInstance().end(getIndexApiAction());
        super.onStop();
    }
}
