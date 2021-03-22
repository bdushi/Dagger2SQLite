package com.example.dagger2sqlite.database;

import com.example.dagger2sqlite.model.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface UserDao {
    Maybe<Long> insertUser(User user);
    Observable<Long> insertUsers(User... users);
    Observable<Long> insertUsers(List<User> users);
    Single<Integer> deleteUser(int index);
    Single<Integer> updateUser(User user);
    Single<List<User>> users();
    Single<User> getUser(int index);
}
