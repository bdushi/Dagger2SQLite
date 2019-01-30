package com.example.dagger2sqlite;

import com.example.dagger2sqlite.database.UserRepository;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    public MainViewModel bindBaseActivity(UserRepository userRepository) {
        return new MainViewModel(userRepository);
    }

    /*@Provides
    public ViewModelProvider.Factory bindBaseActivity(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }*/
}
