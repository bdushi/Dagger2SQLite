package com.example.dagger2sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dagger2sqlite.R;
import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements MainContract.View, HasSupportFragmentInjector {
    //Injection in your fragment
    @Inject
    public DispatchingAndroidInjector<Fragment> activityDispatchingAndroidInjector;
    @Inject
    public MainPresenter mainPresenter;

    @Inject
    public MainContract.Presenter presenter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
        progressBar = findViewById(R.id.progress_circular);
        mainPresenter.viewModel.insertUser(new User("user"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.dropView();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        progressBar.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void users(List<User> users) {
        Log.i(MainActivity.class.getName(), users.toString());
    }

    //Injection in your fragment
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return activityDispatchingAndroidInjector;
    }
}
