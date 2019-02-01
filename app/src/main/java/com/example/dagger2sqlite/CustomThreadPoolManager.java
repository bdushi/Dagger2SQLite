package com.example.dagger2sqlite;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolManager {
    private static CustomThreadPoolManager sInstance = null;
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT;

    public final ExecutorService mExecutorService;
    private final BlockingQueue<Runnable> mTaskQueue;
    private List<Future> mRunningTaskList;

    //private WeakReference<UiThreadCallback> uiThreadCallbackWeakReference;

    // The class is used as a singleton
    static {
        KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        sInstance = new CustomThreadPoolManager();
    }

    // Made constructor private to avoid the class being initiated from outside
    private CustomThreadPoolManager() {
        // initialize a queue for the thread pool. New tasks will be added to this queue
        mTaskQueue = new LinkedBlockingQueue<>();
        mRunningTaskList = new ArrayList<>();
        mExecutorService = new ThreadPoolExecutor(NUMBER_OF_CORES,
                NUMBER_OF_CORES,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                mTaskQueue,
                new BackgroundThreadFactory());
    }

    public static CustomThreadPoolManager getsInstance() {
        return sInstance;
    }

   /* // Keep a weak reference to the UI thread, so we can send messages to the UI thread
    public void setUiThreadCallback(UiThreadCallback uiThreadCallback) {
        this.uiThreadCallbackWeakReference = new WeakReference<UiThreadCallback>(uiThreadCallback);
    }*/
    private static class BackgroundThreadFactory implements ThreadFactory {
       private static int sTag = 1;

       @Override
       public Thread newThread(Runnable runnable) {
           Thread thread = new Thread(runnable);
           thread.setName("CustomThread" + sTag);
           //thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);

           // A exception handler is created to log the exception from threads
           thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
               @Override
               public void uncaughtException(Thread thread, Throwable ex) {
                   Log.e(CustomThreadPoolManager.class.getName(), thread.getName() + " encountered an error: " + ex.getMessage());
               }
           });
           return thread;
       }
   }
}
