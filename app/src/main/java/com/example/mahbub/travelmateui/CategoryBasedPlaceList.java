package com.example.mahbub.travelmateui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mahbub.travelmateui.adapter.ViewPagerAdapterPlaceCategory;
import com.example.mahbub.travelmateui.fragment.CulturalEventFragment;
import com.example.mahbub.travelmateui.fragment.HistoricalFragment;
import com.example.mahbub.travelmateui.fragment.MuseumFragment;
import com.example.mahbub.travelmateui.fragment.NaturalFragment;
import com.example.mahbub.travelmateui.fragment.PopularFragment;
import com.example.mahbub.travelmateui.fragment.ReligiousFragment;
import com.example.mahbub.travelmateui.fragment.SpecialPlaceFragment;

public class CategoryBasedPlaceList extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_based_place_list);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewPager);

        setSupportActionBar(toolbar);
        seDataToViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }

    private void seDataToViewPager() {
        ViewPagerAdapterPlaceCategory adapter = new ViewPagerAdapterPlaceCategory(getSupportFragmentManager());

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
