package com.example.mahbub.travelmateui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

public class MainOptionSelectActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_option_select);

        toolbar = findViewById(R.id.toolbarSelectOption);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select an option");
    }



    public void searchClickAction(View view) {
        Intent intent = new Intent(this, SearchPlaceManualActivity.class);
        startActivity(intent);
    }

    public void allDivisionClickAction(View view) {
        Intent intent = new Intent(this, AllDistrictActivity.class);
        startActivity(intent);
    }

}
