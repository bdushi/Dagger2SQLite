package com.example.dagger2sqlite.ui;

import com.example.dagger2sqlite.database.UserRepository;
import com.example.dagger2sqlite.model.User;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final UserRepository userRepository;

    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> users() {
        return userRepository.getUsers();
    }
}
