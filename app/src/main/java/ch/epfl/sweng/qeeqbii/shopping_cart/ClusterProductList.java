package ch.epfl.sweng.qeeqbii.shopping_cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ch.epfl.sweng.qeeqbii.open_food.ClusterType;
import ch.epfl.sweng.qeeqbii.open_food.Product;


/**
 * Created by davidcleres on 01.11.17.
 */
public class ClusterProductList {

    private Boolean do_mock_barcode_checking = Boolean.FALSE;

    private Map<ClusterType, Boolean> is_checked_item = new HashMap<>();

    private List<ClusterType> m_items = new ArrayList<>();

    public void addToShoppingCartList (ClusterType cluster) {
        m_items.add(cluster);
    }

    public static void emptyList () {
        m_items.clear();
    }

    public ShoppingCart(){
        //To avoid an empty list
        //addItemToCart(new Product("Please Click on + to add an item", "0 mg", "Stuff", "cool Nutrients"));
    }

    public ShoppingCart(List<ClusterType> items) {

        m_items = items;
        for (ClusterType cluster : m_items)
        {
            is_checked_item.put(cluster,false);
        }
    }

    public static void addItemToCart(ClusterType cluster) {
        m_items.add(cluster);
    }

    public static void addSpecificItemInCart(int index, ClusterType cluster) {
        m_items.add(index, cluster);
    }

    public static List<ClusterType> getItems() {
        return m_items;
    }

    public static ClusterType getSpecificItemInCart(int index) {
        return m_items.get(index);
    }


    public static Boolean isCheckedItem(ClusterType cluster)
    {
        return is_checked_item.get(cluster);
    }

    public static void deleteSingleItemShoppingCartList() {
        Iterator<ClusterType> element = m_items.iterator();
        //for (Product element : m_items) {
        while (element.hasNext()) {
            ClusterType cluster = element.next();
            if (is_checked_item.get(cluster))
            {
                element.remove();
            }
        }
    }

    public static void checkItem(ClusterType cluster)
    {

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
        /*for (Product prod : m_items) {
            if ((prod.getName().equals("Pizza") && barcode.equals("4001724819905")) ||
                (prod.getName().equals("Wine") && barcode.equals("8437002948153")) ||
                (prod.getName().equals("Cheese") && barcode.equals("2108726006400")))
            prod.setChecked(Boolean.TRUE);
        }*/
    }

    public static void enableMockBarcodeChecking() {
        do_mock_barcode_checking = Boolean.TRUE;
    }
}