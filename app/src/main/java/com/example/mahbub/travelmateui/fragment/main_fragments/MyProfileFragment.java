package com.example.mahbub.travelmateui.fragment.main_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mahbub.travelmateui.MainActivity;
import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.controller.SharedPrefManager;
import com.example.mahbub.travelmateui.LoginRegistrationActivity;
import com.example.mahbub.travelmateui.model.UserModel;
import com.example.mahbub.travelmateui.model.LoginModel;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class MyProfileFragment extends RootFragment {

    TextView textViewName, textViewEmail;
    Button buttonLogout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_my_profile_main, null);
        textViewName = mainView.findViewById(R.id.textView_Name);
        textViewEmail = mainView.findViewById(R.id.textView_Email);
        buttonLogout = mainView.findViewById(R.id.button_logout);

        setDataInUI();

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getContext()).logout();
                // For finish all activity in stack if login
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXITALL", true);
                startActivity(intent);

                // Finish this Activity
                getActivity().finish();

                startActivity(new Intent(getContext(), LoginRegistrationActivity.class));
            }
        });

        return mainView;
    }

    private void setDataInUI() {
        //getting the current user
        UserModel user = SharedPrefManager.getInstance(getContext()).getUser();

        //setting the values to the textview
        textViewName.setText(user.getName());
        textViewEmail.setText(user.getEmail());
    }
}
