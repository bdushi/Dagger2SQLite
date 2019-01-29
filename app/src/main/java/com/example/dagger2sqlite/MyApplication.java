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
import dagger.android.support.DaggerApplication;

public class MyApplication extends DaggerApplication implements HasActivityInjector {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}