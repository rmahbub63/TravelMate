package com.example.mahbub.travelmateui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.adapter.ViewPagerAdapterPlaceCategory;
import com.example.mahbub.travelmateui.fragment.explore_places_fragments.CulturalEventFragment;
import com.example.mahbub.travelmateui.fragment.explore_places_fragments.HistoricalFragment;
import com.example.mahbub.travelmateui.fragment.explore_places_fragments.MuseumFragment;
import com.example.mahbub.travelmateui.fragment.explore_places_fragments.NaturalFragment;
import com.example.mahbub.travelmateui.fragment.explore_places_fragments.PopularFragment;
import com.example.mahbub.travelmateui.fragment.explore_places_fragments.ReligiousFragment;
import com.example.mahbub.travelmateui.fragment.explore_places_fragments.SpecialPlaceFragment;
import com.example.mahbub.travelmateui.fragment.main_fragments.RootFragment;
import com.example.mahbub.travelmateui.model.DivisionModel;

import java.util.ArrayList;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class ExplorePlaceFragment extends RootFragment {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Spinner divisionSpinner, districtSpinner;

    ArrayList<DivisionModel> divisions;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_explore_places_from_home_fragment, null);

        toolbar = mainView.findViewById(R.id.toolbar);
        tabLayout = mainView.findViewById(R.id.tabs);
        viewPager = mainView.findViewById(R.id.viewPager);
        divisionSpinner = mainView.findViewById(R.id.divisionSpinner);
        districtSpinner = mainView.findViewById(R.id.districtSpinner);

        divisions = new ArrayList<>();
        divisions.add(new DivisionModel("Barisal"));
        divisions.add(new DivisionModel("Chittagong"));
        divisions.add(new DivisionModel("Dhaka"));
        divisions.add(new DivisionModel("Khulna"));
        divisions.add(new DivisionModel("Mymensingh"));
        divisions.add(new DivisionModel("Rajshahi"));
        divisions.add(new DivisionModel("Rangpur"));
        divisions.add(new DivisionModel("Sylhet"));

        ArrayAdapter<DivisionModel> adapter = new ArrayAdapter<DivisionModel>(getContext(), R.layout.spinner_text, divisions);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        divisionSpinner.setAdapter(adapter);
        districtSpinner.setAdapter(adapter);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        seDataToViewPager();
        tabLayout.setupWithViewPager(viewPager);

        return mainView;
    }

    private void seDataToViewPager() {
        ViewPagerAdapterPlaceCategory adapter = new ViewPagerAdapterPlaceCategory(getChildFragmentManager());
        adapter.addFragment(new HistoricalFragment(),"Historical");
        adapter.addFragment(new MuseumFragment(),"Museum");
        adapter.addFragment(new NaturalFragment(),"Natural");
        adapter.addFragment(new PopularFragment(),"Popular");
        adapter.addFragment(new ReligiousFragment(),"Religious");
        adapter.addFragment(new SpecialPlaceFragment(),"Special Places");
        adapter.addFragment(new CulturalEventFragment(),"Cultural Event");
        viewPager.setAdapter(adapter);
    }
}
