package com.example.mahbub.travelmateui.fragment.selected_place_fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mahbub.travelmateui.R;


/**
 * Created by Retu on 02-12-17.
 */

public class OverviewFragment extends Fragment {

    TextView textViewOne, textViewTwo;
    ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View mainView = inflater.inflate(R.layout.overview_fragment_layout, null);

        textViewOne = mainView.findViewById(R.id.textOne);
        textViewTwo = mainView.findViewById(R.id.textTwo);
        scrollView = mainView.findViewById(R.id.test_scrollview);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY(); // For ScrollView

//                Toast.makeText(getContext(), String.valueOf(textViewTwo.getTop()), Toast.LENGTH_SHORT).show();
                Log.i("CheckValue", "Text 2 = " + String.valueOf(textViewTwo.getTop()) + " View Y = " + String.valueOf(scrollY));
                if (textViewTwo.getTop() < scrollY){
                    Log.i("CheckValue", "Gone");
                }
            }
        });


//        if (textViewOne.getVisibility() == View.GONE){
//            Toast.makeText(getContext(), "Gone One", Toast.LENGTH_SHORT).show();
//        }
        return mainView;
    }
}
