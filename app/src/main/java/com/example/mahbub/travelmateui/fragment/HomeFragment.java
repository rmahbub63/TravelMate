package com.example.mahbub.travelmateui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahbub.travelmateui.MyTourPlan;
import com.example.mahbub.travelmateui.R;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class HomeFragment extends RootFragment{

    CardView searchPlace,makeTourPlan, explorePlace;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_home, container, false);

        searchPlace = mainView.findViewById(R.id.card_view_search_place);
        makeTourPlan = mainView.findViewById(R.id.makeTourPlan);

        searchPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterNextFragment();
            }
        });
        //shajjad the failure
//        makeTourPlan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), MyTourPlan.class);
//                startActivity(intent);
//            }
//        });
        return mainView;
    }

    private void enterNextFragment() {
            SearchPlaceManualFragment searchPlaceManualFragment = new SearchPlaceManualFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_main, searchPlaceManualFragment);
            // Store the Fragment in stack
            transaction.addToBackStack(null);
            transaction.commit();
    }
}
