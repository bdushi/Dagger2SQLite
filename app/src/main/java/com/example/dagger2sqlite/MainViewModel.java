package com.example.dagger2sqlite;

import com.example.dagger2sqlite.database.UserDataSource;
import com.example.dagger2sqlite.database.UserRepository;
import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainViewModel implements UserDataSource {
    public final UserRepository userRepository;

    @Inject
    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long insert(User user) {
        return userRepository.insert(user);
    }

    @Override
    public void deleteUser(int index) {

    }

    @Override
    public void updateUser(User user) {

    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUser(int index) {
        return null;
    }
}
