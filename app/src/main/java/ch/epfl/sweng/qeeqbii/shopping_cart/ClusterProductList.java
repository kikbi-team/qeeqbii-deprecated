package ch.epfl.sweng.qeeqbii.shopping_cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.epfl.sweng.qeeqbii.clustering.ClusterType;

/**
 * Created by gmollard on 01.12.17.
 *
 */
public class ClusterProductList {

    private Boolean do_mock_barcode_checking = Boolean.FALSE;

    private Map<ClusterType, Boolean> is_checked_item = new HashMap<>();

    private List<ClusterType> m_items = new ArrayList<>();

    public ClusterProductList(){
        //To avoid an empty list
        //addItemToCart(new Product("Please Click on + to add an item", "0 mg", "Stuff", "cool Nutrients"));
    }

    public ClusterProductList(List<ClusterType> items) {

        m_items = items;
        for (ClusterType cluster : m_items)
        {
            is_checked_item.put(cluster,false);
        }
    }

    public void addItemToList(ClusterType cluster) {
        m_items.add(cluster);
        is_checked_item.put(cluster,false);
    }

    public void addSpecificItemInList(int index, ClusterType cluster) {
        m_items.add(index, cluster);
    }

    public List<ClusterType> getItems() {
        return m_items;
    }

    public ClusterType getSpecificItemInList(int index) {
        return m_items.get(index);
    }

    public Boolean isCheckedItem(ClusterType cluster)
    {
        return is_checked_item.get(cluster);
    }

    public void deleteSingleItem(ClusterType cluster) {
        m_items.remove(cluster);
        is_checked_item.remove(cluster);
    }

    public void clear()
    {
        m_items.clear();
        is_checked_item.clear();
    }

    public void checkItem(ClusterType cluster)
    {
        is_checked_item.put(cluster,true);
    }

    public void checkOrUncheckItem(ClusterType cluster)
    {
        if (!isCheckedItem(cluster))
        {
            is_checked_item.put(cluster,true);
        } else {
            is_checked_item.put(cluster,false);
        }
    }

    public int getClusterPosition(ClusterType cluster)
    {
        return m_items.indexOf(cluster);
    }
}