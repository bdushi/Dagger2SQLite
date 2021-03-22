package com.example.dagger2sqlite;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory {
    private final V v;
    public ViewModelProviderFactory(V v) {
        this.v = v;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(v.getClass())) {
            return (T) v;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
