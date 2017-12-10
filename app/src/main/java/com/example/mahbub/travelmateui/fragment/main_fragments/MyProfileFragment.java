package com.example.mahbub.travelmateui.fragment.main_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mahbub.travelmateui.AddPlaceAdmin;
import com.example.mahbub.travelmateui.LoginRegistrationActivity;
import com.example.mahbub.travelmateui.MainActivity;
import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.controller.SharedPrefManager;
import com.example.mahbub.travelmateui.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by MAHBUB on 01-Dec-17.
 */

public class MyProfileFragment extends RootFragment {

    TextView textViewName, textViewEmail;
    ImageView imageView;
    Button buttonLogout;
    FirebaseAuth myAuth;

    // ADMIN part start
    Button buttonAddPlace;
    // Admin Part end

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myAuth = FirebaseAuth.getInstance();
        View mainView = null;

        if (myAuth.getCurrentUser() != null){
            if (myAuth.getCurrentUser().getEmail().equals("admin@admin.com")){
                mainView = inflater.inflate(R.layout.fragment_admin_main, null);
            } else {
                mainView = inflater.inflate(R.layout.fragment_my_profile_main, null);
            }
        }
        textViewName = mainView.findViewById(R.id.textView_Name);
        textViewEmail = mainView.findViewById(R.id.textView_Email);
        buttonLogout = mainView.findViewById(R.id.button_logout);
        imageView = mainView.findViewById(R.id.imageView_picture);

        // ADMIN part start
        buttonAddPlace = mainView.findViewById(R.id.button_Add_place);

        buttonAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // Admin Part end


        setDataInUI();

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                // For finish all activity in stack if login
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("EXIT_ALL", true);
                // will close all the running activiities
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // Finish this Activity
                getActivity().finish();

                startActivity(new Intent(getContext(), LoginRegistrationActivity.class));
            }
        });

        buttonAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddPlaceAdmin.class));
            }
        });

        return mainView;
    }


    private void setDataInUI() {
        //getting the current user
        FirebaseUser user = myAuth.getCurrentUser();

        //setting the values
        if (user.getPhotoUrl() != null){
            String  photoUrl = user.getPhotoUrl().toString();
            Glide.with(this).load(user.getPhotoUrl().toString()).into(imageView);
        }

        if (user.getDisplayName() != null){
            textViewName.setText(user.getDisplayName());
        }

        textViewEmail.setText(user.getEmail());
    }
}
