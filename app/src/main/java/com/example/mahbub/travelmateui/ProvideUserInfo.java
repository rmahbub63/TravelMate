package com.example.mahbub.travelmateui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class ProvideUserInfo extends AppCompatActivity {

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    private StorageReference profileImageRef;
    private FirebaseAuth mAuth;
    private static final int CHOOSE_IMAGE = 101;
    EditText editTextDisplayName;
    ImageView imageView;
    Button buttonSave;
    String profileImageUrl;
    Uri uriProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_user_info);


        // Assigning Activity this to progress dialog.
        progressDialog = new ProgressDialog(this);

        editTextDisplayName = findViewById(R.id.editText_display_name);
        imageView = findViewById(R.id.imageView_picture);
        imageView.setImageResource(R.drawable.image_add);
        buttonSave = findViewById(R.id.button_save);

        profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");
        mAuth = FirebaseAuth.getInstance();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInfo();
            }
        });
    }

    /* save user information */

    public void saveUserInfo(){
        String displayName = editTextDisplayName.getText().toString().trim();

        if(displayName.isEmpty()){
            editTextDisplayName.setError("Name Required");
            editTextDisplayName.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null){
            // Showing progress dialog at user registration time.
            progressDialog.setMessage("Processing...");
            progressDialog.show();
            UserProfileChangeRequest profileChangeRequest;
            if (uriProfileImage == null){
                profileChangeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName).build();
            } else {
                profileChangeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .setPhotoUri(Uri.parse(profileImageUrl)).build();
            }

            user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // Hiding the progress dialog after all task complete.
                    progressDialog.dismiss();

                    if (task.isSuccessful()){
                        Toast.makeText(ProvideUserInfo.this, "Profile Updated", Toast.LENGTH_SHORT).show();

                        // call MainActivity
                        Intent intent = new Intent(ProvideUserInfo.this, MainActivity.class);
                        // Remove all other activity
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ProvideUserInfo.this, "Profile is not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /* upload image to firebase storage */
    public void uploadImageToFirebase(){
        if(uriProfileImage != null){
            // Showing progress dialog at user registration time.
            progressDialog.setMessage("Processing...");
            progressDialog.show();

            profileImageRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Hiding the progress dialog after all task complete.
                    progressDialog.dismiss();

                    profileImageUrl = taskSnapshot.getDownloadUrl().toString();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Hiding the progress dialog after all task complete.
                    progressDialog.dismiss();

                    Toast.makeText(ProvideUserInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /* Choose an image from Gallery */
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), CHOOSE_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOOSE_IMAGE) {
                // Get the url from data
                uriProfileImage = data.getData();
                if (null != uriProfileImage) {
                    // Set the image in ImageView
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                        imageView.setImageBitmap(bitmap);
                        uploadImageToFirebase();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
