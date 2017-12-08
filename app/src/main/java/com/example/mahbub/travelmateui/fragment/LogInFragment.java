package com.example.mahbub.travelmateui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import com.example.mahbub.travelmateui.LoginRegistrationActivity;
import com.example.mahbub.travelmateui.MainActivity;
import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.ShowSelectedPlaceActivity;
import com.example.mahbub.travelmateui.controller.UserController;
import com.example.mahbub.travelmateui.model.LoginModel;

import com.example.mahbub.travelmateui.model.UserModel;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by SHAJJAD on 27-Nov-17.
 */

public class LogInFragment extends  Fragment {

    private EditText editTextEmail, editTextPassword;
    private LoginButton buttonFbLogin;
    private Button buttonLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle){
        //
        View mainView = inflater.inflate(R.layout.activity_login, container, false);

        editTextEmail = mainView.findViewById(R.id.editTextEmail);
        editTextPassword = mainView.findViewById(R.id.editTextPassword);
        buttonLogin = mainView.findViewById(R.id.button_login);

        // Comment out should be removed
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // check whether user input value
//                if(formValidation()){
//                    // create an object of UserModel
//                    UserModel userModel = new UserModel();
//
//                    // getting the values from UI and set in model
//                    userModel.setEmail(editTextEmail.getText().toString().trim());
//                    userModel.setPassword(editTextPassword.getText().toString().trim());
//
//                    //crete an object of UserController and invoke it's userLogin method
//                    UserController userController = new UserController(getContext());
//                    userController.userLogin(userModel);
//                }
//            }
//        });


        //facebook login
        buttonFbLogin = mainView.findViewById(R.id.button_fb_login);

        // Callback method for facebook login
        buttonFbLogin.registerCallback(LoginRegistrationActivity.callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i("Information", "Success");
                Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(),ShowSelectedPlaceActivity.class);
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
                Log.e("ErrorInFb", "Problem");
                Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });
        return mainView;
    }


    public boolean formValidation() {
        boolean value = false;
        if (isEmailValid() && isPasswordValid()) {
            value = true;
        }
//        Toast.makeText(MainActivity.this, "Return value in formValidation " + value, Toast.LENGTH_LONG).show();
        return value;
    }

    public boolean isPasswordValid() {
        String password = editTextPassword.getText().toString();
        if (password.isEmpty()) {
            editTextPassword.setError("Please insert password");
            return false;
        } else if (password.length() < 6) {
            editTextPassword.setError("Please insert at least 6 characters");
            return false;
        }

        return true;
    }

    public boolean isEmailValid() {

        String email = editTextEmail.getText().toString().trim();

        String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Boolean b = email.matches(EMAIL_REGEX);

        if (email.isEmpty()) {
            editTextEmail.setError("Please insert email");
            return false;
        } else if (!b) {
            editTextEmail.setError("Please insert valid email");
            return false;
        }
        return true;
    }
}
