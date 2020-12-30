package com.example.maskoki.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("username")
    private String username;


    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;


    @SerializedName("id")
    private Integer id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(String username, String name, String password, Integer id) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.id = id;
    }
}
