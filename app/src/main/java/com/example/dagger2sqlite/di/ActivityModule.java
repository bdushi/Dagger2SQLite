package com.example.dagger2sqlite.di;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

public class ActivityModule {

    private Activity mActivity;
    public ActivityModule(Activity activity) {
        mActivity = activity;
    }
    Activity provideActivity() {
        return mActivity;
    }
}
