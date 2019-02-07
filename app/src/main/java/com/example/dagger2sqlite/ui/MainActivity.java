package com.example.dagger2sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;

import android.os.Bundle;

import com.example.dagger2sqlite.R;
import com.example.dagger2sqlite.database.UserRepository;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public ViewModelProvider.Factory mViewModelFactory;

    @Inject
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);
        //((MyApplication)getApplication()).getAppComponent().inject(this);

        setContentView(R.layout.activity_main);
        ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        userRepository.getUsers();
    }
}
