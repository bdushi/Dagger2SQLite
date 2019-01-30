package com.example.dagger2sqlite.di;

import android.app.Application;

import com.example.dagger2sqlite.MyApplication;

import javax.inject.Singleton;

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
    void inject(MyApplication application);
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
