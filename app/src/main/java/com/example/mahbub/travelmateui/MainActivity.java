package com.example.mahbub.travelmateui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mahbub.travelmateui.model.LoginModel;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_show);

        new CountDownTimer(2000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                if (LoginModel.isLogin) {
                    // If user is logged in then directly call the MainOptionSelectActivity
                    Intent intent = new Intent(MainActivity.this, MainOptionSelectActivity.class);
                    startActivity(intent);
                } else {
                    // For Showing the login/registration UI first
                    // We have to call the login/registration activity first by intent
                    Intent intent = new Intent(MainActivity.this, LoginRegistrationActivity.class);
                    startActivity(intent);
                }
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
