package com.example.mahbub.travelmateui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mahbub.travelmateui.adapter.NewPlaceListAdapter;
import com.example.mahbub.travelmateui.adapter.PopularPlaceListAdapter;
import com.example.mahbub.travelmateui.model.NewPlacesModel;
import com.example.mahbub.travelmateui.model.PopularPlacesModel;

import java.util.ArrayList;

public class SearchPlaceManualActivity extends AppCompatActivity {

    RecyclerView recyclerViewPopularPlaces;
    RecyclerView recyclerViewNewPlaces;
    ArrayList<PopularPlacesModel> popularList;
    ArrayList<NewPlacesModel> newList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place_manual);
        // For Recycler View
        popularList = new ArrayList<>();
        newList = new ArrayList<>();

        popularList.add(new PopularPlacesModel(R.drawable.tour_plan_logo, "Bangladesh", 4.5f, "220 Review"));
        popularList.add(new PopularPlacesModel(R.drawable.tour_plan_logo, "Dhaka", 4.5f, "120 Review"));
        popularList.add(new PopularPlacesModel(R.drawable.tour_plan_logo, "Chandpur", 5.0f, "200 Review"));
        popularList.add(new PopularPlacesModel(R.drawable.tour_plan_logo, "Khulna", 4.5f, "210 Review"));

        newList.add(new NewPlacesModel(R.drawable.tour_plan_logo, "Barishal", 4.5f, "220 Review"));
        newList.add(new NewPlacesModel(R.drawable.tour_plan_logo, "Dhaka", 4.5f, "120 Review"));
        newList.add(new NewPlacesModel(R.drawable.tour_plan_logo, "Chandpur", 5.0f, "200 Review"));
        newList.add(new NewPlacesModel(R.drawable.tour_plan_logo, "Khulna", 4.5f, "210 Review"));

        recyclerViewPopularPlaces = findViewById(R.id.horizontal_recycler_view);
        recyclerViewPopularPlaces.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPopularPlaces.setAdapter(new PopularPlaceListAdapter(this, popularList));

        recyclerViewNewPlaces = findViewById(R.id.horizontal_recycler_view_new_place);
        recyclerViewNewPlaces.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNewPlaces.setAdapter(new NewPlaceListAdapter(this, newList));
    }
}
