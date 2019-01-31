package com.example.dagger2sqlite.ui;

import com.example.dagger2sqlite.database.UserRepository;
import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final UserRepository userRepository;

    @Inject
    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> users() {
        return userRepository.getUsers();
    }

    public long insert(User user) {
        return userRepository.insertUser(user);
    }
}
