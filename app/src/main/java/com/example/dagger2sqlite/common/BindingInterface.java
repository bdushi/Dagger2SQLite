package com.example.dagger2sqlite.common;

import androidx.databinding.ViewDataBinding;

/**
 * Created by bruno on 6/23/2017.
 */

public interface BindingInterface<T, V extends ViewDataBinding> {
    void bindData(T model, V binder);
}