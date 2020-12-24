package com.example.maskoki.api;

import com.example.maskoki.models.Post;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    Call<ResponseBody> Login(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("/api/recipes/{id}")
    Call<List<Post>> get(
            @Field("id") String id
    );
}
