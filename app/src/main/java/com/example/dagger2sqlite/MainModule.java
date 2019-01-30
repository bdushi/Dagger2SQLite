package com.example.dagger2sqlite;

import com.example.dagger2sqlite.database.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    /*@Binds
    public abstract UserRepository bindBaseActivity(ViewModel userRepository);*/
    @Provides
    public ViewModel bindBaseActivity(UserRepository userRepository) {
        return new ViewModel(userRepository);
    }
}
