package com.example.dagger2sqlite.database;

import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserLocalDataSource implements UserDataSource {
    private final UserDao userDao;
    @Inject
    public UserLocalDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void deleteUser(int index) {
        userDao.deleteUser(index);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUser(int index) {
        return userDao.getUser(index);
    }
}
