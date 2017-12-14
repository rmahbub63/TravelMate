package com.example.mahbub.travelmateui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.ShowSelectedPlaceActivity;
import com.example.mahbub.travelmateui.adapter.AllPlaceListAdapter;
import com.example.mahbub.travelmateui.adapter.NewPlaceListAdapter;
import com.example.mahbub.travelmateui.adapter.PopularPlaceListAdapter;
import com.example.mahbub.travelmateui.fragment.main_fragments.RootFragment;
import com.example.mahbub.travelmateui.model.NewPlacesModel;
import com.example.mahbub.travelmateui.model.PlaceModel;
import com.example.mahbub.travelmateui.model.PopularPlacesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class SearchPlaceManualFragment extends RootFragment {

    RecyclerView recyclerViewPopularPlaces;
    RecyclerView recyclerViewNewPlaces;

    ArrayList<PopularPlacesModel> popularList;
    ArrayList<NewPlacesModel> newList;
    ArrayList<PlaceModel> placeModelArrayList;

    AllPlaceListAdapter allPlaceListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_search_place_manual, container, false);

        // For Recycler View
        popularList = new ArrayList<>();
        newList = new ArrayList<>();
        placeModelArrayList = new ArrayList<>();

        popularList.add(new PopularPlacesModel("-L0GsZts40N545xU2R2_", R.drawable.sajek, "Sajek Valley", 4.9f, "220 Review"));
        popularList.add(new PopularPlacesModel("-L0GsZts40N545xU2R2_", R.drawable.coxs_bazar, "Cox's Bazar Sea Beach", 4.8f, "120 Review"));
        popularList.add(new PopularPlacesModel("-L0GsZts40N545xU2R2_", R.drawable.jaflong, "Jaflong", 4.78f, "200 Review"));
        popularList.add(new PopularPlacesModel("-L0GsZts40N545xU2R2_", R.drawable.lalbag_kella, "Lalbag Kella", 4.6f, "210 Review"));

        newList.add(new NewPlacesModel("-L0GsZts40N545xU2R2_", R.drawable.sajek, "Sajek Valley", 4.9f, "220 Review"));
        newList.add(new NewPlacesModel("-L0GsZts40N545xU2R2_", R.drawable.sajek, "Sajek Valley", 4.9f, "220 Review"));
        newList.add(new NewPlacesModel("-L0GsZts40N545xU2R2_", R.drawable.sajek, "Sajek Valley", 4.9f, "220 Review"));
        newList.add(new NewPlacesModel("-L0GsZts40N545xU2R2_", R.drawable.sajek, "Sajek Valley", 4.9f, "220 Review"));

        recyclerViewPopularPlaces = mainView.findViewById(R.id.horizontal_recycler_view);
        recyclerViewPopularPlaces.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPopularPlaces.setAdapter(new PopularPlaceListAdapter(getContext(), popularList));

//        recyclerViewNewPlaces = mainView.findViewById(R.id.horizontal_recycler_view_new_place);
//        recyclerViewNewPlaces.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        recyclerViewNewPlaces.setAdapter(new NewPlaceListAdapter(getContext(), newList));
//

        allPlaceListAdapter = new AllPlaceListAdapter(getContext(), placeModelArrayList);
        recyclerViewNewPlaces = mainView.findViewById(R.id.horizontal_recycler_view_new_place);
        recyclerViewNewPlaces.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNewPlaces.setAdapter(allPlaceListAdapter);

        getAllPlaceModel(getContext());

        return mainView;
    }

    public void getAllPlaceModel(final Context context) {
        // provide reference to database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference placeReference = database.getReference("places");
        placeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                placeModelArrayList.clear();
                for (DataSnapshot placeSnap: dataSnapshot.getChildren())
                {
                    PlaceModel placeModel = placeSnap.getValue(PlaceModel.class);
//                    Toast.makeText(context, placeSnap.child("placeName").getValue().toString(), Toast.LENGTH_SHORT).show();
                    placeModelArrayList.add(placeModel);
                    allPlaceListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // If cancelled
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
