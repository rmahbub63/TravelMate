package com.example.mahbub.travelmateui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.ShowSelectedPlaceActivity;
import com.example.mahbub.travelmateui.adapter.ViewPagerAdapter;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.AcomodationFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.BestSeasonFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.CategoryFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.ImageFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.OverviewFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.RangeOfCostFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.WayToGoFragment;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class ShowSelectedPlaceFragment extends RootFragment{

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_selected_place, null);

        toolbar = mainView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(ShowSelectedPlaceActivity.placeName);

        toolbar.setNavigationIcon(R.drawable.back_arrow);

        setHasOptionsMenu(true);

        toolbar.inflateMenu(R.menu.favourite);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.star) {
                    //I want to change my toolbar icon here,once when its clicked...
                    item.setIcon(R.drawable.heart_filled);
                    return true;
                }
                return false;
            }
        });

        tabLayout = mainView.findViewById(R.id.tablayout_main);
        viewPager = mainView.findViewById(R.id.viewPager_main);

        return mainView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataToViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();    //remove all items
        getActivity().getMenuInflater().inflate(R.menu.favourite, menu);
    }

    private void setDataToViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new OverviewFragment()," Overview");
        adapter.addFragment(new BestSeasonFragment()," BestSeason");
        adapter.addFragment(new RangeOfCostFragment()," RangeOfCost");
        adapter.addFragment(new WayToGoFragment()," WayToGo");
        adapter.addFragment(new ImageFragment()," Image");
        adapter.addFragment(new CategoryFragment()," Category");
        adapter.addFragment(new AcomodationFragment()," AcomodationFragment");
        viewPager.setAdapter(adapter);
    }
}
