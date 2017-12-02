package com.example.mahbub.travelmateui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mahbub.travelmateui.fragment.MainHelperFragment;

public class MainActivity extends AppCompatActivity {

//    TabLayout tabLayout;
//    ViewPager viewPager;

    private MainHelperFragment mainHelperFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // withholding the previously created fragment from being created again
            // On orientation change, it will prevent fragment recreation
            // its necessary to reserving the fragment stack inside each tab
            initScreen();

        } else {
            // restoring the previously created fragment
            // and getting the reference
            mainHelperFragment = (MainHelperFragment) getSupportFragmentManager().getFragments().get(0);
        }

//        tabLayout = findViewById(R.id.tablayout_main);
//        viewPager = findViewById(R.id.viewPager_main);


//        setDataToViewPager();
//        tabLayout.setupWithViewPager(viewPager);
//        setupTabIcons();
    }

//    private void setDataToViewPager() {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment (new HomeFragment(),"Home");
//        adapter.addFragment(new MyPlansFragment(),"My Plans");
//        adapter.addFragment(new SavedPlacesFragment(),"Saved Place");
//        adapter.addFragment(new MeProfileFragment(),"My Profile");
//        viewPager.setAdapter(adapter);
//    }

//    private void setupTabIcons() {
//        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabOne.setText("Home");
//        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_main, 0, 0);
//        tabLayout.getTabAt(0).setCustomView(tabOne);
//
//        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabTwo.setText("My Plans");
//        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_plans_main, 0, 0);
//        tabLayout.getTabAt(1).setCustomView(tabTwo);
//
//        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabThree.setText("Saved Place");
//        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saved_places_main, 0, 0);
//        tabLayout.getTabAt(2).setCustomView(tabThree);
//
//        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
//        tabFour.setText("My Profile");
//        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_profile_main, 0, 0);
//        tabLayout.getTabAt(3).setCustomView(tabFour);
//    }

    private void initScreen() {
        // Creating the ViewPager container fragment once
        mainHelperFragment = new MainHelperFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mainHelperFragment)
                .commit();
    }

    /**
     * Only Activity has this special callback method
     * Fragment doesn't have any onBackPressed callback
     *
     * Logic:
     * Each time when the back button is pressed, this Activity will propagate the call to the
     * container Fragment and that Fragment will propagate the call to its each tab Fragment
     * those Fragments will propagate this method call to their child Fragments and
     * eventually all the propagated calls will get back to this initial method
     *
     * If the container Fragment or any of its Tab Fragments and/or Tab child Fragments couldn't
     * handle the onBackPressed propagated call then this Activity will handle the callback itself
     */
    @Override
    public void onBackPressed() {

        if (!mainHelperFragment.onBackPressed()) {
            // container Fragment or its associates couldn't handle the back pressed task
            // delegating the task to super class
//            Toast.makeText(MainActivity.this, "If Called in main acvitity onBackPressed", Toast.LENGTH_SHORT).show();
            super.onBackPressed();

        } else {
//            Toast.makeText(MainActivity.this, "else Called in main acvitity onBackPressed", Toast.LENGTH_SHORT).show();
            // carousel handled the back pressed task
            // do not call super
        }
    }
}
