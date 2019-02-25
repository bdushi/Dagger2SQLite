package com.example.dagger2sqlite.executors;

import static com.example.dagger2sqlite.executors.Status.ERROR;
import static com.example.dagger2sqlite.executors.Status.LOADING;
import static com.example.dagger2sqlite.executors.Status.SUCCESS;

public class Resource<T> {
    private Status status;
    private T data;
    private String message;
    private Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    private static class Builder<T> {
        private Status status;
        private T data;
        private String message;

        Resource.Builder<T> setStatus(Status status) {
            this.status = status;
            return this;
        }

        Resource.Builder<T> setData(T data) {
            this.data = data;
            return this;
        }

        Resource.Builder<T> setMessage(String message) {
            this.message = message;
            return this;
        }

        Resource build() {
            return newInstance(status, data, message);
        }
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    private static <T> Resource<T> newInstance(Status status, T data, String message) {
        return new Resource<>(status, data, message);
    }

    //(SUCCESS, data, null);
    public static <T> Resource success(T data)  {
        return new Resource.Builder<T>().setStatus(SUCCESS).setData(data).setMessage(null).build();
    }

    public static <T> Resource error(String msg, T data) {
        //new Resource<T>(ERROR, data, msg);
        return new Resource.Builder<T>().setStatus(ERROR).setData(data).setMessage(msg).build();
    }

    public static <T> Resource loading(T data) {
        //return new Resource<T>(LOADING, data, null);
        return new Resource.Builder<T>().setStatus(LOADING).setData(data).setMessage(null).build();
    }
}
