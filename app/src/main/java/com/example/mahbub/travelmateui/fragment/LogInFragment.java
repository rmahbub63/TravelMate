package com.example.mahbub.travelmateui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mahbub.travelmateui.MainOptionSelectActivity;
import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.model.LoginModel;

/**
 * Created by SHAJJAD on 27-Nov-17.
 */

public class LogInFragment extends  Fragment {

    private Button buttonLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        //
        View mainView = inflater.inflate(R.layout.activity_login,null);

        buttonLogin = mainView.findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginModel.isLogin = true;
                Intent intent = new Intent(getContext(), MainOptionSelectActivity.class);
                startActivity(intent);
            }
        });

        return mainView;
    }
}
