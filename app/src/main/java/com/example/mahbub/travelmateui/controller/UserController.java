package com.example.mahbub.travelmateui.controller;

/**
 * Created by MAHBUB on 04-Dec-17.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mahbub.travelmateui.MainActivity;
import com.example.mahbub.travelmateui.ProvideUserInfo;
import com.example.mahbub.travelmateui.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;


public class UserController {

    // Creating Progress dialog.
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private Context context;

    public UserController(Context context) {
        this.context = context;
        initialize();
    }

    public void initialize() {
        // provide reference
        mAuth = FirebaseAuth.getInstance();
        // Assigning Activity this to progress dialog.
        progressDialog = new ProgressDialog(context);
    }

    public void userRegistration(final UserModel userModel) {
        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Processing...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(userModel.getEmail(), userModel.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(context, "User Registration Successful", Toast.LENGTH_SHORT).show();
                            // call ProvideUserInfo
                            Intent intent = new Intent(context, ProvideUserInfo.class);
                            // Remove all other activity
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent);
                            ((AppCompatActivity)context).finish();

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                // If same email, display a message to the user.
                                Toast.makeText(context, "You are already registered", Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void userLogin(final UserModel userModel){
        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(userModel.getEmail(), userModel.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // Hiding the progress dialog after all task complete.
                progressDialog.dismiss();

                if (task.isSuccessful()){
                    // call MainActivity
                    Intent intent = new Intent(context, MainActivity.class);
                    // Remove all other activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                    ((AppCompatActivity)context).finish();
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(context, "You are now sign in as " + user.getDisplayName() + ". You can logout from My Profile",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
