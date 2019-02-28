package com.example.dagger2sqlite.ui;

import com.example.dagger2sqlite.ViewModelProviderFactory;
import com.example.dagger2sqlite.database.UserRepository;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    /*@Provides
    public UserRepository bindBaseActivity(UserDataSource userDataSource) {
        return new UserRepository(userDataSource);
    }*/


    /*@Binds
    public abstract ViewModelProvider.Factory bindBaseActivity(ViewModelProviderFactory viewModelProviderFactory);*/

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MainViewModel provideMainViewModel(UserRepository userRepository) {
        return new MainViewModel(userRepository);
    }
}
