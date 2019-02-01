package com.example.dagger2sqlite.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dagger2sqlite.AppExecutors;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;

public class CustomAdapter<T, V extends ViewDataBinding> extends DataBoundListAdapter<T, V> {
    private int resources;
    private BindingInterface<T, V> bindingInterface;
    public CustomAdapter(int resources, BindingInterface<T, V> bindingInterface, AppExecutors appExecutors) {
        super(appExecutors, new DiffUtil.ItemCallback<T>(){
            @Override
            public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.resources = resources;
        this.bindingInterface = bindingInterface;
    }

    @Override
    protected void bind(T item, V binding) {
        bindingInterface.bindData(item, binding);
    }

    @Override
    protected V createBinding(ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), resources, parent, false/*, dataBindingComponent*/);
    }
}
