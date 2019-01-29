package com.example.dagger2sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements MainContract.View {
    @Inject
    public MainPresenter mainPresenter;

    @Inject
    public MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //presenter.insert(new User("bruno"));
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
        //Show Progress
    }

    @Override
    public void users(List<User> users) {
        Log.i(MainActivity.class.getName(), users.toString());
    }
}
