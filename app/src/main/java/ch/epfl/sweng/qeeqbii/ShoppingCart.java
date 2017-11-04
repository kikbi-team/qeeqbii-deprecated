package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by davidcleres on 01.11.17.
 */
public class ShoppingCart {

    static private List<Product> m_items = new ArrayList<Product>();

    static void addToShoppingCartList (Product product) {
        m_items.add(product);
    }

    public ShoppingCart(){
        //To avoid an empty list
        //addItemToCart(new Product("Please Click on + to add an item", "0 mg", "Stuff", "cool Nutrients"));
    }

    public ShoppingCart(List<Product> items) {
        m_items = items;
    }

    public void addItemToCart(Product product) {
        m_items.add(product);
    }

    public void addSpecificItemInCart(int index, Product product) {
        m_items.add(index, product);
    }

    public List<Product> getItemsInCart() {
        return m_items;
    }
    public Product getSpecificItemInCart(int index) {
        return m_items.get(index);
    }
}