package com.itay.finalproject;

public interface OnFirestoreObjectListener<T> {
    void onSuccess(T object);
    void onFailure(Exception e);
}
