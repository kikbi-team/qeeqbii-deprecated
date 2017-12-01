package ch.epfl.sweng.qeeqbii.shopping_cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.epfl.sweng.qeeqbii.RecyclerViewAdapter;
import ch.epfl.sweng.qeeqbii.open_food.Product;


/**
 * Created by davidcleres on 01.11.17.
 */
public class ShoppingCart {

    static public List<Product> m_items = new ArrayList<Product>();
    // set to true to mock barcode -> category mapping
    private static Boolean do_mock_barcode_checking = Boolean.FALSE;

    public ShoppingCart() {
        //To avoid an empty list
        //addItemToCart(new Product("Please Click on + to add an item", "0 mg", "Stuff", "cool Nutrients"));
    }

    public ShoppingCart(List<Product> items) {
        m_items = items;
    }

    public static void addToShoppingCartList(Product product) {
        m_items.add(product);
    }

    public static void emptyShoppingCartList() {
        m_items.clear();
    }

    public static void deleteSingleItemShoppingCartList() {
        Iterator<Product> element = m_items.iterator();
        //for (Product element : m_items) {
        while (element.hasNext()) {
            Product prod = element.next();
            if (prod.isChecked()) {
                element.remove();
            }
        }
    }

    public static void enableMockBarcodeChecking() {
        do_mock_barcode_checking = Boolean.TRUE;
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

    // sets a given product checked in the view
    public void checkProduct(Product product) {
        RecyclerViewAdapter.setItemChecked(product, Boolean.TRUE);
    }

    // given a barcode, check a category in the list corresponding
    // to the product of this barcode
    public void checkItemFromBarcode(String barcode) {
        if (do_mock_barcode_checking)
            checkItemFromBarcodeMock(barcode);
        else {
            // TODO: write a real method
            // instead of this mocked version
            // use OpenFood for map (barcode -> product)
            // and Clustering for for map (product -> category)
            checkItemFromBarcodeMock(barcode);
        }
    }

    // mocked version of checking items from barcode
    // includes a constant mapping barcode -> category
    // instead of using OpenFood (barcode -> product)
    // and Clustering (product -> category)
    // see checkItemFromBarcode()
    private void checkItemFromBarcodeMock(String barcode) {
        for (Product prod : m_items) {
            if ((prod.getName().equals("Pizza") && barcode.equals("4001724819905")) ||
                    (prod.getName().equals("Wine") && barcode.equals("8437002948153")) ||
                    (prod.getName().equals("Cheese") && barcode.equals("2108726006400")))
                checkProduct(prod);
        }
    }
}