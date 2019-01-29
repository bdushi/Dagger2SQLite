package com.example.dagger2sqlite.di;

import android.app.Application;
import android.content.Context;


import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {
    @Binds
    abstract Context bindContext(Application application);

    /*private final MyApplication myApplication;

    public AppModule(MyApplication myApplication){
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    MyApplication providesApplicationContext() {
        return myApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context context) {
        return context.getSharedPreferences("My_Pref", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SQLiteDatabase providesDatabaseHelper(Context context) {
        return new LocalDatabaseHelper(context).getWritableDatabase();
    }*/
}