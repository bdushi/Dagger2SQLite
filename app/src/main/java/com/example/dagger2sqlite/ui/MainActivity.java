package com.example.dagger2sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.AndroidInjection;

import android.os.Bundle;

import com.example.dagger2sqlite.R;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public ViewModelProvider.Factory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);
        //((MyApplication)getApplication()).getAppComponent().inject(this);

        setContentView(R.layout.activity_main);
        //ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        //userRepository.getUsers();
    }
}
