package com.example.mahbub.travelmateui.model;

/**
 * Created by MAHBUB on 31-Oct-17.
 */

public class DivisionModel {
    private int picId;
    private String divisionName;

    public DivisionModel(int picId, String divisionName) {
        this.picId = picId;
        this.divisionName = divisionName;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

}