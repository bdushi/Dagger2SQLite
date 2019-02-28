package com.example.dagger2sqlite.database;

import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class UserLocalDataSource implements UserDataSource {
    private final UserDao userDao;
    @Inject
    public UserLocalDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Maybe<Long> insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public Observable<Long> insertUsers(User... users) {
        return userDao.insertUsers(users);
    }

    @Override
    public Observable<Long> insertUsers(List<User> users) {
        return userDao.insertUsers(users);
    }

    @Override
    public Single<Integer> deleteUser(int index) {
        return userDao.deleteUser(index);
    }

    @Override
    public Single<Integer> updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Single<List<User>> users() {
        return userDao.users();
    }

    @Override
    public Single<User> getUser(int index) {
        return userDao.getUser(index);
    }
}
