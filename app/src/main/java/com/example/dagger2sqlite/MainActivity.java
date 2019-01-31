package com.example.dagger2sqlite;

import dagger.android.support.DaggerAppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.dagger2sqlite.database.UserRepository;
import com.example.dagger2sqlite.model.User;

import javax.inject.Inject;


public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    public UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                userRepository.insert(new User("bruno"));
                userRepository.insert(new User("bruno"));
                userRepository.insert(new User("bruno"));
                userRepository.insert(new User("bruno"));
                userRepository.insert(new User("bruno"));
                userRepository.insert(new User("bruno"));
                userRepository.insert(new User("bruno"));
                userRepository.insert(new User("bruno"));
                userRepository.insert(new User("bruno"));
            }
        }, 1000);

        Log.i(MainActivity.class.getName(), userRepository.getUsers().toString());
    }
}
