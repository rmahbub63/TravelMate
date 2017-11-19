package com.example.mahbub.travelmateui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mahbub.travelmateui.adapter.DivisionListAdapter;
import com.example.mahbub.travelmateui.model.DivisionModel;

import java.util.ArrayList;

public class AllDivisionActivity extends AppCompatActivity {

    ArrayList<DivisionModel> divisions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_division);

        divisions = new ArrayList<>();
        divisions.add(new DivisionModel(R.drawable.ahsan_monjil, "Dhaka"));
        divisions.add(new DivisionModel(R.drawable.ahsan_monjil, "Chittagong"));
        divisions.add(new DivisionModel(R.drawable.ahsan_monjil, "Rajshahi"));
        divisions.add(new DivisionModel(R.drawable.ahsan_monjil, "Khulna"));
        divisions.add(new DivisionModel(R.drawable.ahsan_monjil, "Barishal"));

        RecyclerView divisionList = findViewById(R.id.vertical_recycler_view);
        divisionList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        String[] division = {"DHAKA","CHITTGONG","BARISHAL","KHULNA"};
        divisionList.setAdapter(new DivisionListAdapter(this, divisions));
    }
}
