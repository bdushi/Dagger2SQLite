package com.example.dagger2sqlite;

import com.example.dagger2sqlite.database.UserDataSource;
import com.example.dagger2sqlite.database.UserRepository;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {
    /*@Provides
    public MainViewModel bindBaseActivity(UserRepository userRepository) {
        return new MainViewModel(userRepository);
    }*/

    @Binds
    public abstract UserDataSource bindBaseActivity(UserRepository userRepository);
}
