package com.example.mahbub.travelmateui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahbub.travelmateui.R;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class MyPlansFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.activity_search_place_manual, null);
        return mainView;
    }
}
