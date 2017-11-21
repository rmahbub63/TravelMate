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

        popularList.add(new PopularPlacesModel(R.drawable.sajek, "Sajek Valley", 4.9f, "220 Review"));
        popularList.add(new PopularPlacesModel(R.drawable.coxs_bazar, "Cox's Bazar Sea Beach", 4.8f, "120 Review"));
        popularList.add(new PopularPlacesModel(R.drawable.jaflong, "Jaflong", 4.78f, "200 Review"));
        popularList.add(new PopularPlacesModel(R.drawable.lalbag_kella, "Lalbag Kella", 4.6f, "210 Review"));

        newList.add(new NewPlacesModel(R.drawable.moinot_ghat, "Moinot Ghat", 4.5f, "5 Review"));
        newList.add(new NewPlacesModel(R.drawable.napittachara_jhorna, "Napittachara Jhorna", 4.5f, "3 Review"));
        newList.add(new NewPlacesModel(R.drawable.chandpur_boro_stattion, "Chandpur Boro Station", 5.0f, "2 Review"));
        newList.add(new NewPlacesModel(R.drawable.ratargul, "Ratargul Swamp Forest", 4.5f, "10 Review"));

        recyclerViewPopularPlaces = findViewById(R.id.horizontal_recycler_view);
        recyclerViewPopularPlaces.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPopularPlaces.setAdapter(new PopularPlaceListAdapter(this, popularList));

        recyclerViewNewPlaces = findViewById(R.id.horizontal_recycler_view_new_place);
        recyclerViewNewPlaces.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNewPlaces.setAdapter(new NewPlaceListAdapter(this, newList));
    }
}
