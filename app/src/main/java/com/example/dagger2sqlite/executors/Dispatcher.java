package com.example.dagger2sqlite.executors;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;

public abstract class Dispatcher<T> {
    /*@WorkerThread
    protected abstract long saveCallResult(T t);

    @MainThread
    protected abstract boolean shouldFetch(T t);*/

    @MainThread
    protected abstract T loadFromDb();

}
