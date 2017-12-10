package com.example.mahbub.travelmateui.model;

import android.media.Image;

import java.util.List;

/**
 * Created by MAHBUB on 10-Dec-17.
 */

public class PlaceModel {

    String placeId;
    String placeName;
    String placeDivision;
    String placeDistrict;
    List<String> category;
    List<String> tags;
    Double rating;
    String overView;
    String wayToGo;
    String rangeOfCost;
    String specialFood;
    String accommodation;
    String caution;
    List<String> reviews;
    List<Image> images;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDivision() {
        return placeDivision;
    }

    public void setPlaceDivision(String placeDivision) {
        this.placeDivision = placeDivision;
    }

    public String getPlaceDistrict() {
        return placeDistrict;
    }

    public void setPlaceDistrict(String placeDistrict) {
        this.placeDistrict = placeDistrict;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getWayToGo() {
        return wayToGo;
    }

    public void setWayToGo(String wayToGo) {
        this.wayToGo = wayToGo;
    }

    public String getRangeOfCost() {
        return rangeOfCost;
    }

    public void setRangeOfCost(String rangeOfCost) {
        this.rangeOfCost = rangeOfCost;
    }

    public String getSpecialFood() {
        return specialFood;
    }

    public void setSpecialFood(String specialFood) {
        this.specialFood = specialFood;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
