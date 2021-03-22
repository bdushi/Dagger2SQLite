package com.example.dagger2sqlite.database;

import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class UserRepository {

    private final UserDataSource userDataSource;
    @Inject
    public UserRepository(@NonNull UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public void insertUser(final User user) {
        userDataSource.insertUser(user);
    }

    public void insertUsers(final User... users) {
        userDataSource.insertUsers(users);
    }

    public Observable<Long> insertUsers(final List<User> users) {
        return userDataSource.insertUsers(users);
    }

    public void deleteUser(final int index) {
        userDataSource.deleteUser(index);
    }

    public void updateUser(User user) {
        userDataSource.updateUser(user);
    }

    public Single<List<User>> users() {
        return userDataSource.users();
    }

    public Single<User> getUser(final int index) {
        return userDataSource.getUser(index);
        /*return new Dispatcher<User>() {
            @Override
            protected Single<User> load() {
                return Single.create(new SingleOnSubscribe<User>() {
                    @Override
                    public void subscribe(SingleEmitter<User> e) throws Exception {
                        userDataSource.getUser(index);
                    }
                });
            }
        }.load();*/
    }
}
