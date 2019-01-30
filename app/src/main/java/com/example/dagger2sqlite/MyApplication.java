package com.example.dagger2sqlite;

import com.example.dagger2sqlite.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerApplication;

public class MyApplication extends DaggerApplication implements HasActivityInjector {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}