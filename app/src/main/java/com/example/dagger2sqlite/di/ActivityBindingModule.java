package com.example.dagger2sqlite.di;

import com.example.dagger2sqlite.MainActivity;
import com.example.dagger2sqlite.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract MainActivity mainActivity();

}
