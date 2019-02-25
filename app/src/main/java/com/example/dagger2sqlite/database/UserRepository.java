package com.example.dagger2sqlite.database;

import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;

@Singleton
public class UserRepository {

    private final UserDataSource userDataSource;
    @Inject
    public UserRepository(@NonNull UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public void insertUser(final User user) {
        userDataSource.insertUser(user);
    }

    public void insertUsers(final User... users) {
        userDataSource.insertUsers(users);
    }

    public void insertUsers(final List<User> users) {
        userDataSource.insertUsers(users);
    }

    public void deleteUser(final int index) {
        userDataSource.deleteUser(index);
    }

    public void updateUser(User user) {
        userDataSource.updateUser(user);
    }

    public List<User> users() {
        return userDataSource.users();
    }

    public User getUser(int index) {
        return userDataSource.getUser(index);
    }
}
