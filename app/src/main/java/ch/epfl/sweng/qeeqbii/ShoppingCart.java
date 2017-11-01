package ch.epfl.sweng.qeeqbii;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity {

    private List<String> m_item = new ArrayList<String>();

    private RecyclerView RecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        RecyclerView = (RecyclerView)findViewById(R.id.recycler);

        //create and set layout manager for each RecyclerView
        RecyclerView.LayoutManager firstLayoutManager = new LinearLayoutManager(this);

        RecyclerView.setLayoutManager(firstLayoutManager);

        //TEST
        m_item.add("France");
        m_item.add("Germany");
        m_item.add("Sweden");
        m_item.add("England");
        m_item.add("Spain");
        m_item.add("Sweden");
        m_item.add("England");
        m_item.add("Spain");
        m_item.add("Sweden");
        m_item.add("England");
        m_item.add("Spain");
        m_item.add("Sweden");
        m_item.add("England");
        m_item.add("Spain");

        //Initializing and set adapter for each RecyclerView
        RecyclerViewAdapter firstAdapter = new RecyclerViewAdapter(this, m_item);

        RecyclerView.setAdapter(firstAdapter);
    }

    public void showShoppingList(View view) {
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
    }

    public void addItemToCart(String newItem) {
        m_item.add(newItem);
    }
}
