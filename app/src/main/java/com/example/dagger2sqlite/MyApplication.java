package com.example.dagger2sqlite;

import android.app.Application;

import com.example.dagger2sqlite.di.AppComponent;
import com.example.dagger2sqlite.di.DaggerAppComponent;

public class MyApplication extends Application {
    //private AppComponent daggerComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        //daggerComponent = DaggerAppComponent.builder().application(this).build();
        //daggerComponent.inject(this);
        DaggerAppComponent.builder().application(this).build().inject(this);

    }

    /*public AppComponent getAppComponent(){
        return daggerComponent;
    }*/
}