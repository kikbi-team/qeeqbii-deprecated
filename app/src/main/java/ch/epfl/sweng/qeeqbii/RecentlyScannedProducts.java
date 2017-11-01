package ch.epfl.sweng.qeeqbii;

import java.util.ArrayList;
import java.util.List;


class RecentlyScannedProducts {




    static private ArrayList<Product> mRecentlyScannedProductsList;
    static private ArrayList<String> mProductNameList;
    static int mNumberProducts;

    static public Product getProductFromIndex(int index) {
        return mRecentlyScannedProductsList.get(index);
    }

    static public ArrayList<String> getProductNameList() {
        return mProductNameList;
    }

}
