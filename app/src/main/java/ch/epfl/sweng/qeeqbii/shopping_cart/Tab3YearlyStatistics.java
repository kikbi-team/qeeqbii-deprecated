package ch.epfl.sweng.qeeqbii.shopping_cart;

/**
 * Created by davidcleres on 13.11.17.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.epfl.sweng.qeeqbii.R;

public class Tab3YearlyStatistics extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_3_shopping_cart_statistics, container, false);
        return rootView;
    }
}