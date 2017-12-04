package com.example.mahbub.travelmateui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mahbub.travelmateui.adapter.MyTourListAdapter;
import com.example.mahbub.travelmateui.model.MyTourList;

import java.util.ArrayList;

/**
 * Created by SHAJJAD on 04-Dec-17.
 */

public class MyTourPlanActivity extends AppCompatActivity {
    ArrayList<MyTourList> myTourLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tour_list);

        myTourLists = new ArrayList<>();
        myTourLists.add(new MyTourList("Shajek","Khagrachori","22/12/22",R.drawable.tour_plan_logo));
        myTourLists.add(new MyTourList("Shajek","Khagrachori","22/12/22",R.drawable.tour_plan_logo));
        myTourLists.add(new MyTourList("Shajek","Khagrachori","22/12/22",R.drawable.tour_plan_logo));
        myTourLists.add(new MyTourList("Shajek","Khagrachori","22/12/22",R.drawable.tour_plan_logo));
        myTourLists.add(new MyTourList("Shajek","Khagrachori","22/12/22",R.drawable.tour_plan_logo));
        myTourLists.add(new MyTourList("Shajek","Khagrachori","22/12/22",R.drawable.tour_plan_logo));


        RecyclerView myTourList = findViewById(R.id.vertical_recycler_view);
        myTourList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        String[] division = {"DHAKA","CHITTGONG","BARISHAL","KHULNA"};
        myTourList.setAdapter(new MyTourListAdapter(this, myTourLists));
    }
}
