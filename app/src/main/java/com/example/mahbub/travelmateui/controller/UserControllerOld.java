package com.example.mahbub.travelmateui.controller;

/**
 * Created by MAHBUB on 04-Dec-17.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mahbub.travelmateui.MainActivity;
import com.example.mahbub.travelmateui.model.URLs;
import com.example.mahbub.travelmateui.model.UserModel;

import java.util.HashMap;
import java.util.Map;


public class UserControllerOld {

    public static String loginBy = "";

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    private Context context;

    public UserControllerOld(Context context) {
        this.context = context;
        initialize();
    }

    public void initialize() {
        // Assigning Activity this to progress dialog.
        progressDialog = new ProgressDialog(context);
    }

    public void userRegistration(final UserModel userModel) {
        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Processing...");
        progressDialog.show();
        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // check whether response match with echo of URL API
                        if (ServerResponse.equalsIgnoreCase("Registration Successfully")) {
                            // Start the MainActivity
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent);
                            ((AppCompatActivity)context).finish();

                            Toast.makeText(context, "You are now sign in as " + userModel.getName() + ". You can logout from My Profile",
                                    Toast.LENGTH_SHORT).show();
                            // set vale of loginBy
                            loginBy = "EMAIL";
                            //storing the user in shared preferences
                            SharedPrefManager.getInstance(context).userLogin(userModel);
                        } else {
                            // Showing Echo Response Message Coming From Server.
                            Toast.makeText(context, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(context, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.

                params.put("id", "0");
                params.put("name", userModel.getName());
                params.put("email", userModel.getEmail());
                params.put("pass", userModel.getPassword());

                return params;
            }

        };

        // Adding the StringRequest object into requestQueue.
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void userLogin(final UserModel userModel) {

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        // check whether response match with echo of URL API
                        if (ServerResponse.equalsIgnoreCase("Data Matched")) {
                            // call MainActivity
                            Intent intent = new Intent(context, MainActivity.class);
                            // Remove all other activity
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent);
                            ((AppCompatActivity)context).finish();

                            Toast.makeText(context, "You are now sign in as " + userModel.getName() + ". You can logout from My Profile",
                                    Toast.LENGTH_SHORT).show();

                            // set vale of loginBy
                            loginBy = "EMAIL";

                            //storing the user in shared preferences
                            SharedPrefManager.getInstance(context).userLogin(userModel);
                        } else {
                            // Showing Echo Response Message Coming From Server.
                            Toast.makeText(context, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", userModel.getEmail());
                params.put("pass", userModel.getPassword());
                return params;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
