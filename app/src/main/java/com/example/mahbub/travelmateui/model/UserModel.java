package com.example.mahbub.travelmateui.model;

/**
 * Created by MAHBUB on 04-Dec-17.
 */

public class UserModel {
    private int id;
    private String name;
    private String email;
    private String password;

    public UserModel(){

    }
    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
