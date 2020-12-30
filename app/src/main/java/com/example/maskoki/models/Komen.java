package com.example.maskoki.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Komen {
//    private String komen;
//    private Integer id;
//    private Integer user_id;
//    private Integer recipe_id;
//    private String comment

    @SerializedName("id")
    private Integer id;
    @SerializedName("user_id")
    private Integer userId;
    @SerializedName("recipe_id")
    private Integer recipeId;
    @SerializedName("comment")
    private String comment;
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public String getComment() {
        return comment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Komen(Integer id, Integer userId, Integer recipeId, String comment) {
        this.id = id;
        this.userId = userId;
        this.recipeId = recipeId;
        this.comment = comment;
    }
}
