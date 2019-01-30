package com.example.dagger2sqlite.ui;

import com.example.dagger2sqlite.BasePresenter;
import com.example.dagger2sqlite.BaseView;
import com.example.dagger2sqlite.model.User;

import java.util.List;

public interface MainContract {
    //manage view
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void users(List<User> users);
    }

    //manage CRUD operation and UI item visibility (state)
    interface Presenter extends BasePresenter<View> {
        int insert(User user);
    }
}
