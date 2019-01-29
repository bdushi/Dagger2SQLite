package com.example.dagger2sqlite.di;

import android.app.Application;

import javax.inject.Singleton;

import androidx.appcompat.app.AppCompatActivity;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        DatabaseModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class})
public interface AppComponent {
    void inject(AppCompatActivity appCompatActivity);
    void inject(Application application);
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
