package com.example.dagger2sqlite;

import com.example.dagger2sqlite.database.UserRepository;
import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModel extends androidx.lifecycle.ViewModel {
    public final UserRepository userRepository;
    @Inject
    public ViewModel (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long insert(User user) {
        return userRepository.insert(user);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
