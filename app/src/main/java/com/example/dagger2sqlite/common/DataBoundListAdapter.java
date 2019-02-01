package com.example.dagger2sqlite.common;

import android.view.ViewGroup;

import com.example.dagger2sqlite.AppExecutors;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public abstract class DataBoundListAdapter<T, V extends ViewDataBinding> extends ListAdapter<T, DataBoundViewHolder<V>> {

    DataBoundListAdapter(AppExecutors appExecutors, @NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(new AsyncDifferConfig.Builder<T>(diffCallback).setBackgroundThreadExecutor(appExecutors.diskIO()).build());
    }

    @NonNull
    @Override
    public DataBoundViewHolder<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataBoundViewHolder<>(createBinding(parent));
    }

    @Override
    public void onBindViewHolder(@NonNull DataBoundViewHolder<V> holder, int position) {
        bind(getItem(position), holder.binding);
        holder.binding.executePendingBindings();
    }

    protected abstract void bind(T item, V binding);
    protected abstract V createBinding(ViewGroup parent);
}
