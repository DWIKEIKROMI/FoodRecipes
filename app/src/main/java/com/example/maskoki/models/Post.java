package com.example.maskoki.models;

import com.google.gson.annotations.SerializedName;

public class Post {
    private Integer id;
    private String title;
    private String description;
    private String image;
    private String ingredients;
    private String instructions;

    @SerializedName("body")
    private String text;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getText() {
        return text;
    }
}
