package com.java.tutorial.project.service;

public interface CompletableTask<T> {

    T execute(T task);

    default void secusess() {
    }

    ;

    default void error() {
    }

    ;
}
