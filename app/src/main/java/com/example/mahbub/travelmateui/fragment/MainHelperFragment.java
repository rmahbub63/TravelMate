package com.example.mahbub.travelmateui.fragment;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.adapter.ViewPagerAdapterMain;
import com.example.mahbub.travelmateui.inrface.OnBackPressListener;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MainHelperFragment extends Fragment {

    public MainHelperFragment(){

    }

    /**
     * TabPagerIndicator
     *
     * Please refer to ViewPagerIndicator library
     */
    TabLayout tabLayout;
    ViewPager viewPager;

    private ViewPagerAdapterMain viewPagerAdapterMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_carousel, container, false);

        tabLayout = rootView.findViewById(R.id.tablayout_main);
        viewPager = rootView.findViewById(R.id.viewPager_main);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataToViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    /**
     * Retrieve the currently visible Tab Fragment and propagate the onBackPressed callback
     *
     * @return true = if this fragment and/or one of its associates Fragment can handle the backPress
     */
    public boolean onBackPressed() {
        // currently visible tab Fragment
        OnBackPressListener currentFragment = (OnBackPressListener) viewPagerAdapterMain.getRegisteredFragment(viewPager.getCurrentItem());

        if (currentFragment != null) {
            // lets see if the currentFragment or any of its childFragment can handle onBackPressed
//            Toast.makeText(getContext(), "If Called in MainHelperFragment onBackPressed", Toast.LENGTH_SHORT).show();
            return currentFragment.onBackPressed();
        }

        // this Fragment couldn't handle the onBackPressed call
        return false;
    }
    private void setDataToViewPager() {
        viewPagerAdapterMain = new ViewPagerAdapterMain(getChildFragmentManager());
        viewPagerAdapterMain.addFragment (new HomeFragment(),"Home");
        viewPagerAdapterMain.addFragment(new MyPlansFragment(),"My Plans");
        viewPagerAdapterMain.addFragment(new SavedPlacesFragment(),"Saved Place");
        viewPagerAdapterMain.addFragment(new MeProfileFragment(),"My Profile");
        viewPager.setAdapter(viewPagerAdapterMain);
    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabOne.setText("Home");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_main, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabTwo.setText("My Plans");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_plans_main, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabThree.setText("Saved Place");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saved_places_main, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null);
        tabFour.setText("My Profile");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_profile_main, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }

}
