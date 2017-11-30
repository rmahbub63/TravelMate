package com.example.mahbub.travelmateui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mahbub.travelmateui.LoginRegistrationActivity;
import com.example.mahbub.travelmateui.MainOptionSelectActivity;
import com.example.mahbub.travelmateui.R;

/**
 * Created by SHAJJAD on 27-Nov-17.
 */

public class SignUpFragment extends Fragment {
    private Button buttonSignup,buttonGuest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View mainView = inflater.inflate(R.layout.fragment_signup_login_registration,null);


        buttonSignup = mainView.findViewById(R.id.buttonSignup);
        //SignUp to login activity
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginRegistrationActivity.class);
                startActivity(intent);
            }
        });

        return mainView;
    }
}
