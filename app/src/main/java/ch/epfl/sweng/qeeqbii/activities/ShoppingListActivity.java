package ch.epfl.sweng.qeeqbii.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.qeeqbii.R;
import ch.epfl.sweng.qeeqbii.RecyclerViewAdapter;
import ch.epfl.sweng.qeeqbii.open_food.ClusterType;
import ch.epfl.sweng.qeeqbii.open_food.Product;
import ch.epfl.sweng.qeeqbii.shopping_cart.ClusterProductList;

import static ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity.ACTION_TYPE.ACTION_SHOPPING_CART;
import static ch.epfl.sweng.qeeqbii.activities.BarcodeScannerActivity.EXTRA_ACTION;

public class ShoppingListActivity extends AppCompatActivity {

    private static ClusterProductList m_cart = new ClusterProductList();
    private static RecyclerViewAdapter mAdapter;
    private static List<Product> product_list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);

        //create and set layout manager for each RecyclerView

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Initializing and set adapter for each RecyclerView

        View.OnClickListener onclicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recyclerView.getChildLayoutPosition(v);
                String txt = m_cart.getSpecificItemInList(itemPosition).toString();
                Intent intent = new Intent(ShoppingListActivity.this, ProductListActivity.class);
                intent.putExtra("product_list", "ShoppingList");
                intent.putExtra("cluster", txt);
                startActivity(intent);
            }
        };

        mAdapter = new RecyclerViewAdapter(this.getLayoutInflater(), m_cart, onclicklistener);
        recyclerView.setAdapter(mAdapter);

    }

    public static void addCluster(ClusterType cluster)
    {
        if (!mAdapter.getClusters().contains(cluster)) {
            mAdapter.addItem(cluster);
        }
    }

    public static ClusterProductList getClusterList()
    {
        return m_cart;
    }

    public static void addProduct(Product product)
    {
        product_list.add(product);
    }

    public static List<Product> getProductList(ClusterType cluster)
    {
        List<Product> cluster_product_list = new ArrayList<>();
        for (Product prod: product_list)
        {
            if (prod.getCluster() == cluster)
            {
                cluster_product_list.add(prod);
            }
        }

        return cluster_product_list;
    }

    public void showShoppingList(View view) {
        Intent intent = new Intent(this, ShoppingCartFirstLevelActivity.class);
        startActivity(intent);
    }

    public void deleteShoppingList(View view) {
        mAdapter.clear();
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

    public void deleteSingleItem (View view) {
        mAdapter.deleteSingleItem();
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

    public void scanToCheck(View view) {
        Log.d("STATE", "Going to scan barcode");
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        intent.putExtra(EXTRA_ACTION, ACTION_SHOPPING_CART);
        startActivity(intent);
    }
}
