package com.example.dagger2sqlite.database;

import android.util.Log;

import com.example.dagger2sqlite.AppExecutors;
import com.example.dagger2sqlite.model.User;
import com.example.dagger2sqlite.ui.MainActivity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;

@Singleton
public class UserRepository {

    private final UserDataSource userDataSource;
    private final AppExecutors appExecutors;
    @Inject
    public UserRepository(@NonNull UserDataSource userDataSource, @NonNull AppExecutors appExecutors) {
        this.userDataSource = userDataSource;
        this.appExecutors = appExecutors;
    }

    public void insertUser(final User user) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.i(UserRepository.class.getName(), String.valueOf(userDataSource.insertUser(user)));
            }
        });
    }

    public void insertUsers(final User... users) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                userDataSource.insertUsers(users);
            }
        });
    }

    public void insertUsers(final List<User> users) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                userDataSource.insertUsers(users);
            }
        });
    }

    public void deleteUser(final int index) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                userDataSource.deleteUser(index);
            }
        });
    }

    public void updateUser(User user) {
        userDataSource.updateUser(user);
    }

    public List<User> getUsers() {
        return userDataSource.getUsers();
    }

    public User getUser(int index) {
        return userDataSource.getUser(index);
    }
}
