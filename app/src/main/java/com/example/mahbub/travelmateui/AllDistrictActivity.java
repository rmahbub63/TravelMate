package com.example.mahbub.travelmateui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mahbub.travelmateui.adapter.DivisionListAdapter;
import com.example.mahbub.travelmateui.model.DivisionModel;

import java.util.ArrayList;

public class AllDistrictActivity extends AppCompatActivity {

    ArrayList<DivisionModel> divisions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_district);

        divisions = new ArrayList<>();
        divisions.add(new DivisionModel(R.drawable.barisal_div_image, "Barisal"));
        divisions.add(new DivisionModel(R.drawable.chittagong_division, "Chittagong"));
        divisions.add(new DivisionModel(R.drawable.dhaka_division, "Dhaka"));
        divisions.add(new DivisionModel(R.drawable.khulna_division, "Khulna"));
        divisions.add(new DivisionModel(R.drawable.mymensingh_division, "Mymensingh"));
        divisions.add(new DivisionModel(R.drawable.rajshahi_division, "Rajshahi"));
        divisions.add(new DivisionModel(R.drawable.rangpur_division, "Rangpur"));
        divisions.add(new DivisionModel(R.drawable.sylhet_div_image, "Sylhet"));

        RecyclerView divisionList = findViewById(R.id.vertical_recycler_view);
        divisionList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        String[] division = {"DHAKA","CHITTGONG","BARISHAL","KHULNA"};
        divisionList.setAdapter(new DivisionListAdapter(this, divisions));
    }
}
