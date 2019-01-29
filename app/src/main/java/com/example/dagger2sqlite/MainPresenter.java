package com.example.dagger2sqlite;

import android.os.Handler;

import com.example.dagger2sqlite.di.ActivityScoped;
import com.example.dagger2sqlite.model.User;

import javax.inject.Inject;

@ActivityScoped
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mItemView;
    public final ViewModel viewModel;
    @Inject
    public MainPresenter(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void insert(User user) {
        viewModel.insertUser(user);
    }

    @Override
    public void takeView(MainContract.View view) {
        this.mItemView = view;
        loadItems();

    }

    @Override
    public void dropView() {
        this.mItemView = null;

    }

    @Override
    public void clear() {
        //RxJava Clear Disposable
    }

    private void loadItems() {
        if(mItemView != null)
            mItemView.setLoadingIndicator(true);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mItemView.setLoadingIndicator(false);
                mItemView.users(viewModel.getUsers());
            }
        });
    }


}
