package ch.epfl.sweng.qeeqbii;

/**
 * Created by davidcleres on 13.11.17.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab1MonthlyStatistics extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1_shopping_cart_statistics, container, false);
        //Intent intent = new Intent(getActivity(), GraphsActivity.class);
        //startActivity(intent);
        return rootView;
    }
}

