package com.example.mahbub.travelmateui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.mahbub.travelmateui.adapter.ViewPagerAdapter;
import com.example.mahbub.travelmateui.fragment.main_fragments.FavouritePlacesFragment;
import com.example.mahbub.travelmateui.fragment.main_fragments.MyPlansFragment;
import com.example.mahbub.travelmateui.fragment.ShowSelectedPlaceFragment;
import com.example.mahbub.travelmateui.fragment.main_fragments.MyProfileFragment;

public class ShowSelectedPlaceActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    public static String placeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_carousel);

        tabLayout = findViewById(R.id.tablayout_main);
        viewPager = findViewById(R.id.viewPager_main);

        placeName = getIntent().getStringExtra("place_name");

        setDataToViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setDataToViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment (new ShowSelectedPlaceFragment(),"Home");
        adapter.addFragment(new MyPlansFragment(),"My Plans");
        adapter.addFragment(new FavouritePlacesFragment(),"Saved PlaceModel");
        adapter.addFragment(new MyProfileFragment(),"My Profile");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        final TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Home");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_filled, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        final TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("My Plans");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_plan_blank, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        final TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Favourites");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saved_place_blank, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        final TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("My Profile");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_profile_blank, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab == tabLayout.getTabAt(0)){
                    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_filled, 0, 0);
                } else if(tab == tabLayout.getTabAt(1)){
                    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_plan_filled, 0, 0);
                } else if(tab == tabLayout.getTabAt(2)){
                    tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saved_place_filled, 0, 0);
                } else if(tab == tabLayout.getTabAt(3)){
                    tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_profile_filled, 0, 0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab == tabLayout.getTabAt(0)){
                    tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_blank, 0, 0);
                } else if(tab == tabLayout.getTabAt(1)){
                    tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_plan_blank, 0, 0);
                } else if(tab == tabLayout.getTabAt(2)){
                    tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saved_place_blank, 0, 0);
                } else if(tab == tabLayout.getTabAt(3)){
                    tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_profile_blank, 0, 0);
                }
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
