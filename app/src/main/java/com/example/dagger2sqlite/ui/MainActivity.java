package com.example.dagger2sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;

import android.os.Bundle;
import android.util.Log;

import com.example.dagger2sqlite.R;
import com.example.dagger2sqlite.model.User;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public ViewModelProvider.Factory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        setContentView(R.layout.activity_main);
        MainViewModel userViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        for(int i = 0; i < 10000; i++){
            Log.i("MainActivity", String.valueOf(userViewModel.insert(new User("Bruno"))));
        }
        Log.i("MainActivity",userViewModel.users().toString());
    }
}
