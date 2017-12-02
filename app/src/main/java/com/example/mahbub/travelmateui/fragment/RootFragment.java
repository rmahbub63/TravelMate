package com.example.mahbub.travelmateui.fragment;

import android.support.v4.app.Fragment;
import com.example.mahbub.travelmateui.inrface.BackPressImpl;
import com.example.mahbub.travelmateui.inrface.OnBackPressListener;


/**
 * Created by shahabuddin on 6/6/14.
 */
public class RootFragment extends Fragment implements OnBackPressListener {

    @Override
    public boolean onBackPressed() {
        return new BackPressImpl(this).onBackPressed();
    }
}
