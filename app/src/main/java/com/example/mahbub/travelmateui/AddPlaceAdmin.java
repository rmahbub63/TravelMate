package com.example.mahbub.travelmateui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mahbub.travelmateui.controller.PlaceController;
import com.example.mahbub.travelmateui.model.DivisionModel;
import com.example.mahbub.travelmateui.model.PlaceModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AddPlaceAdmin extends AppCompatActivity {

    private StorageReference profileImageRef;
    private static final int CHOOSE_IMAGE = 111;
    EditText editTextPlaceName, editTextCategories, editTextTags,
            editTextRating, editTextOverView, editTextWayToGo, editTextRangeOfCost, editTextSpecialFood,
            editTextAccommodation, editTextCaution, editTextReviews;
    Spinner spinnerDivision, spinnerDistrict;
    Button buttonSave;
    ImageView imageView;
    String profileImageUrl;
    Uri uriProfileImage;

    ArrayList<DivisionModel> divisions;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place_admin);

        // Assigning Activity this to progress dialog.
        progressDialog = new ProgressDialog(this);

        profileImageRef = FirebaseStorage.getInstance().getReference("main_image/" + System.currentTimeMillis() + ".jpg");

        imageView = findViewById(R.id.imageView_picture);
        imageView.setImageResource(R.drawable.image_add);

        editTextPlaceName = findViewById(R.id.editText_addPlaceName);
        editTextCategories = findViewById(R.id.editText_addPlaceCategory);
        editTextTags = findViewById(R.id.editText_addPlaceTags);
        editTextRating = findViewById(R.id.editText_addPlaceRating);
        editTextOverView = findViewById(R.id.editText_addPlaceOverview);
        editTextWayToGo = findViewById(R.id.editText_addPlaceWayToGo);
        editTextRangeOfCost = findViewById(R.id.editText_addPlaceRangeOfCost);
        editTextSpecialFood = findViewById(R.id.editText_addPlaceSpecialFood);
        editTextAccommodation = findViewById(R.id.editText_addPlaceAccommodation);
        editTextCaution = findViewById(R.id.editText_addPlaceCaution);
        editTextReviews = findViewById(R.id.editText_addPlaceReview);

        spinnerDivision = findViewById(R.id.divisionSpinner);
        spinnerDistrict = findViewById(R.id.districtSpinner);

        buttonSave = findViewById(R.id.button_save_place);

        setSpinnerData();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uriProfileImage != null || editTextPlaceName != null && spinnerDistrict.getSelectedItem() != null
                        && spinnerDivision.getSelectedItem() != null && editTextCategories != null && editTextTags != null) {
                    PlaceModel placeModel = new PlaceModel();
                    placeModel.setMainImageUri(uriProfileImage.toString());
                    placeModel.setPlaceName(editTextPlaceName.getText().toString().trim());
                    placeModel.setPlaceDivision(spinnerDivision.getSelectedItem().toString());
                    placeModel.setPlaceDistrict(spinnerDistrict.getSelectedItem().toString());
                    placeModel.setCategory(Arrays.asList(editTextCategories.getText().toString().trim().split(",")));
                    placeModel.setTags(Arrays.asList(editTextTags.getText().toString().trim().split(",")));
                    placeModel.setRating(Double.parseDouble(editTextRating.getText().toString().trim()));
                    placeModel.setOverView(editTextOverView.getText().toString().trim());
                    placeModel.setWayToGo(editTextWayToGo.getText().toString().trim());
                    placeModel.setRangeOfCost(editTextRangeOfCost.getText().toString().trim());
                    placeModel.setSpecialFood(editTextSpecialFood.getText().toString().trim());
                    placeModel.setAccommodation(editTextAccommodation.getText().toString().trim());
                    placeModel.setCaution(editTextCaution.getText().toString().trim());
                    placeModel.setReviews(Arrays.asList(editTextReviews.getText().toString().trim().split(",")));


                    PlaceController placeController = new PlaceController(AddPlaceAdmin.this);
                    placeController.savePlace(placeModel);
                } else {
                    Toast.makeText(AddPlaceAdmin.this, "Enter proper data", Toast.LENGTH_LONG).show();
                }
            }
        });
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

                            Toast.makeText(AddPlaceAdmin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void setSpinnerData() {
        divisions = new ArrayList<>();
        divisions.add(new DivisionModel("Barisal"));
        divisions.add(new DivisionModel("Chittagong"));
        divisions.add(new DivisionModel("Dhaka"));
        divisions.add(new DivisionModel("Khulna"));
        divisions.add(new DivisionModel("Mymensingh"));
        divisions.add(new DivisionModel("Rajshahi"));
        divisions.add(new DivisionModel("Rangpur"));
        divisions.add(new DivisionModel("Sylhet"));

        ArrayAdapter<DivisionModel> adapter = new ArrayAdapter<DivisionModel>(AddPlaceAdmin.this, R.layout.spinner_text, divisions);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerDivision.setAdapter(adapter);
        spinnerDistrict.setAdapter(adapter);
    }
}
