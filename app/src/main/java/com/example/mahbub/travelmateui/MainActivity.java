package com.example.mahbub.travelmateui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mahbub.travelmateui.adapter.ViewPagerAdapter;
import com.example.mahbub.travelmateui.fragment.LogIn;
import com.example.mahbub.travelmateui.fragment.SignUp;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
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
               MainActivity.this.setContentView(R.layout.activity_registration);
                //toolbar = findViewById(R.id.toolbarSelectOption);
                //setSupportActionBar(toolbar);
                //getSupportActionBar().setTitle("Select an option");
                tabLayout = findViewById(R.id.tablayout);
                viewPager = findViewById(R.id.viewPager);

                setDataToViewPager();
                tabLayout.setupWithViewPager(viewPager);
            }

            private void setDataToViewPager() {
                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                adapter.addFragment(new LogIn(),"Log In");
                adapter.addFragment(new SignUp(),"Sign Up");
                viewPager.setAdapter(adapter);
            }

        }.start();
    }
    public void main(){
        //hello

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
