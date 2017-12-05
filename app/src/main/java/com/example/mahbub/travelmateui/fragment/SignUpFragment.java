package com.example.mahbub.travelmateui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.controller.UserController;
import com.example.mahbub.travelmateui.model.UserModel;

/**
 * Created by SHAJJAD on 27-Nov-17.
 */

public class SignUpFragment extends Fragment {

    private Button buttonSignup, buttonGuest;
    private EditText editTextName, editTextEmail, editTextPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View mainView = inflater.inflate(R.layout.fragment_signup_login_registration, container, false);


        editTextName = mainView.findViewById(R.id.editTextName);
        editTextEmail = mainView.findViewById(R.id.editTextEmail);
        editTextPassword = mainView.findViewById(R.id.editTextPassword);
        buttonSignup = mainView.findViewById(R.id.buttonSignup);

        //SignUp to login activity
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (formValidation()) {
                    UserModel userModel = new UserModel();
                    userModel.setId(0);
                    userModel.setName(editTextName.getText().toString().trim());
                    userModel.setEmail(editTextEmail.getText().toString().trim());
                    userModel.setPassword(editTextPassword.getText().toString().trim());

                    UserController userController = new UserController(getContext());
                    userController.userRegistration(userModel);
                }
            }
        });

        return mainView;
    }

    public boolean formValidation() {
        boolean value = false;
        if (isUserNameValid() && isPasswordValid() && isEmailValid()) {
            value = true;
        }
//        Toast.makeText(MainActivity.this, "Return value in formValidation " + value, Toast.LENGTH_LONG).show();
        return value;
    }

    public boolean isUserNameValid() {
        String userName = editTextName.getText().toString().trim();
        if (userName.isEmpty()) {
            editTextName.setError("Please insert username");
            return false;
        } else if (userName.length() < 3 || userName.length() > 50) {
            editTextName.setError("Please insert valid username");
            return false;
        } else if (userName.contains(" ")) {
            editTextName.setError("Username should not contain any space");
            return false;
        }
        return true;
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
