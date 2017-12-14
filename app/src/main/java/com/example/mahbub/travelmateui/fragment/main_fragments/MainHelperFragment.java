package com.example.mahbub.travelmateui.fragment.main_fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.adapter.CustomViewPager;
import com.example.mahbub.travelmateui.adapter.ViewPagerAdapterMain;
import com.example.mahbub.travelmateui.inrface.OnBackPressListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainHelperFragment extends Fragment {

    /**
     * TabPagerIndicator
     * <p>
     * Please refer to ViewPagerIndicator library
     */
    TabLayout tabLayout;
    CustomViewPager viewPager;
    private ViewPagerAdapterMain viewPagerAdapterMain;

    public MainHelperFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_carousel, container, false);

        tabLayout = rootView.findViewById(R.id.tablayout_main);
        viewPager = rootView.findViewById(R.id.viewPager_main);

        viewPager.setPagingEnabled(false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataToViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    /**
     * Retrieve the currently visible Tab Fragment and propagate the onBackPressed callback
     *
     * @return true = if this fragment and/or one of its associates Fragment can handle the backPress
     */
    public boolean onBackPressed() {
        // currently visible tab Fragment
        OnBackPressListener currentFragment = (OnBackPressListener) viewPagerAdapterMain.getRegisteredFragment(viewPager.getCurrentItem());

        if (currentFragment != null) {
            // lets see if the currentFragment or any of its childFragment can handle onBackPressed
//            Toast.makeText(getContext(), "If Called in MainHelperFragment onBackPressed", Toast.LENGTH_SHORT).show();
            return currentFragment.onBackPressed();
        }

        // this Fragment couldn't handle the onBackPressed call
        return false;
    }

    private void setDataToViewPager() {
        viewPagerAdapterMain = new ViewPagerAdapterMain(getChildFragmentManager());
        viewPagerAdapterMain.addFragment(new HomeFragment(), "Home");
        viewPagerAdapterMain.addFragment(new MyPlansFragment(), "My Plans");
        viewPagerAdapterMain.addFragment(new FavouritePlacesFragment(), "Favourites");
        viewPagerAdapterMain.addFragment(new MyProfileFragment(), "My Profile");
        viewPager.setAdapter(viewPagerAdapterMain);
    }

    private void setupTabIcons() {
        final TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabOne.setText("Home");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_filled, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        final TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabTwo.setText("My Plans");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_plan_blank, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        final TextView tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabThree.setText("Favourites");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saved_place_blank, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        final TextView tabFour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabFour.setText("My Profile");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_profile_blank, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == tabLayout.getTabAt(0)) {
                    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_filled, 0, 0);
                } else if (tab == tabLayout.getTabAt(1)) {
                    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_plan_filled, 0, 0);
                } else if (tab == tabLayout.getTabAt(2)) {
                    tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saved_place_filled, 0, 0);
                } else if (tab == tabLayout.getTabAt(3)) {
                    tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_profile_filled, 0, 0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab == tabLayout.getTabAt(0)) {
                    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_blank, 0, 0);
                } else if (tab == tabLayout.getTabAt(1)) {
                    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_plan_blank, 0, 0);
                } else if (tab == tabLayout.getTabAt(2)) {
                    tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saved_place_blank, 0, 0);
                } else if (tab == tabLayout.getTabAt(3)) {
                    tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_profile_blank, 0, 0);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
