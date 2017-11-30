package com.example.mahbub.travelmateui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mahbub.travelmateui.model.LoginModel;

public class LauncherSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (LoginModel.isLogin) {
            // If user is logged in then directly call the MainOptionSelectActivity
            Intent intent = new Intent(LauncherSplashActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            // For Showing the login/registration UI first
            // We have to call the login/registration activity first by intent
            Intent intent = new Intent(LauncherSplashActivity.this, LoginRegistrationActivity.class);
            startActivity(intent);
        }

        finish();
    }
}
