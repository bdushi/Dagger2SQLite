package com.example.dagger2sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.dagger2sqlite.AppExecutors;
import com.example.dagger2sqlite.R;
import com.example.dagger2sqlite.common.BindingInterface;
import com.example.dagger2sqlite.common.CustomAdapter;
import com.example.dagger2sqlite.databinding.UserSingleItemBinding;
import com.example.dagger2sqlite.model.User;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public ViewModelProvider.Factory mViewModelFactory;

    /*@Inject
    User user;*/

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    AppExecutors appExecutors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        setContentView(R.layout.activity_main);
        final MainViewModel userViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        RecyclerView items = findViewById(R.id.items);
        items.setLayoutManager(new LinearLayoutManager(this));

        CustomAdapter<User, UserSingleItemBinding> customAdapter = new CustomAdapter<>(R.layout.user_single_item, new BindingInterface<User, UserSingleItemBinding>() {
            @Override
            public void bindData(User model, UserSingleItemBinding binder) {
                binder.setUser(model);
            }
        }, appExecutors);
        customAdapter.submitList(userViewModel.users());
        items.setAdapter(customAdapter);
        userViewModel.usersList().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        userViewModel.insert(new User(
                        "bruno",
                        "Bruno",
                        "Bruno",
                        "hash",
                        "bruno",
                        true,
                        true,
                        true,
                        1,
                        true,
                        "12-12-2012",
                        true,
                        true,
                        true,
                        true,
                        true,
                        true,
                        "bruno@bruno.al"));

        /*appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {

            }
        });*/
        /*new Handler().post(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++){
                    Log.i("MainActivity", String.valueOf(userViewModel.insert(new User("Bruno"))));
                }
            }
        });*/
        //Log.i(MainActivity.class.getName(), userViewModel.users().toString());

        /*userService.users().enqueue(new Callback<User[]>() {
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
        });*/
    }
}
