package com.example.mahbub.travelmateui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_show);

        new CountDownTimer(2000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                //set the new Content of your activity
                MainActivity.this.setContentView(R.layout.activity_main);
                toolbar = findViewById(R.id.toolbarSelectOption);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Select an option");
            }
        }.start();
    }

    public void searchClickAction(View view) {
        Intent intent = new Intent(this, SearchPlaceManualActivity.class);
        startActivity(intent);
    }

    public void allDivisionClickAction(View view) {
        Intent intent = new Intent(this, AllDivisionActivity.class);
        startActivity(intent);
    }
}
