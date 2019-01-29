package com.example.dagger2sqlite.database;

import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class UserRepository implements UserDataSource {

    private final UserDataSource userDataSource;
    @Inject
    public UserRepository(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @Override
    public void insertUser(User user) {
        userDataSource.insertUser(user);
    }

    @Override
    public void deleteUser(int index) {
        userDataSource.deleteUser(index);
    }

    @Override
    public void updateUser(User user) {
        userDataSource.updateUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDataSource.getUsers();
    }

    @Override
    public User getUser(int index) {
        return userDataSource.getUser(index);
    }
}
