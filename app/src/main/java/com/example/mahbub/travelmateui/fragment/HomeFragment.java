package com.example.mahbub.travelmateui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mahbub.travelmateui.R;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class HomeFragment extends RootFragment{

    CardView searchPlace, explorePlace;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_home, container, false);

        searchPlace = mainView.findViewById(R.id.card_view_search_place);

        searchPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterNextFragment();
            }
        });
        return mainView;
    }

    private void enterNextFragment() {
            SearchPlaceManualFragment searchPlaceManualFragment = new SearchPlaceManualFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();


            transaction.replace(R.id.home_fragment, searchPlaceManualFragment);
            // Store the Fragment in stack
            transaction.addToBackStack(null);
            transaction.commit();
    }
}
