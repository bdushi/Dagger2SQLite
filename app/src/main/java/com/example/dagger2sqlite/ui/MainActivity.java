package com.example.dagger2sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.example.dagger2sqlite.R;
import com.example.dagger2sqlite.model.User;
import com.example.dagger2sqlite.services.UserService;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public ViewModelProvider.Factory mViewModelFactory;

    @Inject
    public UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        setContentView(R.layout.activity_main);
        final MainViewModel userViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        /*new Handler().post(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++){
                    Log.i("MainActivity", String.valueOf(userViewModel.insert(new User("Bruno"))));
                }
            }
        });*/
        //Log.i(MainActivity.class.getName(), userViewModel.users().toString());

        userService.users().enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(@NotNull Call<User[]> call, @NotNull Response<User[]> response) {
                if(response.body() != null)
                    Log.i(MainActivity.class.getName(), response.body().toString());
                else
                    Log.i(MainActivity.class.getName(), "Null");
            }

            @Override
            public void onFailure(@NotNull Call<User[]> call, @NotNull Throwable t) {
                Log.i(MainActivity.class.getName(), t.getMessage());
            }
        });
    }
}
