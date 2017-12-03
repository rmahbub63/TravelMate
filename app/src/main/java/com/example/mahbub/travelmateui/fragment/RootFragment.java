package com.example.mahbub.travelmateui.fragment;

import android.support.v4.app.Fragment;
import com.example.mahbub.travelmateui.inrface.BackPressImpl;
import com.example.mahbub.travelmateui.inrface.OnBackPressListener;


/**
 * Created by MAHBUB on 01-Dec-17
 */
public class RootFragment extends Fragment implements OnBackPressListener {

    @Override
    public boolean onBackPressed() {
        return new BackPressImpl(this).onBackPressed();
    }
}
