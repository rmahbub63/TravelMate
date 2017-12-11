package com.example.mahbub.travelmateui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.ShowSelectedPlaceActivity;
import com.example.mahbub.travelmateui.adapter.ViewPagerAdapter;
import com.example.mahbub.travelmateui.fragment.main_fragments.RootFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.AcomodationFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.BestSeasonFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.CategoryFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.ImageFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.OverviewFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.RangeOfCostFragment;
import com.example.mahbub.travelmateui.fragment.selected_place_fragments.WayToGoFragment;
import com.example.mahbub.travelmateui.model.PlaceModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class ShowSelectedPlaceFragment extends RootFragment {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout linearLayout;
    ScrollView scrollView;
    // Database object
    FirebaseDatabase database;

    // For show data
    TextView valueTV;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_selected_place, null);
        toolbar = mainView.findViewById(R.id.toolbar);
        linearLayout = mainView.findViewById(R.id.fragment_selected_linear_layout);
        scrollView = mainView.findViewById(R.id.fragment_selected_scrollView);

        // provide reference to database
        database = FirebaseDatabase.getInstance();

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if (ShowSelectedPlaceActivity.placeName != null) {
            activity.getSupportActionBar().setTitle(ShowSelectedPlaceActivity.placeName);
        } else {
            activity.getSupportActionBar().setTitle("BACK");
        }

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

        // get data from firebase
        getPlaceModel();

        tabLayout = mainView.findViewById(R.id.tablayout_main);
        viewPager = mainView.findViewById(R.id.viewPager_main);

        //
        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Way To Go"));

        //
        valueTV = new TextView(getContext());
        valueTV.setTextSize(24);
        valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(valueTV);

        final TextView newEdittext = new TextView(getContext());
        newEdittext.setText("222222222222222222222222222 hallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo halla" +
                "hallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo halla" +
                "hallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo halla");
        newEdittext.setTextSize(24);
        newEdittext.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(newEdittext);

        TextView newEdittext3 = new TextView(getContext());
        newEdittext3.setText("333333333333333333333 hallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo halla" +
                "hallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo hallahallo halla");
        newEdittext3.setTextSize(24);
        newEdittext3.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(newEdittext3);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY(); // For ScrollView
//                Toast.makeText(getContext(), String.valueOf(textViewTwo.getTop()), Toast.LENGTH_SHORT).show();
//                Log.i("CheckValue", "Text 2 = " + String.valueOf(newEdittext.getTop()) + " View Y = " + String.valueOf(scrollY));
                if (newEdittext.getTop() < scrollY){
//                    Log.i("CheckValue", "Gone");
                    TabLayout.Tab tab = tabLayout.getTabAt(1);
                    tab.select();
                }
            }
        });

        return mainView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setDataToViewPager();
//        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();    //remove all items
        getActivity().getMenuInflater().inflate(R.menu.favourite, menu);
    }

    public void getPlaceModel() {

        DatabaseReference getPlaceByIdReference = database.getReference("places").child(ShowSelectedPlaceActivity.placeId);
        getPlaceByIdReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String placeName = dataSnapshot.child("placeName").getValue().toString();
                PlaceModel placeModel = dataSnapshot.getValue(PlaceModel.class);
                setDatainUI(placeModel);
                Toast.makeText(getContext(), placeModel.getPlaceName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // If cancelled
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setDatainUI(PlaceModel placeModel){
        valueTV.setText(placeModel.getOverView());
    }

    private void setDataToViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new OverviewFragment(), " Overview");
        adapter.addFragment(new BestSeasonFragment(), " BestSeason");
        adapter.addFragment(new RangeOfCostFragment(), " RangeOfCost");
        adapter.addFragment(new WayToGoFragment(), " WayToGo");
        adapter.addFragment(new ImageFragment(), " Image");
        adapter.addFragment(new CategoryFragment(), " Category");
        adapter.addFragment(new AcomodationFragment(), " AcomodationFragment");
        viewPager.setAdapter(adapter);
    }
}
