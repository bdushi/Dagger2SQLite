package com.example.dagger2sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.dagger2sqlite.database.UserRepository;
import com.example.dagger2sqlite.model.User;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    public ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getApplication()).getAppComponent().inject(this);

        setContentView(R.layout.activity_main);
        viewModel.insertUser(new User("brunodushi"));
        Log.i(MainActivity.class.getName(), viewModel.getUsers().toString());
    }
}
