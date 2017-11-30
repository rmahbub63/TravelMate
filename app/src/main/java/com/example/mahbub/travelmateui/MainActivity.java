package com.example.mahbub.travelmateui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.mahbub.travelmateui.adapter.ViewPagerAdapter;
import com.example.mahbub.travelmateui.fragment.HomeFragment;
import com.example.mahbub.travelmateui.fragment.MeProfileFragment;
import com.example.mahbub.travelmateui.fragment.MyPlansFragment;
import com.example.mahbub.travelmateui.fragment.SavedPlacesFragment;
import com.example.mahbub.travelmateui.model.LoginModel;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout_main);
        viewPager = findViewById(R.id.viewPager_main);

        setDataToViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setDataToViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment (new HomeFragment(),"Home");
        adapter.addFragment(new MyPlansFragment(),"My Plans");
        adapter.addFragment(new SavedPlacesFragment(),"Saved Place");
        adapter.addFragment(new MeProfileFragment(),"My Profile");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Home");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_main, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("My Plans");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_plans_main, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Saved Place");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saved_places_main, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("My Profile");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_profile_main, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }
}
