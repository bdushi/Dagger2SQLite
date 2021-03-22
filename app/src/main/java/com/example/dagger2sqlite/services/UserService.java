package com.example.dagger2sqlite.services;

import com.example.dagger2sqlite.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("users?")
    Call<User[]> login(@Query("username") String username);
    @GET("users")
    Call<User[]> users();
}

