package com.example.dagger2sqlite.database;

import com.example.dagger2sqlite.model.User;

import java.util.List;

public interface UserDataSource {
    void insertUser(User user);
    void deleteUser(int index);
    void updateUser(User user);
    List<User> getUsers();
    User getUser(int index);
}
