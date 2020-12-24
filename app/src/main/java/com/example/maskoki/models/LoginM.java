package com.example.maskoki.models;

import com.google.gson.annotations.SerializedName;

public class LoginM {
    private String username;
    private String password;

    public LoginM(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    @SerializedName("body")
    private String text;

}
