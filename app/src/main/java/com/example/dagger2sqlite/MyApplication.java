package com.example.dagger2sqlite;

import android.app.Activity;
import android.app.Application;

import com.example.dagger2sqlite.database.UserRepository;
import com.example.dagger2sqlite.di.AppComponent;
import com.example.dagger2sqlite.di.DaggerAppComponent;

import javax.inject.Inject;

import androidx.annotation.VisibleForTesting;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApplication extends Application implements HasActivityInjector {
    AppComponent appComponent;
    @Inject
    public UserRepository userRepository;
    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        //DaggerAppComponent.builder().application(this).build().inject(this);

    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @VisibleForTesting
    public UserRepository userRepository() {
        return userRepository;
    }
}