package com.example.dagger2sqlite.di;

import com.example.dagger2sqlite.MainActivity;
import com.example.dagger2sqlite.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();

}
