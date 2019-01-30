package com.example.dagger2sqlite.di;

import com.example.dagger2sqlite.ui.MainActivity;
import com.example.dagger2sqlite.ui.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

}
