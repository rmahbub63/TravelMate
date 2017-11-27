package com.example.mahbub.travelmateui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mahbub.travelmateui.adapter.ViewPagerAdapter;
import com.example.mahbub.travelmateui.fragment.LogIn;
import com.example.mahbub.travelmateui.fragment.SignUp;

public class RegistrationActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        
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
}
