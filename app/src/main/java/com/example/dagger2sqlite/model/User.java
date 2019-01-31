package com.example.dagger2sqlite.model;

import com.squareup.moshi.Json;

public class User {
    private long id;
    @Json(name = "UserName")
    private String username;
    public User(){}

    public User(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
