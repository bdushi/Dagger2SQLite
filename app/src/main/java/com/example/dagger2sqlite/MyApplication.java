package com.example.dagger2sqlite;

import android.app.Activity;
import android.app.Application;

import com.example.dagger2sqlite.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApplication extends Application implements HasActivityInjector {
    //private AppComponent daggerComponent;
    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        //daggerComponent = DaggerAppComponent.builder().application(this).build();
        //daggerComponent.inject(this);
        DaggerAppComponent.builder().application(this).build().inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    /*public AppComponent getAppComponent(){
        return daggerComponent;
    }*/
}