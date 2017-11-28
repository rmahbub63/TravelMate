package com.example.mahbub.travelmateui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mahbub.travelmateui.LoginRegistrationActivity;
import com.example.mahbub.travelmateui.MainOptionSelectActivity;
import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.model.LoginModel;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by SHAJJAD on 27-Nov-17.
 */

public class LogInFragment extends  Fragment {
    LoginButton buttonFbLogin;
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
        //facebook login
        buttonFbLogin = mainView.findViewById(R.id.button_fb_login);

        buttonFbLogin.registerCallback(LoginRegistrationActivity.callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(),MainOptionSelectActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });
        return mainView;
    }
}
