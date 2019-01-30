package com.example.dagger2sqlite;

import com.example.dagger2sqlite.database.UserRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainModule {
    @Binds
    public abstract ViewModel bindBaseActivity(UserRepository userRepository);
}
