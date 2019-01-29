package com.example.dagger2sqlite;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainModule {
    @Binds
    abstract AppCompatActivity bindBaseActivity(MainActivity activity);
}
