package com.example.mahbub.travelmateui.model;

/**
 * Created by MAHBUB on 31-Oct-17.
 */

public class PopularPlacesModel {
    public String placeId;
    private int imageId;
    private String placeName;
    private float placeRating;
    private String noOfPlaceReview;

    public PopularPlacesModel() {
    }

    public PopularPlacesModel(String placeId, int imageId, String placeName, float placeRating, String noOfPlaceReview) {
        this.placeId = placeId;
        this.imageId = imageId;
        this.placeName = placeName;
        this.placeRating = placeRating;
        this.noOfPlaceReview = noOfPlaceReview;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public float getPlaceRating() {
        return placeRating;
    }

    public void setPlaceRating(float placeRating) {
        this.placeRating = placeRating;
    }

    public String getNoOfPlaceReview() {
        return noOfPlaceReview;
    }

    public void setNoOfPlaceReview(String noOfPlaceReview) {
        this.noOfPlaceReview = noOfPlaceReview;
    }
}
