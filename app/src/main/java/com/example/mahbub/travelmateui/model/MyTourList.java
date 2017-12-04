package com.example.mahbub.travelmateui.model;

/**
 * Created by SHAJJAD on 04-Dec-17.
 */

public class MyTourList {

    private String placeName;
    private String location;
    private String tourDate;
    private int picId;

    public MyTourList(String placeName, String location, String tourDate, int picId) {
        this.placeName = placeName;
        this.location = location;
        this.tourDate = tourDate;
        this.picId = picId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTourDate() {
        return tourDate;
    }

    public void setTourDate(String tourDate) {
        this.tourDate = tourDate;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
