package com.example.dagger2sqlite;

import androidx.appcompat.app.AppCompatActivity;
import dagger.android.AndroidInjection;

import android.os.Bundle;

import com.example.dagger2sqlite.database.UserRepository;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        //((MyApplication)getApplication()).getAppComponent().inject(this);

        setContentView(R.layout.activity_main);
        userRepository.getUsers();
    }
}
