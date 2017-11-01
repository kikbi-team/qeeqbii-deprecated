package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/*
public class ShoppingCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ListActivity
        //ArrayAdapter<ModelForShoppingCart> adapter = new InteractiveArrayAdapter(this,
        //        getModel());
        //setListAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShoppingList(view);
            }
        });
    }

    public void showShoppingList(View view) {
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
    }

    private List<ItemInShoppingCart> getModel() {
        List<ItemInShoppingCart> list = new ArrayList<ItemInShoppingCart>();
        list.add(get("Linux"));
        list.add(get("Windows7"));
        list.add(get("Suse"));
        list.add(get("Eclipse"));
        list.add(get("Ubuntu"));
        list.add(get("Solaris"));
        list.add(get("Android"));
        list.add(get("iPhone"));
        // Initially select one of the items
        list.get(1).setSelected(true);
        return list;
    }

    private ItemInShoppingCart get(String s) {
        return new ItemInShoppingCart(s);
    }

}
*/

public class ShoppingCart extends AppCompatActivity {

    private String[] asiaCountries = {"Vietnam", "China", "Japan", "Korea", "India", "Singapore", "Thailand", "Malaysia"};
    private String[] europeCountries = {"France", "Germany", "Sweden", "Denmark", "England", "Spain", "Portugal", "Norway"};

    private RecyclerView firstRecyclerView;
    private RecyclerView secondRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        firstRecyclerView = (RecyclerView)findViewById(R.id.recycler);
        secondRecyclerView = (RecyclerView)findViewById(R.id.recycler_1);

        //create and set layout manager for each RecyclerView
        RecyclerView.LayoutManager firstLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager secondLayoutManager = new LinearLayoutManager(this);

        firstRecyclerView.setLayoutManager(firstLayoutManager);
        secondRecyclerView.setLayoutManager(secondLayoutManager);

        //Initializing and set adapter for each RecyclerView
        RecyclerViewAdapter firstAdapter = new RecyclerViewAdapter(this, asiaCountries);
        RecyclerViewAdapter secondAdapter = new RecyclerViewAdapter(this, europeCountries);

        firstRecyclerView.setAdapter(firstAdapter);
        secondRecyclerView.setAdapter(secondAdapter);
    }

    public void showShoppingList(View view) {
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
    }
}
