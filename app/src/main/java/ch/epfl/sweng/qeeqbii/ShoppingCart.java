package ch.epfl.sweng.qeeqbii;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidcleres on 01.11.17.
 */

public class ShoppingCart {
    private List<Product> m_items = new ArrayList<Product>();

    public ShoppingCart(){
        //TEST
        m_items.add(new Product("Cheese", "500 mg", "Stuff", "cool Nutrients"));
        m_items.add(new Product("Wine", "500 mg", "Stuff", "cool Nutrients"));
        m_items.add(new Product("Beer", "500 mg", "Stuff", "cool Nutrients"));
        m_items.add(new Product("Cheese", "500 mg", "Stuff", "cool Nutrients"));
        m_items.add(new Product("Wine", "500 mg", "Stuff", "cool Nutrients"));
        m_items.add(new Product("Beer", "500 mg", "Stuff", "cool Nutrients"));
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
