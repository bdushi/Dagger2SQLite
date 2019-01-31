package com.example.dagger2sqlite.di;

import android.app.Application;

import com.example.dagger2sqlite.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        DatabaseModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<MyApplication> {
    //void inject(AppCompatActivity appCompatActivity);
    void inject(Application mainApplication);
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}
