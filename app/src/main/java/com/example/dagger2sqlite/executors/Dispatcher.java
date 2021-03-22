package com.example.dagger2sqlite.executors;

import androidx.annotation.MainThread;
import io.reactivex.Single;

public abstract class Dispatcher<T> {
    /*@WorkerThread
    protected abstract long saveCallResult(T t);

    @MainThread
    protected abstract boolean shouldFetch(T t);*/

    //Using Observable to Publish Result
    public Single<T> loadFromDatabase() {
        return null;
    }

    @MainThread
    protected abstract Single<T> load();

}
