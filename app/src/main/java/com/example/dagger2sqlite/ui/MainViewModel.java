package com.example.dagger2sqlite.ui;

import android.util.Log;

import com.example.dagger2sqlite.database.UserRepository;
import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

public class MainViewModel extends ViewModel {

    private final UserRepository userRepository;

    @Inject
    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<List<User>> users() {
        return userRepository.users();
    }

    public void insert(User user) {
        userRepository.insertUser(user);
    }

    public void insert(List<User> users) {
        userRepository.insertUsers(users).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                Log.i(MainViewModel.class.getName(), String.valueOf(aLong));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Log.i(MainViewModel.class.getName(), throwable.getMessage());

            }
        });
    }

    public void inserts(final User[] users) {
        userRepository.insertUsers(users);
    }
}
