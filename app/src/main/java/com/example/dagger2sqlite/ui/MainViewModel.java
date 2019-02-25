package com.example.dagger2sqlite.ui;

import com.example.dagger2sqlite.AppExecutors;
import com.example.dagger2sqlite.database.UserRepository;
import com.example.dagger2sqlite.executors.Resource;
import com.example.dagger2sqlite.model.User;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final AppExecutors appExecutors;

    @Inject
    public MainViewModel(UserRepository userRepository,  @NonNull AppExecutors appExecutors) {
        this.userRepository = userRepository;
        this.appExecutors = appExecutors;
    }

    public List<User> users() {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        return userRepository.users();
    }

    public void insert(User user) {
        userRepository.insertUser(user);
    }

    public void inserts(final User[] users) {
        userRepository.insertUsers(users);
    }


    public Executor usersList() {
        return new Executor() {
            @Override
            public void execute(Runnable command) {
                userRepository.users();
            }
        };
    }
}
