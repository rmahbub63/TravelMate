package com.example.mahbub.travelmateui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mahbub.travelmateui.fragment.MainHelperFragment;

public class MainActivity extends AppCompatActivity {

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
    }

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
            super.onBackPressed();

        } else {
            // carousel handled the back pressed task
            // do not call super
        }
    }
}
