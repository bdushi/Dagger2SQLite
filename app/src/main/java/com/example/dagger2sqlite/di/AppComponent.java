package com.example.dagger2sqlite.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        DatabaseModule.class,
        AndroidInjectionModule.class,
        ActivityBindingModule.class})
public interface AppComponent {
    void inject(Application application);
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
