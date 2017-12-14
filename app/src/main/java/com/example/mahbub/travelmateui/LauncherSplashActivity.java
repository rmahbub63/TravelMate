package com.example.mahbub.travelmateui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LauncherSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting the current user
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        FirebaseUser user = myAuth.getCurrentUser();

        if (user != null) {
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

    @Override
    protected void onStart() {
        super.onStart();
        // For offline data
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
