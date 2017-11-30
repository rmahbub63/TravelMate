package com.example.mahbub.travelmateui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahbub.travelmateui.MainActivity;
import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.SearchPlaceManualActivity;
import com.example.mahbub.travelmateui.model.LoginModel;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class HomeFragment extends Fragment{

    CardView searchPlace, explorePlace;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.activity_main_option_select,null);

        searchPlace = mainView.findViewById(R.id.card_view_search_place);

        searchPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Fragment newFragment = new SearchPlaceManualFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.viewPager_main, newFragment ); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
                Intent intent = new Intent(getContext(), SearchPlaceManualActivity.class);
                startActivity(intent);
            }
        });

        return mainView;
    }
}
