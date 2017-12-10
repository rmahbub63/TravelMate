package com.example.mahbub.travelmateui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.controller.UserController;
import com.example.mahbub.travelmateui.model.UserModel;

/**
 * Created by SHAJJAD on 27-Nov-17.
 */

public class SignUpFragment extends Fragment {

    private Button buttonSignup, buttonGuest;
    private EditText editTextEmail, editTextPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View mainView = inflater.inflate(R.layout.fragment_signup_login_registration, container, false);

        editTextEmail = mainView.findViewById(R.id.editTextEmail);
        editTextPassword = mainView.findViewById(R.id.editTextPassword);
        buttonSignup = mainView.findViewById(R.id.buttonSignup);

        //SignUp to login activity
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (formValidation()) {

                    UserModel userModel = new UserModel();
                    userModel.setId("");
                    userModel.setEmail(editTextEmail.getText().toString().trim());
                    userModel.setPassword(editTextPassword.getText().toString().trim());

                    // To authenticate the user in firebase
                    UserController userController = new UserController(getContext());
                    userController.userRegistration(userModel);
                }
            }
        });

        return mainView;
    }

    public boolean formValidation() {
        boolean value = false;
        if (isPasswordValid() && isEmailValid()) {
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
