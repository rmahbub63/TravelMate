package com.example.mahbub.travelmateui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mahbub.travelmateui.fragment.CulturalEventFragment;

import java.util.ArrayList;

/**
 * Created by SHAJJAD on 03-Dec-17.
 */

public class ViewPagerAdapterPlaceCategory extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    ArrayList<String> tabTitleList = new ArrayList<>();

///new
    public ViewPagerAdapterPlaceCategory(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public String getPageTitle(int position){
        return tabTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        fragmentArrayList.add(fragment);
        tabTitleList.add(title);
    }
}
