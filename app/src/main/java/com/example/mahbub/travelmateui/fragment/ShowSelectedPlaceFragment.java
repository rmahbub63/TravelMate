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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.ShowSelectedPlaceActivity;
import com.example.mahbub.travelmateui.fragment.main_fragments.RootFragment;
import com.example.mahbub.travelmateui.model.PlaceModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    int nextTop, prevTop;

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

        tabLayout = mainView.findViewById(R.id.tablayout_main);
        viewPager = mainView.findViewById(R.id.viewPager_main);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY(); // For ScrollView
                int count = linearLayout.getChildCount();
                int currentTabPosition = tabLayout.getSelectedTabPosition();

//                for (int i = 0; i<count; i++){
//                    if (currentTabPosition == i) {
//                        Log.i("CheckValue", "Called");
//                        performAction(currentTabPosition, scrollY);
//                    }
//                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab == tabLayout.getTabAt(5)){
                    TextView v = (TextView) linearLayout.getChildAt(8);
                    v.setFocusable(true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return mainView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // get data from firebase
        getPlaceModel();
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
                if(((LinearLayout) linearLayout).getChildCount() > 0)
                    ((LinearLayout) linearLayout).removeAllViews();
//                String placeName = dataSnapshot.child("placeName").getValue().toString();
                PlaceModel placeModel = dataSnapshot.getValue(PlaceModel.class);
                setDatainUI(placeModel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // If cancelled
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setDatainUI(PlaceModel placeModel) {

        if (placeModel != null && placeModel.getPlaceId() != null) {
            if (placeModel.getPlaceName() != null) {
//                Toast.makeText(getContext(), placeModel.getPlaceName(), Toast.LENGTH_SHORT).show();
            }
            if (placeModel.getOverView() != null) {
                TextView newTextView = new TextView(getContext());
                addnewItemInlayout(newTextView, "Overview", placeModel.getOverView());
            }
            if (placeModel.getWayToGo() != null) {
                TextView newTextView = new TextView(getContext());
                addnewItemInlayout(newTextView, "Way To Go", placeModel.getWayToGo());
            }
            if (placeModel.getRangeOfCost() != null) {
                TextView newTextView = new TextView(getContext());
                addnewItemInlayout(newTextView, "Range of Cost", placeModel.getRangeOfCost());
            }
            if (placeModel.getAccommodation().length() != 0) {
                TextView newTextView = new TextView(getContext());
                addnewItemInlayout(newTextView, "Accommodation", placeModel.getAccommodation());
            }
            if (placeModel.getSpecialFood().length() != 0) {
                TextView newTextView = new TextView(getContext());
                addnewItemInlayout(newTextView, "Food", placeModel.getSpecialFood());
            }
            if (placeModel.getCaution().length() != 0) {
                TextView newTextView = new TextView(getContext());
                addnewItemInlayout(newTextView, "Caution", placeModel.getCaution());
            }
            if (placeModel.getReviews().get(0).length() != 0) {
                TextView newTextView = new TextView(getContext());
                addnewItemInlayout(newTextView, "Reviews", "1. '" + placeModel.getReviews().get(0) + "'");
                for (int i = 1; i < placeModel.getReviews().size(); i++){
                    TextView newReviewTextView = new TextView(getContext());
                    addReviews(newReviewTextView, (i +1) + ". '" + placeModel.getReviews().get(i)+ "'");
                }
            }
        }
    }

    public void addnewItemInlayout(TextView textView, String header, String textValue) {
        tabLayout.addTab(tabLayout.newTab().setText(header.toUpperCase()));

        TextView headerTextView = new TextView(getContext());
        headerTextView.setText(header + ":");
        headerTextView.setTextSize(20);
        headerTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(headerTextView);

        textView.setText(textValue);
        textView.setTextSize(14);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(textView);
    }

    public void addReviews(TextView textView, String textValue) {
        textView.setText(textValue);
        textView.setTextSize(14);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(textView);
    }

    public void performAction(int index, int scrollY) {
        TextView v = null;
        v = (TextView) linearLayout.getChildAt(index);
        prevTop = v.getTop();
        Log.i("CheckValue", v.getText().toString());
        v = (TextView) linearLayout.getChildAt(index + 2);
        nextTop = v.getTop();
        Log.i("CheckValue", v.getText().toString());

        if (prevTop < scrollY) {
            Log.i("CheckValue", "Gone1");
            TabLayout.Tab prevtab = tabLayout.getTabAt(index);
            prevtab.select();
        }
        if (nextTop < scrollY) {
            Log.i("CheckValue", "Gone2");
            TabLayout.Tab nexttab = tabLayout.getTabAt(index + 1);
            nexttab.select();
        }
    }
}
