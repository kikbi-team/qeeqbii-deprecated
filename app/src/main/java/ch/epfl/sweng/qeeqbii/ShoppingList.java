package ch.epfl.sweng.qeeqbii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class ShoppingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_shopping_list);
    }
}
