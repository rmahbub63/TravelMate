package com.example.mahbub.travelmateui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by SHAJJAD on 27-Nov-17.
 */

public class LoginActivity extends AppCompatActivity {
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.button);

    }


    public void loginClickAction(View view) {
        Intent intent = new Intent(LoginActivity.this, SearchPlaceManualActivity.class);
        startActivity(intent);
    }
}
