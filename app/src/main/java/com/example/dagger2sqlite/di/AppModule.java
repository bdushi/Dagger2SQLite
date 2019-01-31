package com.example.dagger2sqlite.di;

import android.app.Application;
import android.content.Context;


import com.example.dagger2sqlite.model.User;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {
    @Binds
    abstract Context bindContext(Application application);

    /*@Binds
    abstract User bindUser(User user);*/
}