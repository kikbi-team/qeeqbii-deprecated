package ch.epfl.sweng.qeeqbii.shopping_cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import ch.epfl.sweng.qeeqbii.open_food.Product;


/**
 * Created by davidcleres on 01.11.17.
 */
public class ShoppingCart {

    private static Boolean do_mock_barcode_checking = Boolean.FALSE;

    static public List<Product> m_items = new ArrayList<Product>();

    public static void addToShoppingCartList (Product product) {
        m_items.add(product);
    }

    public static void emptyShoppingCartList () {
        m_items.clear();
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

    public static void deleteSingleItemShoppingCartList() {
        Iterator<Product> element = m_items.iterator();
        //for (Product element : m_items) {
        while (element.hasNext()) {
            Product prod = element.next();
            if (prod.isChecked())
            {
                element.remove();
            }
        }
    }

    public void checkItemFromBarcode(String barcode) {
        if(do_mock_barcode_checking)
            checkItemFromBarcodeMock(barcode);
        else {
            // TODO: write a real method
            // instead of this mocked version
            checkItemFromBarcodeMock(barcode);
        }
    }

    private void checkItemFromBarcodeMock(String barcode) {
        for (Product prod : m_items) {
            if ((prod.getName().equals("Pizza") && barcode.equals("4001724819905")) ||
                (prod.getName().equals("Wine") && barcode.equals("8437002948153")) ||
                (prod.getName().equals("Cheese") && barcode.equals("2108726006400")))
            prod.setChecked(Boolean.TRUE);
        }
    }

    public static void enableMockBarcodeChecking() {
        do_mock_barcode_checking = Boolean.TRUE;
    }
}