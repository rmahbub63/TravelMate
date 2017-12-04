package com.example.mahbub.travelmateui.fragment.main_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.fragment.main_fragments.RootFragment;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class MyPlansFragment extends RootFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_search_place_manual, null);
        return mainView;
    }
}
