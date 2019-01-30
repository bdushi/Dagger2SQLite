package com.example.dagger2sqlite;

import com.example.dagger2sqlite.database.UserDataSource;
import com.example.dagger2sqlite.database.UserRepository;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    public UserRepository bindBaseActivity(UserDataSource userDataSource) {
        return new UserRepository(userDataSource);
    }


    /*@Binds
    public abstract UserDataSource bindBaseActivity(UserRepository userRepository);*/
}
