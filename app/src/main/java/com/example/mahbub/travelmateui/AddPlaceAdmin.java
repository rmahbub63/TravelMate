package com.example.mahbub.travelmateui;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mahbub.travelmateui.controller.PlaceController;
import com.example.mahbub.travelmateui.model.DivisionModel;
import com.example.mahbub.travelmateui.model.PlaceModel;

import java.util.ArrayList;
import java.util.List;

public class AddPlaceAdmin extends AppCompatActivity {

    EditText editTextPlaceName, editTextCategories, editTextTags,
            editTextRating, editTextOverView , editTextWayToGo ,editTextRangeOfCost , editTextSpecialFood,
            editTextAccommodation, editTextCaution, editTextReviews;
    Spinner spinnerDivision, spinnerDistrict;
    Button buttonSave;
    ArrayList<DivisionModel> divisions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place_admin);

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

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaceModel placeModel = new PlaceModel();
                placeModel.setPlaceName(editTextPlaceName.getText().toString().trim());
                placeModel.setPlaceDivision(spinnerDivision.getSelectedItem().toString());
                placeModel.setPlaceDistrict(spinnerDistrict.getSelectedItem().toString());
                placeModel.setOverView(editTextOverView.getText().toString().trim());

                PlaceController placeController = new PlaceController(AddPlaceAdmin.this);
                placeController.savePlace(placeModel);


            }
        });
    }

    public void setSpinnerData(){
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
