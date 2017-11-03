package ch.epfl.sweng.qeeqbii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by davidcleres on 01.11.17.
 */

public class ShoppingCart {
    private List<String> m_item = new ArrayList<String>();

    public ShoppingCart(){
        //TEST
        m_item.add("Cheese");
        m_item.add("Wine");
        m_item.add("Beer");
    }

    public void addItemToCart(String newItem) {
        m_item.add(newItem);
    }

    public void addSpecificItemInCart(int index, String name) {
        m_item.add(index, name);
    }

    public List<String> getItemsInCart() {
        return m_item;
    }
    public String getSpecificItemInCart(int index) {
        return m_item.get(index);
    }

}
