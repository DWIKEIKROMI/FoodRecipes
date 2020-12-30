package com.example.maskoki.api;

import androidx.lifecycle.LiveData;

import com.example.maskoki.models.Komen;
import com.example.maskoki.models.LoginM;
import com.example.maskoki.models.Post;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceAPI {
    @GET("/api/recipes")
    Call<List<Post>> getPosts();

    @FormUrlEncoded
    @POST("/api/users/register")
    Call<ResponseBody> Register(
            @Field("username") String username,
            @Field("name") String name,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/users/token")
    Call<LoginM> Login(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("/api/recipes/{recipeId}/comments")
    Call<List<Komen>> getkomen(
            @Path("recipeId") String id

    );

    @FormUrlEncoded
    @POST("/api/recipes/{recipeId}/comments")
    Call<ResponseBody> inputkomen(
            @HeaderMap Map<String, String> token,
            @Field("comment") String comment,
            @Path("recipeId") String id
    );

    @DELETE("/api/comments/{id}")
    Call<ResponseBody> hapuskomen(
            @HeaderMap Map<String, String> token,
            @Path("id") String id
    );
}
