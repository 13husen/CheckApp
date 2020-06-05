package com.designer.checkapp.helper;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<PostResponse> loginData(
            @Body JsonObject body);

    @POST("register")
    Call<PostResponse> registerData(
            @Body JsonObject body);

    @GET("volley_array.json")
    Call<DataResponse> getMovies();
}